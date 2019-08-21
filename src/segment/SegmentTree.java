package segment;

import java.util.function.BinaryOperator;

public class SegmentTree<E> {
    private E[] tree;
    private E[] data;
    private BinaryOperator<E> binaryOperator;

    public SegmentTree(E[] arr, BinaryOperator<E> binaryOperator) {
        data = (E[]) new Object[arr.length];
        for (int i = 0; i < arr.length; i++) {
            data[i] = arr[i];
        }

        tree = (E[]) new Object[4 * arr.length];
        this.binaryOperator = binaryOperator;

        buildSegmentTree(0, 0, data.length - 1);
    }

    public int getSize() {
        return data.length;
    }

    private int leftChild(int index) {
        return 2 * index + 1;
    }

    private int rightChild(int index) {
        return 2 * index + 2;
    }

    /**
     * 宏观语义：在treeIndex的位置创建表示区间[l...r]的线段树
     */
    private void buildSegmentTree(int treeIndex, int l, int r) {
        if (l == r) {
            tree[treeIndex] = data[l];
            return;
        }

        int leftTreeIndex = leftChild(treeIndex);
        int rightTreeIndex = rightChild(treeIndex);

        //（l+r)/2 防止int值溢出
        int mid = l + (r - l) / 2;

        buildSegmentTree(leftTreeIndex, l, mid);
        buildSegmentTree(rightTreeIndex, mid + 1, r);

        tree[treeIndex] = binaryOperator.apply(tree[leftTreeIndex], tree[rightTreeIndex]);
    }


    public E query(int queryL, int queryR) {
        if (queryL < 0 || queryL >= data.length || queryR < 0 || queryR >= data.length) {
            throw new IllegalArgumentException("Index is illegal.");
        }

        return query(0, 0, data.length - 1, queryL, queryR);
    }

    /*在以treeIndex为根,区间[l...r]之间的线段树下搜索区间[queryL...queryR]的值*/
    private E query(int treeIndex, int l, int r, int queryL, int queryR) {
        if (l == queryL && r == queryR) {
            return tree[treeIndex];
        }

        int leftTreeIndex = leftChild(treeIndex);
        int rightTreeIndex = rightChild(treeIndex);

        int mid = l + (r - l) / 2;
        if (queryL >= mid + 1) {
            return query(rightTreeIndex, mid + 1, r, queryL, queryR);
        } else if (queryR <= mid) {
            return query(leftTreeIndex, l, mid, queryL, queryR);
        } else {
            E leftResult = query(leftTreeIndex, l, mid, queryL, mid);
            E rightResult = query(rightTreeIndex, mid + 1, r, mid + 1, queryR);

            return binaryOperator.apply(leftResult, rightResult);
        }
    }

    public void set(int index, E e) {
        if (index < 0 || index >= data.length) {
            throw new IllegalArgumentException("Index is illegal.");
        }

        data[index] = e;

        set(0, 0, data.length - 1, index, e);
    }

    /*更新以treeIndex为根节点[l...r]区间的线段树中的*/
    private void set(int treeIndex, int l, int r, int index, E e) {
        if (l == r) {
            tree[treeIndex] = e;
            return;
        }

        int leftTreeIndex = leftChild(treeIndex);
        int rightTreeIndex = rightChild(treeIndex);

        int mid = l + (r - l) / 2;
        if (index >= mid + 1) {
            set(rightTreeIndex, mid + 1, r, index, e);
        } else if (index <= mid) {
            set(leftTreeIndex, l, mid, index, e);
        }

        tree[treeIndex] = binaryOperator.apply(tree[leftTreeIndex],tree[rightTreeIndex]);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append('[');
        for (E e : tree) {
            if (e != null) {
                sb.append(e).append(',');
            } else {
                sb.append("null").append(',');
            }
        }
        sb.deleteCharAt(sb.length() - 1);
        sb.append(']');
        return sb.toString();
    }
}
