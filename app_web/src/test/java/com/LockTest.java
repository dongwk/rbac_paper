package com;

import org.junit.Test;

import java.util.Scanner;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockTest {

    private static final Lock lock = new ReentrantLock();
    private static final Condition full = lock.newCondition();

    public static void main(String[] args) {

        T t = new T();
        new Thread(() -> {
            for (int i = 0; i < 10000; i++) {
                System.out.println("thread_1_"+i);
                t.test();
            }
        }, "thread_1").start();

        new Thread(() -> {

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            for (int i = 0; i < 10000; i++) {
                System.out.println("thread_2_"+i);
                t.test();
            }
        }, "thread_1").start();


        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        while (true) {
            Scanner s = new Scanner(System.in);
            s.nextLine();

            lock.lock();
            try {
                full.signalAll();
            } finally {
                lock.unlock();
            }
        }
    }


    static class T {
        public void test(){
            lock.lock();
            try {
                full.await(); // 唤醒从这执行，没有条件生产者消费者问题，获取空
                System.out.println("1");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
    }

}

