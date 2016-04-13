package com.example;

/**
 * Created by 啟成 on 2016/3/30.
 */
public class Test {
    public static void main(String[] args) throws Exception {
        int[] nums = {3, 8, 4, 12, 1, 22, 15};
        for (int i = 0; i < nums.length; i++) {
            for (int f = nums.length - 1; f > i; f--) {
                if (nums[i] > nums[f]) {
                    int ti = nums[i];
                    int tf = nums[f];
                    nums[i] = tf;
                    nums[f] = ti;
                }
            }
        }

        System.out.print(nums.toString());
    }
}
