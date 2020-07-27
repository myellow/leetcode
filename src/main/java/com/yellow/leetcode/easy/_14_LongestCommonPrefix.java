package com.yellow.leetcode.easy;

/**
 * Class Name: LongestCommonPrefix
 * Description: https://leetcode.com/problems/longest-common-prefix/
 *
 * @author yellow
 * @create 2019-05-26 20:31
 *
 * best result: 1 ms	35.1 MB
 */
public class _14_LongestCommonPrefix {

    public static String longestCommonPrefix(String[] strs) {
        if (strs.length == 0) {
            return "";
        }
        StringBuilder result = new StringBuilder();
        int i = -1;
        while (strs[0].length() != ++i) {
            char c = strs[0].charAt(i);
            for (int j = 1; j < strs.length; j++) {
                if (strs[j].length() == i || c != strs[j].charAt(i)) {
                    return result.toString();
                }
            }
            result.append(c);
        }
        return result.toString();
    }

    public static void main(String[] args) {
        System.out.println(longestCommonPrefix(new String[]{"flower","flow","flight"}));
        System.out.println(longestCommonPrefix(new String[]{"dog","racecar","car"}));
        System.out.println(longestCommonPrefix(new String[]{"","b"}));
        System.out.println(longestCommonPrefix(new String[]{"aa","a"}));
        System.out.println(longestCommonPrefix(new String[]{}));
    }
}
