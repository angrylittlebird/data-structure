package linkedlist;

public class LinkedList<E> {
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

    private Node dummyHead;
    private int size;

    public LinkedList() {
        dummyHead = new Node();
        size = 0;
    }

    public LinkedList(E[] arr) {
        dummyHead = new Node(arr[0]);
        Node prev = dummyHead;
        for (int i = 0; i < arr.length; i++) {
            prev.next = new Node(arr[i]);
            prev = prev.next;
        }
    }


    public int getSize(){
        return size;
    }

    public boolean isEmpty(){
        return size == 0;
    }


    /**
     *
     * @param index 在索引index位置添加元素e
     * @param e
     */
    public void add(int index,E e){
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("add failed.illegal index");
        }

        Node prev = dummyHead;
        for (int i = 0; i < index; i++) {
            prev = prev.next;
        }

        prev.next = new Node(e, prev.next);

        size++;
    }

    /**
     *
     * @param e 在链表首位添加元素e
     */
    public void addFirst(E e){
//        Node node = new Node(e);
//        node.next = head;
//        head = node;

//        head = new Node(e, head);
//        size++;
        add(0, e);
    }


    /**
     *
     * @param e 在末尾处添加元素e
     */
    public void addLast(E e){
        add(size,e);
    }



    /**
     *
     * @param index 获取索引为index的元素
     * @return
     */
    public E get(int index){
        if(index<0 || index>=size)
            throw new IllegalArgumentException("get failed.illegal index");

        Node cur = dummyHead.next;
        for (int i = 0; i < index; i++) {
            cur = cur.next;
        }

        return cur.e;
    }

    public E getFirst(){
        return get(0);
    }

    public E getLast(){
        return get(size-1);
    }

    /**
     *
     * @param index 设置链表中索引为index的元素值为e
     * @param e
     */
    public void set(int index, E e) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("set failed.illegal index");
        }

        Node cur = dummyHead.next;
        for (int i = 0; i <index ; i++) {
            cur = cur.next;
        }
        cur.e = e;
    }

    /**
     *
     * @param e 判断链表中是否包含元素e
     * @return
     */
    public boolean contains(E e){
        Node cur = dummyHead.next;
        while (cur != null) {
            if (cur.e.equals(e)) {
                return true;
            }
            cur = cur.next;
        }
        return false;
    }

    public E remove(int index){
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("remove failed.illegal index");
        }

        Node pre = dummyHead;
        for (int i = 0; i < index; i++) {
            pre = pre.next;
        }

        Node removeNode = pre.next;

        //左边是引用变量名称，右边是对象
        pre.next = removeNode.next;
        removeNode.next = null;

        size--;
        return removeNode.e;
    }

    public E removeFirst(){
        return remove(0);
    }

    public E removeLast(){
        return remove(size - 1);
    }


    @Override
    public String toString(){
        StringBuffer sb = new StringBuffer();
        sb.append("head:");
        Node cur = dummyHead.next;
        while (cur != null) {
            sb.append(cur);
            sb.append("->");
            cur = cur.next;
        }
        sb.append("NULL");
        return sb.toString();
    }
}
