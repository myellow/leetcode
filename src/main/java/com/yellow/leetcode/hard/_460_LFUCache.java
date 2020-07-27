package com.yellow.leetcode.hard;

import java.util.HashMap;
import java.util.Map;

/**
 * Class Name: _460_LFUCache
 * Description: https://leetcode.com/problems/lfu-cache/
 *
 * @author yellow
 * @since 2020-07-19 23:05
 * <p>
 * best result: 14 ms	47.4 MB
 */
public class _460_LFUCache {

    public static class SingleDLinkLFUCache {
        static class DLinkedNode {
            int key;
            int value;
            int count;
            DLinkedNode prev;
            DLinkedNode next;

            public DLinkedNode() {
            }

            public DLinkedNode(int key, int value) {
                this.key = key;
                this.value = value;
                this.count = 0;
            }

        }

        Map<Integer, DLinkedNode> map;
        DLinkedNode head, tail;
        int capacity;

        public SingleDLinkLFUCache(int capacity) {
            map = new HashMap<>(capacity);
            this.capacity = capacity;
            head = null;
            tail = null;
        }

        public int get(int key) {
            if (map.containsKey(key)) {
                DLinkedNode node = map.get(key);
                node.count++;
                adjust(node);
                return node.value;
            }
            return -1;
        }

        public void put(int key, int value) {
            if (map.containsKey(key)) {
                // 存在key，则覆盖value并把node移动到末端
                DLinkedNode node = map.get(key);
                node.value = value;
                node.count++;
                adjust(node);
            } else {
                DLinkedNode node = new DLinkedNode(key, value);
                if (capacity == 0) {
                    // 应对沙雕case
                    return;
                } else if (map.size() == capacity) {
                    map.remove(head.key);
                    if (head.next != null) {
                        head.next.prev = node;
                        node.next = head.next;
                        head = node;
                        adjust(node);
                    }
                } else if (head == null) {
                    head = node;
                    tail = node;
                } else {
                    node.next = head;
                    head.prev = node;
                    head = node;
                    adjust(node);
                }
                map.put(key, node);
            }
        }

        private void adjust(DLinkedNode node) {
            if (node.next == null) {
                // 已经在末端
                return;
            }
            DLinkedNode next = node.next;
            while (next.next != null) {
                if (next.count == next.next.count) {
                    next = next.next;
                } else {
                    break;
                }
            }
            if (next.count <= node.count) {
                // 交换两个node
                if (node.prev != null) {
                    // node.prev.next
                    node.prev.next = node.next;
                }
                if (next.next != null) {
                    // next.next.prev
                    next.next.prev = node;
                }
                // node.next.prev
                node.next.prev = node.prev;
                if (node.next.prev == null) {
                    head = node.next;
                }
                // node.prev
                node.prev = next;
                // node.next
                node.next = next.next;
                // next.next
                next.next = node;
            }
            // 判断是否已经是head或者tail
            if (node.next == null) {
                tail = node;
            }
        }

    }

    public static class LFUCache {
        static class DLinkedNode {
            int key;
            int value;
            int count;
            DLinkedNode prev;
            DLinkedNode next;

            public DLinkedNode() {
            }

            public DLinkedNode(int key, int value) {
                this.key = key;
                this.value = value;
                this.count = 0;
            }

        }

        static class DLinkedList {
            DLinkedNode head;
            DLinkedNode tail;

            public DLinkedList() {
                // 创建虚拟头尾node
                this.head = new DLinkedNode();
                this.tail = new DLinkedNode();
                this.head.next = tail;
                this.tail.prev = head;
            }

            public DLinkedList(DLinkedNode node) {
                this();
                head.next = node;
                tail.prev = node;
                node.prev = head;
                node.next = tail;
            }
        }

        Map<Integer, DLinkedNode> map;
        Map<Integer, DLinkedList> countMap;
        int minCount;
        int capacity;

        public LFUCache(int capacity) {
            this.map = new HashMap<>(capacity);
            this.countMap = new HashMap<>(capacity);
            this.minCount = 1;
            this.capacity = capacity;
        }

        public int get(int key) {
            if (map.containsKey(key)) {
                DLinkedNode node = map.get(key);
                adjust(node);
                return node.value;
            }
            return -1;
        }

        public void put(int key, int value) {
            if (capacity == 0) {
                // 应对沙雕case
                return;
            }
            DLinkedNode node;
            if (map.containsKey(key)) {
                // 存在key，则覆盖value并把node移动到末端
                node = map.get(key);
                node.value = value;
            } else {
                if (capacity == map.size()) {
                    DLinkedNode head = countMap.get(minCount).head;
                    DLinkedNode removeNode = head.next;
                    if (removeNode.next.next == null) {
                        // 假如当前节点移除后已经没有数据，则移除当前countMap
                        countMap.remove(removeNode.count);
                    } else {
                        // 移除头部节点
                        head.next = removeNode.next;
                        removeNode.next.prev = head;
                    }
                    map.remove(removeNode.key);
                }
                node = new DLinkedNode(key, value);
                map.put(key, node);
            }
            adjust(node);
        }

        private void adjust(DLinkedNode node) {
            node.count++;
            if (node.count == 1) {
                // 新增的node，没有前后对应关系
                minCount = 1;
            } else {
                if (node.prev.prev == null && node.next.next == null) {
                    // 判断原有的链表是否只剩下node这一个节点；则需要把旧链表清除
                    int oldCount = node.count - 1;
                    countMap.remove(oldCount);
                    if (minCount == oldCount) {
                        // 如果链表被移除，则判断被移除的链表是否是最小的count，是则minCount+1
                        minCount++;
                    }
                }
                // 现有的node，需要把旧的对应关系解除
                node.prev.next = node.next;
                node.next.prev = node.prev;
                node.next = null;
                node.prev = null;
            }
            DLinkedList dLinkedList = countMap.get(node.count);
            if (dLinkedList != null) {
                // 注：dLinkedList存在虚拟头尾节点
                dLinkedList.tail.prev.next = node;
                node.prev = dLinkedList.tail.prev;
                node.next = dLinkedList.tail;
                dLinkedList.tail.prev = node;
            } else {
                countMap.put(node.count, new DLinkedList(node));
            }
        }

    }

    public static void main(String[] args) {
        // 1 -1 3 -1 3 4
//        test(2, new int[]{1, 1}, new int[]{2, 2}, new int[]{1}, new int[]{3, 3}, new int[]{2}, new int[]{3}, new int[]{4, 4}, new int[]{1}, new int[]{3}, new int[]{4});
        // 4 3 2 -1 -1 2 3 -1 5
        test(3, new int[]{1, 1}, new int[]{2, 2}, new int[]{3,3}, new int[]{4, 4}, new int[]{4}, new int[]{3}, new int[]{2}, new int[]{1}, new int[]{5,5}, new int[]{1}, new int[]{2}, new int[]{3}, new int[]{4}, new int[]{5});
    }

    public static void test(int capacity, int[]... args) {
        System.out.println("***** TEST *****");
        LFUCache cache = new LFUCache(capacity);
        for (int[] arg : args) {
            if (arg.length == 1) {
                System.out.println(cache.get(arg[0]));
            } else if (arg.length == 2) {
                cache.put(arg[0], arg[1]);
            }
        }
    }
}
