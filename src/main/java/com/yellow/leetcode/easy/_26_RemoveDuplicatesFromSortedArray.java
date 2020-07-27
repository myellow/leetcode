package com.yellow.leetcode.easy;

/**
 * Class Name: _26_RemoveDuplicatesFromSortedArray
 * Description: https://leetcode.com/problems/remove-duplicates-from-sorted-array/
 *
 * @author yellow
 * @since 2020-07-17 18:02
 * <p>
 * best result:0 ms	    41.1 MB
 */
public class _26_RemoveDuplicatesFromSortedArray {

    public static int removeDuplicates(int[] nums) {
        if (nums == null) {
            return 0;
        }
        if (nums.length < 1) {
            return nums.length;
        }
        int result = 1;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i - 1] != nums[i]) {
                nums[result] = nums[i];
                result++;
            }
        }
//        IntStream.rangeClosed(0, result - 1)
//                .forEach(i -> System.out.print(nums[i]));
//        System.out.println();
        return result;
    }

    public static void main(String[] args) {
        System.out.println(removeDuplicates(new int[]{1, 1, 2}));
        System.out.println(removeDuplicates(new int[]{0, 0, 1, 1, 1, 2, 2, 3, 3, 4}));
    }
}
