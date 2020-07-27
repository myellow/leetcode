package com.yellow.leetcode.easy;

/**
 * Class Name: _21_MergeTwoSortedLists
 * Description: https://leetcode.com/problems/merge-two-sorted-lists/
 *
 * @author yellow
 * @since 2020-07-17 15:26
 *
 * best result: 0 ms	39.1 MB
 */
public class _21_MergeTwoSortedLists {

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

    /**
     * 1 -> 2 -> 4
     * 1 -> 3 -> 4
     *
     * 1 -> 1 -> 2 -> 4
     * 3 -> 4
     */
    public static ListNode mergeTwoSortedLists(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        } else if (l2 == null) {
            return l1;
        }
        ListNode first, main, compare;
        if (l2.val < l1.val) {
            main = l2;
            compare = l1;
        } else {
            main = l1;
            compare = l2;
        }
        first = main;
        while (compare != null) {
            if (main.next == null) {
                main.next = compare;
                break;
            }
            if (main.val <= compare.val && compare.val < main.next.val) {
                ListNode mainNext = main.next;
                ListNode compareNext = compare.next;
                main.next = compare;
                compare.next = mainNext;
                compare = compareNext;
            } else {
                main = main.next;
            }
        }
        return first;
    }

    public static ListNode buildList(int... args) {
        ListNode first = new ListNode(args[0]);
        ListNode current = first;
        for (int i = 1; i < args.length; i++) {
            current.next = new ListNode(args[i]);
            current = current.next;
        }
        return first;
    }

    public static void main(String[] args) {
        ListNode result = mergeTwoSortedLists(
                buildList(-10,-9,-6,-4,1,9,9),
                buildList(-5,-3,0,7,8,8));
        while (result != null) {
            System.out.println(result.val);
            result = result.next;
        }
    }

}
