package com.yellow.leetcode.demo;

/**
 * Class Name: WaitNotifyDemo
 * Description: Object的wait和notify方法demo
 *
 * @author yellow
 * @since 2020-07-25 19:13
 */
public class WaitNotifyDemo {

    static final Object lock = new Object();

    public static void main(String[] args) {
        new Thread(() -> {
            System.out.println("thread-1 start!");
            synchronized (lock) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("thread-2 try wait!");
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("thread-1 notified!");
            }
        }).start();

        new Thread(() -> {
            System.out.println("thread-3 start!");
            synchronized (lock) {
                System.out.println("thread-3 try wait!");
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("thread-3 notified!");
            }
        }).start();

        new Thread(() -> {
            System.out.println("thread-2 start!");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (lock) {
                System.out.println("try notify!");
                lock.notifyAll();
                System.out.println("thread-2 has notify all!");
            }
        }).start();
    }
}
