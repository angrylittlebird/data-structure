package trie;

import java.util.TreeMap;

public class Trie {
    private int size;
    private Node root;

    public Trie() {
        this.size = 0;
        root = new Node();
    }

    public int getSize() {
        return size;
    }

    public void add(String word) {
        Node cur = root;

        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (cur.next.get(c) == null) {
                cur.next.put(c, new Node());
            }
            cur = cur.next.get(c);
        }

        if (!cur.isWord) {
            cur.isWord = true;
            size++;
        }
    }

    public Boolean contains(String word) {
        Node cur = root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (cur.next.get(c) == null) {
                return false;
            }
            cur = cur.next.get(c);
        }

        return cur.isWord;
    }


    public Boolean match(String word) {
        return match(root, word, 0);
    }

    private Boolean match(Node node, String word, int index) {
        if (index == word.length()) {
            return node.isWord;
        }

        char c = word.charAt(index);
        if ('.' != c) {
            if (node.next.get(c) == null) {
                return false;
            } else {
                return match(node.next.get(c), word, index + 1);
            }
        } else {
            for (char key : node.next.keySet()) {
                if (match(node.next.get(key), word, index + 1)) {
                    return true;
                }
            }
            return false;
        }
    }

    public Boolean isPrefix(String prefix) {
        Node cur = root;
        for (int i = 0; i < prefix.length(); i++) {
            char c = prefix.charAt(i);
            if (cur.next.get(c) == null) {
                return false;
            }
            cur = cur.next.get(c);

        }

        return true;
    }

    private class Node {
        public Boolean isWord;
        public TreeMap<Character, Node> next;

        public Node(Boolean isWord) {
            this.isWord = isWord;
            next = new TreeMap<>();
        }

        public Node() {
            this(false);
        }
    }
}
