package com.yellow.leetcode.easy;

import java.util.Stack;

/**
 * Class Name: ValidParentheses
 * Description: https://leetcode.com/problems/valid-parentheses/
 *
 * @author yellow
 * @create 2019-05-26 23:48
 *
 * best result: 2 ms	33.4 MB
 */
public class _20_ValidParentheses {

    public static boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == "(".charAt(0) || c == "[".charAt(0) || c == "{".charAt(0)) {
                stack.push(c);
            } else {
                if (stack.empty()) {
                    return false;
                }
                char peek = stack.peek();
                if (c == ")".charAt(0) && peek == "(".charAt(0)
                        || c == "]".charAt(0) && peek == "[".charAt(0)
                        || c == "}".charAt(0) && peek == "{".charAt(0)) {
                    stack.pop();
                } else {
                    return false;
                }
            }
        }
        return stack.empty();
    }

    public static void main(String[] args) {
        System.out.println(isValid("()"));
        System.out.println(isValid("()[]{}"));
        System.out.println(isValid("(]"));
        System.out.println(isValid("([)]"));
        System.out.println(isValid("{[]}"));
    }
}
