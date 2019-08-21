package queue;

import org.junit.Test;

public class LoopQueueTest {
    @Test
    public void enqueue() throws Exception {
        LoopQueue<Integer> loopQueue = new LoopQueue<>();
        for(int i=0;i<10;i++) {
            loopQueue.enqueue(i);
        }
        System.out.println(loopQueue);

        assert loopQueue.dequeue()==0;
        loopQueue.enqueue(10);
        assert loopQueue.getCapacity() == 10;
        loopQueue.enqueue(11);
        assert loopQueue.getCapacity() == 20;
        assert loopQueue.getFront() ==1;
        System.out.println(loopQueue);

    }

    @Test
    public void dequeue() throws Exception {
    }


}
