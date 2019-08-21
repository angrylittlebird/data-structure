package segment;

public class Solution {
    //一个数组中某段区间的和,除了用线段树之外
    public static void main(String[] args) {
        int[] nums = {-2, 0, 3, -5, 2, -1};


        int sum = getSum(0, 2, nums);
        assert sum == 1;

        assert getSum(0, 5,nums) == -3;
        assert getSum(2, 5, nums) == -1;


    }


    public static int getSum(int l, int r, int[] nums) {
        //arr 第i个元素代表nums数组第i个元素的和
        Integer[] arr = new Integer[nums.length + 1];
        arr[0] = 0;
        for (int i = 1; i < arr.length; i++) {
            arr[i] = arr[i - 1] + nums[i - 1];
        }

        return arr[r+1] - arr[l];
    }
}
