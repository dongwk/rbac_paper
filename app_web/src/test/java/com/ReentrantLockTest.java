package com;

import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.*;

/**
 * @author dongwk
 * @date 2018/9/3 11:17
 **/
public class ReentrantLockTest {

    private Lock lock = new ReentrantLock();
    private ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    private Lock readLock = readWriteLock.readLock();
    private Lock writeLock = readWriteLock.writeLock();
    public Condition condition=lock.newCondition();

    @Test
    public void testAwait() {

        try {
            lock.lock();
            System.out.println("开始wait:threadName:"+Thread.currentThread().getName());
            condition.await();

            // 只说明唤醒，没说明多久又挂起，比如没有抢占到资源
            try {
                //test.condition.signal();
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            for (int i = 0; i < 5; i++) {
                System.out.println("ThreadName=" + Thread.currentThread().getName()
                        + (" " + (i + 1)));
            }
            condition.signalAll();
        } catch (Exception e) {
            // TODO 自动生成的 catch 块
            e.printStackTrace();
        }
        finally
        {
            lock.unlock();
        }
    }

    @Test
    public void testSignal() {

        try {
            lock.lock();
            condition.signalAll();
        } catch (Exception e) {
            e.printStackTrace();
        } finally
        {
            lock.unlock();
        }
    }

    @Test
    public void testMutiThread() {
        ReentrantLockTest test = new ReentrantLockTest();


        for (int i=0; i<10; i++){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    test.testAwait();
                }
            }, "zhangsan "+i).start();
        }

        // 测试 Condition
        // 理解错误，signal
        try {
            //test.condition.signal();
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        for (int i=0; i<1; i++){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    test.testSignal();
                }
            }, "lisi "+i).start();
        }

        try {
            //test.condition.signal();
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 可重入
     */
    @Test
    public void testReentrantLock() {

        try {
            System.out.println("1");
            lock.lock();
            System.out.println("2");
            lock.lock();
            System.out.println("3");
        } catch (Exception e) {
            e.printStackTrace();
        } finally
        {
            lock.unlock();
        }
    }

    /**
     * 不可重入，读写锁
     * 已验证，都是可重入
     */
    @Test
    public void testReadWriteLock() {

        try {
            System.out.println("1");
            writeLock.lock();
            System.out.println("2");
            writeLock.lock();
            System.out.println("3");
        } catch (Exception e) {
            e.printStackTrace();
        } finally
        {
            writeLock.unlock();
        }
    }

    /**
     * 读写锁
     * 读写锁不冲突
     */
    @Test
    public void testReadWriteLock1() {

        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("write 1");
                writeLock.lock();

                try {
                    //test.condition.signal();
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    System.out.println("write 2");
                    writeLock.unlock();
                }

            }
        }, "zhangsan");

        // ---------------------------------------------
        try {
            //test.condition.signal();
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // ---------------------------------------------

        // 读锁
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("read 1");
                readLock.lock();

//                try { // 读-不冲突
//                    //test.condition.signal();
//                    Thread.sleep(10000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }

                System.out.println("read 2");

            }
        }, "lisi").start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("w read 1");
                try { // 读-不冲突
                    //test.condition.signal();
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                readLock.lock();
                System.out.println("w read 2");

            }
        }, "wangwu").start();


        while (true){
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    /**
     * 锁等待调用的什么
     * park interr
     */
    @Test
    public void testReent() {

        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("lock 1");
                lock.lock();
                System.out.println("lock 1 e");

            }
        }, "zhangsan").start();

        // ---------------------------------------------
        try {
            //test.condition.signal();
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // ---------------------------------------------

        // 读锁
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("lock 2");
                lock.lock();
                System.out.println("lock 2 e");

            }
        }, "lisi").start();

        while (true){
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * tryAcquireShared
     * ReentrantReadWriteLock
     * 追加数量
     * compareAndSetState(c, c + SHARED_UNIT)
     * SHARED_UNIT
     * static final int SHARED_UNIT    = (1 << SHARED_SHIFT);
     * 位运算
     */
    @Test
    public void testWeiYunSuan() {
        System.out.println(1 << 16);
    }

    @Test
    public void testUnlock(){
        int count = 5;
        CountDownLatch latch = new CountDownLatch(count);
        Runnable r = new Runnable() {
            @Override
            public void run() {
                System.out.println("start:"+Thread.currentThread().getName());
                lock.lock();
                System.out.println("end:"+Thread.currentThread().getName());

                try {
                    Thread.sleep(10000);
                    lock.unlock();
                    latch.countDown();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        for (int i = 1; i <= count; i++) {
            Thread t = new Thread(r,"t"+i);
            t.start();
        }

        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
