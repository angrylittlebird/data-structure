package red_black;

import avl.AVLTree;
import util.FileOperation;

import java.util.ArrayList;

public class RBTree<K extends Comparable<K>, V> {
    private static final boolean RED = true;
    private static final boolean BLACK = false;

    private Node root;
    private int size;

    public RBTree() {
        this.root = null;
        this.size = 0;
    }

    public static void main(String[] args) {

        System.out.println("Pride and Prejudice");

        ArrayList<String> words = new ArrayList<>();
        if (FileOperation.readFile("src/util/pride-and-prejudice.txt", words)) {
            System.out.println("Total words: " + words.size());

            RBTree<String, Integer> map = new RBTree<>();
            for (String word : words) {
                if (map.contains(word))
                    map.set(word, map.get(word) + 1);
                else
                    map.add(word, 1);
            }

            System.out.println("Total different words: " + map.getSize());
            System.out.println("Frequency of PRIDE: " + map.get("pride"));
            System.out.println("Frequency of PREJUDICE: " + map.get("prejudice"));

        }

        System.out.println();
    }

    public void add(K key, V value) {
        root = add(root, key, value);
        root.color = BLACK;// 最终根节点为黑色节点
    }

    public boolean contains(K key) {
        return getNode(root, key) != null;
    }

    public V get(K key) {
        Node node = getNode(root, key);
        return node == null ? null : node.value;
    }

    public void set(K key, V newValue) {
        Node node = getNode(root, key);
        if (node == null) {
            throw new IllegalArgumentException("Set failed.This key does not exist.");
        }
        node.value = newValue;
    }

    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    private Node add(Node node, K key, V value) {
        if (node == null) {
            size++;
            return new Node(key, value);
        }

        if (node.key.compareTo(key) < 0) {
            node.right = add(node.right, key, value);
        } else if (node.key.compareTo(key) > 0) {
            node.left = add(node.left, key, value);
        } else {
            //node.key.compareTo(key) == 0
            node.value = value;
        }

        //统一逻辑：先看是否需要向左旋转，向左旋转之后可能得到需要向右旋转的情况，向右旋转之后可能得到需要向上融合的情况
        if (isRed(node.right) && !isRed(node.left)) {//向左旋转只有可能是这种情况下才会发生,右子节点为红色节点
            node = leftRotate(node);
        }

        if (isRed(node.left) && isRed(node.left.left)) {//向右旋转只有可能是这种情况下才会发生，临时4节点，且红色都在左侧
            node = rightRotate(node);
        }

        if (isRed(node.left) && isRed(node.right)) {//左右都为红色子节点说明是临时4节点，需要向上融合
            flipColors(node);
        }

        return node;
    }

    private Node getNode(Node node, K key) {
        if (node == null) {
            return null;
        }

        if (node.key.compareTo(key) == 0) {
            return node;
        } else if (node.key.compareTo(key) > 0) {
            return getNode(node.left, key);
        } else {
            return getNode(node.right, key);
        }
    }

    private Node minimum(Node node) {
        if (node.left == null) {
            return node;
        }

        return minimum(node.left);
    }


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

    private boolean isRed(Node node) {
        if (node == null) {
            return BLACK;//红黑树定义：空节点为黑色
        }
        return node.color;
    }

    //   node                     x
    //  /   \     左旋转         /  \
    // T1   x   --------->   node   T3
    //     / \              /   \
    //    T2 T3            T1   T2
    private Node leftRotate(Node node) {
        Node x = node.right;

        //左旋转
        node.right = x.left;
        x.left = node;
        //更新颜色
        x.color = node.color;
        node.color = RED;

        return x;
    }

    //     node                   x
    //    /   \     右旋转       /  \
    //   x    T2   ------->   y   node
    //  / \                       /  \
    // y  T1                     T1  T2
    private Node rightRotate(Node node) {
        Node x = node.left;

        //错误示范
//        x.right = node;
//        node.left = x.right;

        node.left = x.right;
        x.right = node;

        x.color = node.color;
        node.color = RED;

        return x;
    }
//    private Node rightRotate(Node node) {
//        Node x = node.left;
//        Node T1 = x.right;
//
//        x.right = node;
//        node.left = T1;
//
//        x.color = node.color;
//        node.color = RED;
//
//        return x;
//    }

    //颜色旋转
    private void flipColors(Node node) {
        node.color = RED;
        node.left.color = BLACK;
        node.right.color = BLACK;
    }

    private class Node {
        private K key;
        private Node left, right;
        private V value;
        private boolean color;

        public Node(K key, V value) {
            this(key, value, null, null);
        }

        public Node(K key, V value, Node left, Node right) {
            this.key = key;
            this.value = value;
            this.left = left;
            this.right = right;
            this.color = RED;
        }

        public Node(K key) {
            this(key, null, null, null);
        }
    }

}
