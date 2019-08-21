package recursive;

public class RecursiveSum {

    /**
     * sum的宏观语义：求arr数组中索引从 l 到 最后的和
     * @param arr
     * @param l
     * @return
     */
    public int sum(int[] arr, int l) {
        if(l == arr.length)
            return 0;

        return arr[l] + sum(arr, l + 1);
    }

    public static void main(String[] args) {
        int sum = new RecursiveSum().sum(new int[]{1, 2, 3, 4, 5}, 0);
        System.out.println(sum);
    }
}
