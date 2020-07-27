package com.yellow.leetcode.medium;

import java.util.HashMap;
import java.util.Map;

/**
 * Class Name: _146_LRUCache
 * Description: https://leetcode.com/problems/lru-cache/
 *
 * @author yellow
 * @since 2020-07-18 13:45
 *
 * best result: 16 ms	47.5 MB
 */
public class _146_LRUCache {

    // how to design key & index???
    static class StupidLRUCache {

        int time = 0;
        private final int[] times;
        private final int[] values;
        private final Integer[] keys;

        public StupidLRUCache(int capacity) {
            this.times = new int[capacity];
            this.values = new int[capacity];
            this.keys = new Integer[capacity];
        }

        public int get(int key) {
            for (int i = 0; i < keys.length; i++) {
                if (keys[i] != null && keys[i] == key) {
                    times[i] = ++time;
                    return values[i];
                }
            }
            return -1;
        }

        public void put(int key, int value) {
            int index = 0;
            // 1 0 2
            for (int i = 0; i < times.length; i++) {
                if (keys[i] == null || keys[i] == key) {
                    index = i;
                    break;
                }
                if (times[index] > times[i]) {
                    index = i;
                }
            }
            keys[index] = key;
            values[index] = value;
            times[index] = ++time;
        }

    }

    public static class LRUCache {
        static class DLinkedNode {
            int key;
            int value;
            DLinkedNode prev;
            DLinkedNode next;

            public DLinkedNode() {
            }

            public DLinkedNode(int key, int value) {
                this.key = key;
                this.value = value;
            }
        }

        Map<Integer, DLinkedNode> map;
        DLinkedNode head, tail;
        int capacity;

        public LRUCache(int capacity) {
            map = new HashMap<>(capacity);
            this.capacity = capacity;
            head = null;
            tail = null;
        }

        public int get(int key) {
            if (map.containsKey(key)) {
                DLinkedNode node = map.get(key);
                move2Tail(node);
                return node.value;
            }
            return -1;
        }

        public void put(int key, int value) {
            if (map.containsKey(key)) {
                // 存在key，则覆盖value并把node移动到末端
                DLinkedNode node = map.get(key);
                node.value = value;
                move2Tail(node);
            } else {
                DLinkedNode node = new DLinkedNode(key, value);
                if (map.size() == capacity) {
                    map.remove(head.key);
                    if (head.next != null) {
                        head.next.prev = null;
                        head = head.next;
                    }
                }
                if (head == null) {
                    head = node;
                } else {
                    tail.next = node;
                    node.prev = tail;
                }
                map.put(key, node);
                tail = node;
            }
        }

        private void move2Tail(DLinkedNode node) {
            if (node.next == null) {
                // 已经在末端
                return;
            } else if (node.prev == null) {
                // 处于首端
                node.next.prev = null;
                head = node.next;
            } else {
                // 在中部
                node.next.prev = node.prev;
                node.prev.next = node.next;
            }
            node.next = null;
            node.prev = tail;
            tail.next = node;
            tail = node;
        }
    }

    public static void main(String[] args) {
//        test(2, new int[]{1, 1}, new int[]{2, 2}, new int[]{1}, new int[]{3, 3}, new int[]{2}, new int[]{4, 4}, new int[]{1}, new int[]{3}, new int[]{4});
//        test(2, new int[]{2, 1}, new int[]{2, 2}, new int[]{2}, new int[]{1, 1}, new int[]{4, 1}, new int[]{2});
//        test(2, new int[]{2}, new int[]{2, 6}, new int[]{1}, new int[]{1, 5}, new int[]{1, 2}, new int[]{1}, new int[]{2});
//        test(2, new int[]{2, 1}, new int[]{3, 2}, new int[]{3}, new int[]{2}, new int[]{4, 3}, new int[]{2}, new int[]{3}, new int[]{4});
        test(10, new int[]{10, 13}, new int[]{3, 17}, new int[]{6, 11}, new int[]{10, 5}, new int[]{9, 10}, new int[]{13}, new int[]{2, 19}, new int[]{2}, new int[]{3}, new int[]{5, 25}, new int[]{8}, new int[]{9, 22}, new int[]{5, 5}, new int[]{1, 30}, new int[]{11}, new int[]{9, 12}, new int[]{7}, new int[]{5}, new int[]{8}, new int[]{9}, new int[]{4, 30}, new int[]{9, 3}, new int[]{9}, new int[]{10}, new int[]{10}, new int[]{6, 14}, new int[]{3, 1}, new int[]{3}, new int[]{10, 11}, new int[]{8}, new int[]{2, 14}, new int[]{1}, new int[]{5}, new int[]{4}, new int[]{11, 4}, new int[]{12, 24}, new int[]{5, 18}, new int[]{13}, new int[]{7, 23}, new int[]{8}, new int[]{12}, new int[]{3, 27}, new int[]{2, 12}, new int[]{5}, new int[]{2, 9}, new int[]{13, 4}, new int[]{8, 18}, new int[]{1, 7}, new int[]{6}, new int[]{9, 29}, new int[]{8, 21}, new int[]{5}, new int[]{6, 30}, new int[]{1, 12}, new int[]{10}, new int[]{4, 15}, new int[]{7, 22}, new int[]{11, 26}, new int[]{8, 17}, new int[]{9, 29}, new int[]{5}, new int[]{3, 4}, new int[]{11, 30}, new int[]{12}, new int[]{4, 29}, new int[]{3}, new int[]{9}, new int[]{6}, new int[]{3, 4}, new int[]{1}, new int[]{10}, new int[]{3, 29}, new int[]{10, 28}, new int[]{1, 20}, new int[]{11, 13}, new int[]{3}, new int[]{3, 12}, new int[]{3, 8}, new int[]{10, 9}, new int[]{3, 26}, new int[]{8}, new int[]{7}, new int[]{5}, new int[]{13, 17}, new int[]{2, 27}, new int[]{11, 15}, new int[]{12}, new int[]{9, 19}, new int[]{2, 15}, new int[]{3, 16}, new int[]{1}, new int[]{12, 17}, new int[]{9, 1}, new int[]{6, 19}, new int[]{4}, new int[]{5}, new int[]{5}, new int[]{8, 1}, new int[]{11, 7}, new int[]{5, 2}, new int[]{9, 28}, new int[]{1}, new int[]{2, 2}, new int[]{7, 4}, new int[]{4, 22}, new int[]{7, 24}, new int[]{9, 26}, new int[]{13, 28}, new int[]{11, 26});
    }

    public static void testStupidCache(int capacity, int[]... args) {
        System.out.println("***** TEST *****");
        StupidLRUCache cache = new StupidLRUCache(capacity);
        for (int[] arg : args) {
            if (arg.length == 1) {
                System.out.println(cache.get(arg[0]));
            } else if (arg.length == 2) {
                cache.put(arg[0], arg[1]);
            }
        }
    }

    public static void test(int capacity, int[]... args) {
        System.out.println("***** TEST *****");
        LRUCache cache = new LRUCache(capacity);
        StupidLRUCache sCache = new StupidLRUCache(capacity);
        for (int[] arg : args) {
            if (arg.length == 1) {
                System.out.print(cache.get(arg[0]));
                System.out.print(", ");
                System.out.println(sCache.get(arg[0]));
            } else if (arg.length == 2) {
                cache.put(arg[0], arg[1]);
                sCache.put(arg[0], arg[1]);
            }
        }
    }
}
