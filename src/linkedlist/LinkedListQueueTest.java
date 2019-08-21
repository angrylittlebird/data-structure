package linkedlist;


import org.junit.Test;

public class LinkedListQueueTest {

    @Test
    public void enqueue() {
        LinkedListQueue<Integer> queue = new LinkedListQueue<>();
        for (int i = 0; i < 10; i++) {
            queue.enqueue(i);
        }
        System.out.println(queue);
        assert queue.getSize() == 10;
        assert queue.dequeue() == 0;
        assert queue.getSize() == 9;
        assert queue.getFront() == 1;
        queue.enqueue(10);
        System.out.println(queue);
    }
}
