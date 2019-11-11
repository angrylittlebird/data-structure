package hashtable;

import java.util.HashMap;

//给定一个字符串，返回第一个不重复字符的索引
public class LeetCode387 {
    public static void main(String[] args) {
        LeetCode387 leetcode = new LeetCode387();
        int aabbcc = leetcode.getFirstUniqueCharIndex2("aabbcc");
        System.out.println(aabbcc);
        int aabccdee = leetcode.getFirstUniqueCharIndex2("aabccdee");
        System.out.println(aabccdee);
    }

    public int getFirstUniqueCharIndex(String str) {
        if (str == null) return -1;

        HashMap<Character, Integer> freq = new HashMap<>();
        for (char c : str.toCharArray()) {
            if (freq.containsKey(c)) {
                Integer frequency = freq.get(c);
                freq.put(c, ++frequency);
            } else {
                freq.put(c, 1);
            }
        }
        for (int i = 0; i < str.length(); i++) {
            if (freq.get(str.charAt(i)) == 1) {
                return i;
            }
        }
        return -1;
    }

    public int getFirstUniqueCharIndex2(String str) {
        if (str == null) return -1;

        //假设只有26个小写字母
        int[] freq = new int[26];//这里的freq其实可以看成hash表，字符和数字之间形成一个映射的关系，数字即索引。每个字符和一个索引相对应。
        //HashMap中的key，最终也会换算成一个hash值，通过hash值来找到table中的位置，时间复杂度为O(1)，和数组中通过索引查找是一个道理。
        //hash函数:把“键”转成“索引”。

        for (char c : str.toCharArray()) {
            freq[c - 'a']++;
        }

        for (int i = 0; i < str.length(); i++) {
            if (freq[str.charAt(i) - 'a'] == 1) {
                return i;
            }
        }
        return -1;
    }
}
