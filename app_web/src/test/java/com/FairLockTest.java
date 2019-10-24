package com;

import org.junit.Test;

import java.util.Scanner;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class FairLockTest {
    public static void main(String[] args) {

        // 公平锁
        Lock fairLock = new ReentrantLock(true);
        // 共享锁
        ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
        Lock readLock = readWriteLock.readLock();
        Lock writeLock = readWriteLock.writeLock();

        new Thread(() -> {
            System.out.println("thread_1 s");
            fairLock.lock();
            try {
                Thread.sleep(30000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            fairLock.unlock();
            System.out.println("thread_1");
        }, "thread_1").start();

//
        new Thread(() -> {
            System.out.println("thread_2 s");
            fairLock.lock();


            try {
                Thread.sleep(30000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            fairLock.unlock();
            System.out.println("thread_2");
        }, "thread_2").start();
//
//
//        new Thread(() -> {
//            System.out.println("thread_3 s");
//            fairLock.lock();
//
//            try {
//                Thread.sleep(30000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            fairLock.unlock();
//            System.out.println("thread_3");
//        }, "thread_3").start();

        while(true){
            Scanner s = new Scanner(System.in);
            s.nextLine();
        }
    }

    // 读写锁，共享锁
    @Test
    public void readWirte(){
        System.out.println((1 << 16));
    }
}
