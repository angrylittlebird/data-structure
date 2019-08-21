package tree;

import linkedlist.LinkedListQueue;
import stack.ArrayStack;

public class BST<E extends Comparable<E>> {
    private class Node {
        private E e;
        private Node right, left;

        public Node(E e) {
            this.e = e;
            this.right = null;
            this.left = null;
        }
    }

    private int size;
    private Node root;

    public BST() {
        this.root = null;
        this.size = 0;
    }

    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void add(E e) {
//        if (root == null) {
//            root = new Node(e);
//        }else {
//            add(root, e);
//        }
        root = add(root, e);
    }

    ///
//    /**
//     * 宏观语义：向该node添加 以一个子节点
//     * @param node
//     * @param e
//     */
//    private void add(Node node, E e) {
//        if (node.e.compareTo(e) == 0) {
//            return;
//        } else if (node.e.compareTo(e) == -1 && node.left == null) {
//            node.left = new Node(e);
//            size++;
//            return;
//        } else if (node.e.compareTo(e) == 1 && node.right == null) {
//            node.right = new Node(e);
//            size++;
//            return;
//        }
//
//        if (node.e.compareTo(e) == -1) {
//            add(node.left, e);
//        }else {
//            add(node.right, e);
//        }
//    }

    /**
     * 在以node为根节点的二分搜索树中添加子节点，并返回当前node
     *
     * @param node
     * @param e
     * @return
     */
    private Node add(Node node, E e) {
        if (node == null) {
            size++;
            return new Node(e);
        }

        if (node.e.compareTo(e) < 0) {
            node.right = add(node.right, e);
        } else if (node.e.compareTo(e) > 0) {
            node.left = add(node.left, e);
        }

        return node;
    }

    public boolean contains(E e) {
        return contains(root, e);
    }

    //以node为根的二分搜索树 是否包含e
    private boolean contains(Node node, E e) {
        if (node == null) {
            return false;
        } else if (node.e.compareTo(e) == 0) {
            return true;
        }

        if (node.e.compareTo(e) > 0) {
            return contains(node.left, e);
        } else {
            return contains(node.right, e);
        }
    }

    public void preOrder() {
        preOrder(root);
    }

    //打印当前结点的值
    private void preOrder(Node node) {
        if (node == null) {
            return;
        }

        //操作当前结点
        System.out.println(node.e);

        preOrder(node.left);
        preOrder(node.right);
    }


    /**
     * 非递归的前序遍历
     */
    public void preOrderNR() {
        ArrayStack<Node> stack = new ArrayStack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            Node cur = stack.pop();

            //操作当前结点
            System.out.println(cur.e);

            if (cur.right != null) {
                stack.push(cur.right);
            }

            if (cur.left != null) {
                stack.push(cur.left);
            }

        }
    }

    public void inOrder() {
        inOrder(root);
    }

    private void inOrder(Node node) {
        if (node == null) {
            return;
        }

        inOrder(node.left);
        System.out.println(node.e);
        inOrder(node.right);
    }

    public void postOrder() {
        postOrder(root);
    }

    private void postOrder(Node node) {
        if (node == null) {
            return;
        }

        postOrder(node.left);
        postOrder(node.right);
        System.out.println(node.e);
    }

    public void levelOrder() {
        LinkedListQueue<Node> queue = new LinkedListQueue<>();
        queue.enqueue(root);
        while (!queue.isEmpty()) {
            Node cur = queue.dequeue();
            System.out.println(cur.e);

            if (cur.left != null) {
                queue.enqueue(cur.left);
            }
            if (cur.right != null) {
                queue.enqueue(cur.right);
            }
        }
    }

    /**
     * 搜索二分搜索树中的最小元素
     *
     * @return
     */
    public E minimum() {
        if (root == null)
            throw new IllegalArgumentException("BST is empty");

        return minimum(root).e;
    }

    private Node minimum(Node node) {
        if (node.left == null) {
            return node;
        }

        return minimum(node.left);
    }

    /**
     * 搜索二分搜索树中的最大元素
     *
     * @return
     */
    public E maximum() {
        if (root == null)
            throw new IllegalArgumentException("BST is empty");

        return maximum(root).e;
    }

    private Node maximum(Node node) {
        if (node.right == null) {
            return node;
        }

        return maximum(node.right);
    }

    /**
     * 删除二分搜索树中最小的值
     *
     * @return
     */
    public E removeMin() {
        E minimum = minimum();
        root = removeMin(root);
        return minimum;
    }

    /**
     * 宏观语义： 删除node根节点下 的最小元素，并返回新的根节点
     *
     * @param node
     * @return
     */
    private Node removeMin(Node node) {
        if (node.left == null) {
            Node rightNode = node.right;
            node.right = null;
            size--;
            return rightNode;
        }

        node.left = removeMin(node.left);
        return node;
    }


    /**
     * 删除二分搜索树中最小的值
     *
     * @return
     */
    public E removeMax() {
        E maximum = maximum();
        root = removeMax(root);
        return maximum;
    }

    /**
     * 宏观语义： 删除node根节点下 的最小元素，并返回新的根节点
     *
     * @param node
     * @return
     */
    private Node removeMax(Node node) {
        if (node.right == null) {
            Node leftNode = node.left;
            node.left = null;
            size--;
            return leftNode;
        }

        node.right = removeMax(node.right);
        return node;
    }

    /**
     * 删除元素e
     *
     * @param e
     */
    public void remove(E e) {
        if (root == null) {
            throw new IllegalArgumentException("BST is empty");
        }

        root = remove(root, e);
    }

    //删除以node为根节点中的元素e,
    //并返回新的根节点
    private Node remove(Node node, E e) {
        //这里的基线条件相当于未找到
        if (node == null) {
            return node;
        }

        if (node.e.compareTo(e) > 0) {
            node.left = remove(node.left, e);
            return node;
        } else if (node.e.compareTo(e) < 0) {
            node.right = remove(node.right, e);
            return node;
        } else {//node.e.compareTo(e)==0
            if (node.right == null) {
                Node leftNode = node.left;
                node.left = null;
                size--;
                return leftNode;
            }

//            if (node.left == null) {
//                Node rightNode = node.right;
//                node.right = null;
//                size--;
//                return rightNode;
//            }

            Node successor = minimum(node.right);
            successor.right = removeMin(node.right);
            successor.left = node.left;
            node.left = node.right = null;
            return successor;
        }
    }
}
