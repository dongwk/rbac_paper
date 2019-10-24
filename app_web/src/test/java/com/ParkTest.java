package com;

import org.junit.Test;

import java.util.concurrent.locks.*;

/**
 * @author dongwk
 * @date 2018/9/3 11:17
 **/
public class ParkTest {

    private Lock lock = new ReentrantLock();
    private ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    private Lock readLock = readWriteLock.readLock();
    private Lock writeLock = readWriteLock.writeLock();
    public Condition condition=lock.newCondition();

    @Test
    public void testPark() {

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("zhangsan 1");
                LockSupport.park();
                System.out.println("zhangsan 2");
            }
        }, "zhangsan");
        thread.start();


        try {
            //test.condition.signal();
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("unpark");
        LockSupport.unpark(thread);

        while (true){
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    @Test
    public void testInterrupt() {

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("zhangsan 1");
                }
            }
        }, "zhangsan");
        thread.start();

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("interrupt");
        thread.interrupt();


        alwaysRun();
    }

    private void alwaysRun(){
        while (true){
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
