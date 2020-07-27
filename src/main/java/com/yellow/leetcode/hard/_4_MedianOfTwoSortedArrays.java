package com.yellow.leetcode.hard;

/**
 * Class Name: _4_MedianOfTwoSortedArrays
 * Description: https://leetcode.com/problems/median-of-two-sorted-arrays/
 *
 * @author yellow
 * @since 2020-07-21 22:19
 * <p>
 * best result: 2 ms	40.6 MB
 */
public class _4_MedianOfTwoSortedArrays {

    /**
     * O(m+n)解法，比较复杂
     */
    public static double findMedianSortedArrays_1(int[] nums1, int[] nums2) {
        int length = nums1.length + nums2.length;
        int index1 = 0, index2 = 0;
        int median1, median2;
        if (nums1.length == 0) {
            median1 = median2 = nums2[0];
            index2++;
        } else if (nums2.length == 0) {
            median1 = median2 = nums1[0];
            index1++;
        } else if (nums1[0] < nums2[0]) {
            median1 = median2 = nums1[0];
            index1++;
        } else {
            median1 = median2 = nums2[0];
            index2++;
        }
        for (int i = 0; i < length / 2; i++) {
            median1 = median2;
            if (index1 == nums1.length) {
                // nums1已经遍历完，直接出队nums2
                median2 = nums2[index2++];
            } else if (index2 == nums2.length) {
                // nums2已经遍历完，直接出队nums1
                median2 = nums1[index1++];
            } else {
                // 判断谁比较小，则出队那一个
                if (nums1[index1] < nums2[index2]) {
                    median2 = nums1[index1++];
                } else {
                    median2 = nums2[index2++];
                }
            }
        }
        if (length % 2 == 0) {
            // 偶数长度
            return (median1 + median2) / 2d;
        } else {
            // 奇数长度
            return median2;
        }
    }

    public static double findMedianSortedArrays_2(int[] nums1, int[] nums2) {
        int n = nums1.length;
        int m = nums2.length;
        int left = (n + m + 1) / 2;
        int right = (n + m + 2) / 2;
        //将偶数和奇数的情况合并，如果是奇数，会求两次同样的 k 。
        return (getKth(nums1, 0, n - 1, nums2, 0, m - 1, left) + getKth(nums1, 0, n - 1, nums2, 0, m - 1, right)) * 0.5;
    }

    private static int getKth(int[] nums1, int start1, int end1, int[] nums2, int start2, int end2, int k) {
        int len1 = end1 - start1 + 1;
        int len2 = end2 - start2 + 1;
        //让 len1 的长度小于 len2，这样就能保证如果有数组空了，一定是 len1
        if (len1 > len2) return getKth(nums2, start2, end2, nums1, start1, end1, k);
        if (len1 == 0) return nums2[start2 + k - 1];

        if (k == 1) return Math.min(nums1[start1], nums2[start2]);

        // 判断是否超界了
        int i = start1 + Math.min(len1, k / 2) - 1;
        int j = start2 + Math.min(len2, k / 2) - 1;

        if (nums1[i] > nums2[j]) {
            return getKth(nums1, start1, end1, nums2, j + 1, end2, k - (j - start2 + 1));
        } else {
            return getKth(nums1, i + 1, end1, nums2, start2, end2, k - (i - start1 + 1));
        }
    }

    private static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int m = nums1.length;
        int n = nums2.length;
        if (m > n) {
            return findMedianSortedArrays(nums2, nums1); // 保证 m <= n
        }
        int iMin = 0, iMax = m;
        while (iMin <= iMax) {
            int i = (iMin + iMax) / 2;
            int j = (m + n + 1) / 2 - i;
            if (j != 0 && i != m && nums2[j - 1] > nums1[i]) { // i 需要增大
                iMin = i + 1;
            } else if (i != 0 && j != n && nums1[i - 1] > nums2[j]) { // i 需要减小
                iMax = i - 1;
            } else { // 达到要求，并且将边界条件列出来单独考虑
                int maxLeft = 0;
                if (i == 0) {
                    maxLeft = nums2[j - 1];
                } else if (j == 0) {
                    maxLeft = nums1[i - 1];
                } else {
                    maxLeft = Math.max(nums1[i - 1], nums2[j - 1]);
                }
                if ((m + n) % 2 == 1) {
                    return maxLeft;
                } // 奇数的话不需要考虑右半部分

                int minRight = 0;
                if (i == m) {
                    minRight = nums2[j];
                } else if (j == n) {
                    minRight = nums1[i];
                } else {
                    minRight = Math.min(nums2[j], nums1[i]);
                }

                return (maxLeft + minRight) / 2.0; //如果是偶数的话返回结果
            }
        }
        return 0.0;
    }

    public static void main(String[] args) {
//        System.out.println(findMedianSortedArrays(new int[]{1, 3}, new int[]{2}));
//        System.out.println(findMedianSortedArrays(new int[]{1, 3}, new int[]{2, 4}));
        System.out.println(findMedianSortedArrays(new int[]{1, 2}, new int[]{3, 4}));
//        System.out.println(findMedianSortedArrays(new int[]{1, 3, 5}, new int[]{2, 4}));
//        System.out.println(findMedianSortedArrays(new int[]{}, new int[]{1}));
//        System.out.println(findMedianSortedArrays(new int[]{1,2,3,4,5,6,7,8,9,10}, new int[]{1,3,4,9}));
//        System.out.println(findMedianSortedArrays(new int[]{2,4,13,14,15,16,17}, new int[]{1,5,8,10,11,12,12,15}));
    }

}