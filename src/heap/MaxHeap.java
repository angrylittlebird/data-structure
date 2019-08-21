package heap;

public class MaxHeap<E extends Comparable<E>> {
    private Array<E> data;

    public MaxHeap(int capacity) {
        this.data = new Array<>(capacity);
    }

    public MaxHeap() {
        this.data = new Array<>();
    }

    public MaxHeap(E[] arr) {
        data = new Array<>(arr);
        for (int i = parentIndex(arr.length - 1); i >= 0; i--) {//从最后一个非叶子节点开始向上进行siftDown的操作
            siftDown(i);
        }
    }

    /**
     * @param index
     * @return 返回当前索引index节点的父节点的索引
     */
    private int parentIndex(int index) {
        return (index - 1) / 2;
    }

    private void siftDown(int k) {
        while (leftChild(k) < data.getSize()) {
            int j = leftChild(k);
            if (j + 1 < size() && data.get(j).compareTo(data.get(j + 1)) < 0) {
                j++;
            }

            if (data.get(k).compareTo(data.get(j)) < 0) {
                data.swap(k, j);
            } else {
                //if (data.get(k).compareTo(data.get(j)) >= 0)
                break;
            }

            k = j;
        }
    }

    /**
     * @param index
     * @return 返回当前索引index节点的左子节点的索引
     */
    private int leftChild(int index) {
        return (2 * index) + 1;
    }

    public int size() {
        return data.getSize();
    }

    public void add(E e) {
        data.addLast(e);
        siftUp(data.getSize() - 1);
    }

    private void siftUp(int k) {
        while (k != 0 && data.get(k).compareTo(data.get(parentIndex(k))) > 0) {
            data.swap(k, parentIndex(k));
            k = parentIndex(k);
        }
    }

    public E extractMax() {
        E ret = findMax();

        data.swap(0, data.getSize() - 1);
        data.removeLast();

        siftDown(0);

        return ret;
    }

    public E findMax() {
        if (size() <= 0) {
            throw new IllegalArgumentException("Can not find max when heap is empty.");
        }
        return data.get(0);
    }

    public Boolean isEmpty() {
        return data.isEmpty();
    }

    public E replace(E e) {
        E ret = findMax();
        data.set(0, e);
        siftDown(0);
        return ret;
    }

    /**
     * @param index
     * @return 返回当前索引index节点的右子节点的索引
     */
    private int rightChild(int index) {
        return (2 * index) + 2;
    }


}
