package queue;

import java.util.Random;

public class Main {
    public static void main(String[] args) {
        testQueue(new ArrayQueue<>());
        testQueue(new LoopQueue<>());
    }

    public static void testQueue(Queue<Integer> queue){
        int opCount = 100000;
        Random random = new Random();
        long starttime = System.nanoTime() / 100000000;
        for (int i = 0; i < opCount; i++) {
            queue.enqueue(random.nextInt(Integer.MAX_VALUE));
        }

        for (int i = 0; i < opCount; i++) {
            queue.dequeue();
        }
        long endtime = System.nanoTime() / 100000000;

        System.out.println(endtime -  starttime);

    }
}
