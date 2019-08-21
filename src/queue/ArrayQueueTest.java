package queue;

import org.junit.Test;
import stack.ArrayStack;

public class ArrayQueueTest {
    @Test
    public void push() throws Exception {
        ArrayQueue<Integer> arrayQueue = new ArrayQueue<>();
        for (int i = 0; i < 10; i++) {
            arrayQueue.enqueue(i);
        }
        System.out.println(arrayQueue);

        Integer dequeue = arrayQueue.dequeue();
        assert dequeue == 0;
        System.out.println(arrayQueue);

        Integer front = arrayQueue.getFront();
        assert front == 1;
        System.out.println(arrayQueue);


    }
}
