package heap;

import java.util.*;
import java.util.PriorityQueue;

public class Solution2 {
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

        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>(Comparator.comparingInt(map::get));
        for (Integer key : map.keySet()) {
            if (priorityQueue.size() < k) {
                priorityQueue.add(key);
            }else if(map.get(priorityQueue.peek()) < map.get(key)){
                priorityQueue.remove();
                priorityQueue.add(key);
            }
        }

        List<Integer> res = new ArrayList<>();
        while (!priorityQueue.isEmpty()) {
            res.add(priorityQueue.remove());
        }

        return res;

    }
}
