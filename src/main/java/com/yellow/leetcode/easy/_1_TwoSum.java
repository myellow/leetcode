package com.yellow.leetcode.easy;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Class Name: TwoSum
 * Description: https://leetcode.com/problems/two-sum/
 *
 * @author yellow
 * @create 2019-05-23 10:17
 *
 * best result: 1 ms	37.4 MB
 */
public class _1_TwoSum {

    public static int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> result = new HashMap<>(nums.length);
        for (int i = 0; i < nums.length; i++) {
            // 先判断当前的数字有没有其它数字需要组合，没有则把数字放入map
            if (!result.containsKey(nums[i])) {
                // 当前数字需要target-nums[i]的数字，把这个所需的数字作为key存入Map，value存放当前数字的index
                result.put(target - nums[i], i);
            } else {
                // 匹配上了组合，返回这两个数的下标
                return new int[]{result.get(nums[i]), i};
            }
        }
        return null;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(twoSum(new int[]{2, 7, 11, 12}, 9)));
        System.out.println(Arrays.toString(twoSum(new int[]{7, 3, 11, 15}, 18)));
        System.out.println(Arrays.toString(twoSum(new int[]{8, 4, 5, 20}, 25)));
    }
}
