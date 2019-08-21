package heap;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solution {
    public static void main(String[] args) {
        List<Integer> maxFrequenceChar = getMaxFrequenceChar(new Integer[]{0, 0, 1, 1, 1, 1, 2, 2, 2, 3, 4, 5, 6}, 3);
        System.out.println(maxFrequenceChar);
    }

    public static List<Integer> getMaxFrequenceChar(Integer[] arr, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        for (Integer anArr : arr) {
            if (map.containsKey(anArr)) {
                map.put(anArr, map.get(anArr) + 1);
            } else {
                map.put(anArr, 1);
            }
        }

        MaxHeap<Freq> maxHeap = new MaxHeap<>();
        for (Integer key : map.keySet()) {
            if (maxHeap.size() < k) {
                maxHeap.add(new Freq(key, map.get(key)));
            } else if (maxHeap.findMax().freq < map.get(key)) {
                maxHeap.replace(new Freq(key, map.get(key)));
            }
        }

        List<Integer> res = new ArrayList<>();
        while (!maxHeap.isEmpty()) {
            res.add(maxHeap.extractMax().name);
        }

        return res;

    }

    private static class Freq implements Comparable<Freq> {
        public int freq;
        public int name;

        public Freq(int name, int freq) {
            this.name = name;
            this.freq = freq;
        }

        @Override
        public int compareTo(Freq o) {
//            if (freq < o.freq) {
//                return 1;
//            } else if (freq > o.freq) {
//                return -1;
//            } else {
//                return 0;
//            }

            return o.freq - freq;
        }
    }
}
