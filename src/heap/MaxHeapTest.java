package heap;

import org.junit.Test;

import java.util.Arrays;
import java.util.Random;
import java.util.stream.Collectors;

public class MaxHeapTest {
    @Test
    public void add() {
        int n = 1000;

        MaxHeap<Integer> heap = new MaxHeap<>();
        Random random = new Random();
        for (int i = 0; i < n; i++) {
            heap.add(random.nextInt(1000));
        }
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = heap.extractMax();
        }

        for (int i = 1; i < n; i++) {
            System.out.println(arr[i]);
            if (arr[i - 1] < arr[i]) {
                assert false;
            }
        }

    }

    //这里不知为什么把testHeapify和testHeapify2两个方法放在一块计算时间
    //第二个测试的例子总是比第一个例子时间少。
    @Test
    public void testHeapify() {
        Integer[] arr = new Integer[10000000];
        Random random = new Random();
        for (int i = 0; i < 10000000; i++) {
            arr[i] = i;
        }

        double withHeapify = testHeap(arr, true);
        System.out.println("withHeapify:" + withHeapify);

    }

    @Test
    public void testHeapify2() {
        Integer[] arr = new Integer[10000000];
        for (int i = 0; i < 10000000; i++) {
            arr[i] = i;
        }

        double withoutHeapify = testHeap(arr, false);
        System.out.println("withoutHeapify:" + withoutHeapify);
    }

    private static double testHeap(Integer[] arr, Boolean isHeapify) {
        long startTime = System.currentTimeMillis();

        MaxHeap<Integer> maxHeap;
        if (isHeapify) {
            maxHeap = new MaxHeap<>(arr);
        } else {
            maxHeap = new MaxHeap<>();
            for (int i = 0; i < arr.length; i++) {
                maxHeap.add(arr[i]);
            }
        }


        int[] arr1 = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            arr1[i] = maxHeap.extractMax();
        }

        for (int i = 1; i < arr.length; i++) {
            if (arr1[i - 1] < arr1[i]) {
                assert false;
            }
        }


        long endTime = System.currentTimeMillis();

        return (endTime - startTime) / 1000.0;
    }
}
