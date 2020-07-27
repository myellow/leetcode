package com.yellow.leetcode.easy;

/**
 * Class Name: ReverseInteger
 * Description: https://leetcode.com/problems/reverse-integer/
 *
 * @author yellow
 * @create 2019-05-23 10:38
 *
 * best result:	1 ms	32.7 MB
 */
public class _7_ReverseInteger {

    public static int reverse(int x) {
        long rev = 0;
        while (x != 0) {
            int pop = x % 10;
            x /= 10;
            rev = rev * 10 + pop;
            if (rev > Integer.MAX_VALUE || rev < Integer.MIN_VALUE) {
                return 0;
            }
        }
        return (int) rev;
    }

    public static void main(String[] args) {
        System.out.println(reverse(123));
        System.out.println(reverse(-123));
        System.out.println(reverse(120));
        System.out.println(reverse(1534236469));
    }
}
