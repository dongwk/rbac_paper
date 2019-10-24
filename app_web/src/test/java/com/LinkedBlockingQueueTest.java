package com;

import java.util.concurrent.LinkedBlockingQueue;

/*
测试 LinkedBlockingQueue 添加元素，是阻塞还是报错

结论：add 报错，put 阻塞
 */
public class LinkedBlockingQueueTest {

    public static void main(String[] args) {
        LinkedBlockingQueue<String> queue = new LinkedBlockingQueue<>(2);
//        try {
//            queue.put("1");
//            queue.put("2");
//            queue.put("3");
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

        queue.add("1");
        queue.add("2");
        queue.add("3");
    }
}
