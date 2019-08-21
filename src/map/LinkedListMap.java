package map;

public class LinkedListMap<K,V> implements Map<K,V> {
    private class Node{
        public K key;
        public V value;
        public Node next;

        public Node(K key, V value, Node next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }

        public Node(K key) {
            this(key, null, null);
        }

        public Node() {
            this(null, null, null);
        }
    }

    private int size;
    private Node dummyhead;

    public LinkedListMap() {
        dummyhead = new Node();
        size = 0;
    }

    private Node getNode(K key){
        Node cur = dummyhead.next;
        while (cur != null) {
            if (cur.key.equals(key)) {
                return cur;
            }
            cur = cur.next;
        }

        return null;
    }


    @Override
    public void add(K key, V value) {
        Node node = getNode(key);
        if (node == null) {
            dummyhead.next = new Node(key, value, dummyhead.next);
            size++;
        }else {
            node.value = value;
        }
    }

    @Override
    public V remove(K key) {
        Node prev = dummyhead;
        while (prev.next != null) {
            if (prev.next.key.equals(key)) {
                Node delNode = prev.next;
                prev.next = delNode.next;
                delNode.next = null;
                size--;
                return delNode.value;
            }
            prev = prev.next;
        }

        return null;
    }

    @Override
    public boolean contains(K key) {
        return getNode(key) != null;
    }

    @Override
    public V get(K key) {
        return getNode(key) == null?null: getNode(key).value;
    }

    @Override
    public void set(K key, V newValue) {
        Node node = getNode(key);
        if (node == null) {
            throw new IllegalArgumentException("key doesn't exist");
        }else{
            node.value = newValue;
        }
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }
}
