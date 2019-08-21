package linkedlist;

import queue.Queue;

//记得维护两个指针以及size
public class LinkedListQueue<E> implements Queue<E> {
    private class Node{
        public E e;
        public Node next;

        public Node(E e, Node next) {
            this.e = e;
            this.next = next;
        }

        public Node(E e) {
            this(e, null);
        }

        public Node() {
            this(null, null);
        }

        @Override
        public String toString(){
            return e.toString();
        }
    }

    private Node head,tail;
    private int size;

    public LinkedListQueue() {
        head = null;
        tail = null;
        size = 0;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size==0;
    }

    /**
     * 考虑到队尾删除元素的话，队尾指针tail不能移动到前一个元素
     * @param e 在队尾入队
     */
    @Override
    public void enqueue(E e) {
        if (tail == null) {
            tail = new Node(e);
            head = tail;
        }else {
            tail.next = new Node(e);
            tail = tail.next;
        }

        size++;
    }

    /**
     * 在队首删除元素
     * @return 返回被删除的元素
     */
    @Override
    public E dequeue() {
        if (head == null) {
            throw new IllegalArgumentException("dequeue failed.Cannot dequeue from an empty");
        }

        Node retNode = head;
        head = head.next;
        retNode.next = null;
        size--;

        if (head == null) {
            tail = null;
        }
        return retNode.e;
    }

    @Override
    public E getFront() {
        if (head == null) {
            new IllegalArgumentException("Queue is empty");
        }

        return head.e;
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("LinkedListQueue: front");
        Node cur = head;
        while (cur != null) {
            sb.append(cur.e);
            sb.append("->");
            cur = cur.next;
        }
        sb.append("NULL");
        return sb.toString();
    }
}
