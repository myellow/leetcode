package com.yellow.leetcode.medium;

/**
 * Class Name: _3_LongestSubstringWithoutRepeatingCharacters
 * Description: https://leetcode.com/problems/longest-substring-without-repeating-characters/
 *
 * @author yellow
 * @since 2020-07-21 14:24
 *
 * best result: 4 ms	40.7 MB
 */
public class _3_LongestSubstringWithoutRepeatingCharacters {

    public static int lengthOfLongestSubstring(String s) {
        int maxCount = 0;
        // 标准ASCII大小，创建存储字符的数组
        int[] index = new int[128];
        for (int i = 0, j = 0; i < s.length(); i++) {
            // 获取当前字符曾经出现过的下标，否则返回j当前值（j用于记录最近一次出现过重复的字符的下标值）
            j = Math.max(index[s.charAt(i)], j);
            // 计算最大的长度
            maxCount = Math.max(maxCount, i - j + 1);
            // 把当前字符的ASCII值作为下标，存储字符串下标+1
            index[s.charAt(i)] = i + 1;
        }
        return maxCount;
    }

    public static void main(String[] args) {
        System.out.println(lengthOfLongestSubstring("abcabcbb"));
        System.out.println(lengthOfLongestSubstring("bbbbb"));
        System.out.println(lengthOfLongestSubstring("pwwkew"));
    }
}
