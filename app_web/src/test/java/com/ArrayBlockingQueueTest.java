package com;

import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;

/**
 * @author dongwk
 * @date 2018/9/3 15:17
 **/
public class ArrayBlockingQueueTest {

    private Thread provider = new Thread(new Runnable() {
        @Override
        public void run() {

        }
    });

    private Thread customer = new Thread(new Runnable() {
        @Override
        public void run() {

        }
    });

    public static void main(String[] args) {
        ArrayBlockingQueue<Integer> queue = new ArrayBlockingQueue<Integer>(2);

        // 提供者
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    queue.put(2);
                    queue.put(3);
                    System.out.println("第一个执行：存入第三个"); // 第一个执行
                    queue.put(5); // 第三个执行
                    System.out.println("第三个执行：存入第三个结束"); // 最后执行 3
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();


        try {
            //test.condition.signal();
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // 消费者
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println("第二步：获取队列的第一个值，第三个也就会自动被唤醒存入第三个"); // 第二个执行
                    int a = queue.take(); // 第二个执行
                    System.out.println("消费者：" + a); // 这个不一定比
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();


        try {
            //test.condition.signal();
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
