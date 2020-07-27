package com.yellow.leetcode.easy;

/**
 * Class Name: PalindromeNumber
 * Description: https://leetcode.com/problems/palindrome-number/
 *
 * @author yellow
 * @create 2019-05-26 11:58
 *
 * best result: 6 ms	35.2 MB
 */
public class _9_PalindromeNumber {

    public static boolean isPalindrome(int x) {
        if (x < 0) {
            return false;
        }
        int rev = 0;
        int temp = x;
        while (temp != 0) {
            int pop = temp % 10;
            rev = rev * 10 + pop;
            temp /= 10;
        }
        return rev == x;
    }

    public static boolean isPalindrome2(int x) {
        if (x < 0) {
            return false;
        }
        int temp = x;
        int length = 0;
        int maxDigital = 1;
        while (temp != 0) {
            temp /= 10;
            if (length > 0) {
                maxDigital *= 10;
            }
            length ++;
        }
        int times = length / 2;
        while (times != 0) {
            int first = x / maxDigital;
            int end = x % 10;
            if (first != end) {
                return false;
            }
            x -= first * maxDigital;
            x /= 10;
            times --;
            maxDigital /= 100;
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(isPalindrome(121));
        System.out.println(isPalindrome(-121));
        System.out.println(isPalindrome(10));
        System.out.println(isPalindrome(1001));
        System.out.println(isPalindrome(9999));
    }
}
