package heap;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class IndexMaxHeapTest {
    @Test
    public void test() {
        int N = 1000;
        Integer[] arr = new Integer[N];
        for (int i = 0; i < N; i++) {
            arr[i] = (int) (Math.random() * N);
        }

        IndexMaxHeap<Integer> indexMaxHeap = new IndexMaxHeap<>(arr);

        for (int i = 0; i < N; i++) {
            indexMaxHeap.change(i, ((int) (Math.random() * N)));
        }

        int random = (int) (Math.random() * N);
        indexMaxHeap.add(random);
        indexMaxHeap.change(random,random);


        List<Integer> list = new ArrayList<>();
        while (indexMaxHeap.size() > 0){
            list.add(indexMaxHeap.extractMax());
        }
        assert indexMaxHeap.size() == 0;
        assert indexMaxHeap.isEmpty();

        for (int i = 1; i < list.size(); i++) {
            assert list.get(i - 1) >= list.get(i);
        }
    }
}
