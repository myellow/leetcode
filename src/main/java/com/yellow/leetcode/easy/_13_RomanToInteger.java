package com.yellow.leetcode.easy;

/**
 * Class Name: RomanToInteger
 * Description: https://leetcode.com/problems/roman-to-integer/
 *
 * @author yellow
 * @create 2019-05-26 13:32
 *
 * best result: 3 ms	35.4 MB
 */
public class _13_RomanToInteger {

    /**
     * ("IV", 4);
     * ("IX", 9);
     * ("XL", 40);
     * ("XC", 90);
     * ("CD", 400);
     * ("CM", 900);
     *
     * ("I", 1);
     * ("V", 5);
     * ("X", 10);
     * ("L", 50);
     * ("C", 100);
     * ("D", 500);
     * ("M", 1000);
     */

    public static int romanToInt(String s) {
        int result = 0;
        char[] chars = s.toCharArray();
        int[] ints = new int[chars.length];
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == "I".charAt(0)) {
                ints[i] = 1;
            } else if (chars[i] == "X".charAt(0)) {
                ints[i] = 10;
            } else if (chars[i] == "C".charAt(0)) {
                ints[i] = 100;
            } else if (chars[i] == "V".charAt(0)) {
                ints[i] = 5;
            } else if (chars[i] == "L".charAt(0)) {
                ints[i] = 50;
            } else if (chars[i] == "D".charAt(0)) {
                ints[i] = 500;
            } else if (chars[i] == "M".charAt(0)) {
                ints[i] = 1000;
            }
        }
        for (int i = 0; i < ints.length; i++) {
            if (i+1 != chars.length && ints[i] < ints[i+1]) {
                result += (ints[i+1] - ints[i]);
                i++;
            } else {
                result += ints[i];
            }
        }
        return result;
    }

    public static int romanToInt2(String s) {
        int result = 0;
        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == "I".charAt(0)) {
                boolean mark = i+1 != chars.length;
                if (mark && chars[i+1] == "V".charAt(0)) {
                    result += 4;
                } else if (mark && chars[i+1] == "X".charAt(0)) {
                    result += 9;
                } else {
                    result += 1;
                    continue;
                }
                i++;
            } else if (chars[i] == "X".charAt(0)) {
                boolean mark = i+1 != chars.length;
                if (mark && chars[i+1] == "L".charAt(0)) {
                    result += 40;
                } else if (mark && chars[i+1] == "C".charAt(0)) {
                    result += 90;
                } else {
                    result += 10;
                    continue;
                }
                i++;
            } else if (chars[i] == "C".charAt(0)) {
                boolean mark = i+1 != chars.length;
                if (mark && chars[i+1] == "D".charAt(0)) {
                    result += 400;
                } else if (mark && chars[i+1] == "M".charAt(0)) {
                    result += 900;
                } else {
                    result += 100;
                    continue;
                }
                i++;
            } else if (chars[i] == "V".charAt(0)) {
                result += 5;
            } else if (chars[i] == "L".charAt(0)) {
                result += 50;
            } else if (chars[i] == "D".charAt(0)) {
                result += 500;
            } else if (chars[i] == "M".charAt(0)) {
                result += 1000;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(romanToInt("III"));
        System.out.println(romanToInt("IV"));
        System.out.println(romanToInt("IX"));
        System.out.println(romanToInt("LVIII"));
        System.out.println(romanToInt("MCMXCIV"));
        System.out.println(romanToInt("MCMLXXXIV"));
        System.out.println(romanToInt("MMMCMXCIX"));
    }

}
