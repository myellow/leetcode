package com.yellow.leetcode.medium;

/**
 * Class Name: _2_AddTwoNumbers
 * Description: https://leetcode.com/problems/add-two-numbers/
 *
 * @author yellow
 * @since 2020-07-21 11:15
 * <p>
 * best result: 1 ms	39.9 MB
 */
public class _2_AddTwoNumbers {

    public static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }


    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode fNode = null;
        ListNode cNode = null;
        int carry = 0;
        while (l1 != null || l2 != null) {
            int current = l1 != null ? l1.val : 0;
            current += l2 != null ? l2.val : 0;
            current += carry;
            if (current > 9) {
                carry = 1;
                current-=10;
            } else {
                carry = 0;
            }
            if (fNode == null) {
                fNode = new ListNode(current);
                cNode = fNode;
            } else {
                cNode.next = new ListNode(current);
                cNode = cNode.next;
            }
            if (l1 != null) {
                l1 = l1.next;
            }
            if (l2 != null) {
                l2 = l2.next;
            }
        }
        if (carry > 0) {
            cNode.next = new ListNode(carry);
        }
        return fNode;
    }

    public static void main(String[] args) {
        ListNode result = addTwoNumbers(createNumber(9, 9), createNumber(1));
        while (result != null) {
            System.out.print(result.val);
            result = result.next;
        }
    }

    public static ListNode createNumber(int... args) {
        if (args == null || args.length == 0) {
            return null;
        }
        ListNode fNode = null;
        ListNode cNode = null;
        for (int arg : args) {
            if (fNode == null) {
                fNode = new ListNode(arg);
                cNode = fNode;
            } else {
                cNode.next = new ListNode(arg);
                cNode = cNode.next;
            }
        }
        return fNode;
    }
}
