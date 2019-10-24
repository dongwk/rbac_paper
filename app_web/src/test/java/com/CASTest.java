package com;

import com.google.common.collect.Lists;
import org.junit.Test;
import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author dongwk
 * @date 2018/8/14 13:57
 **/
public class CASTest {

    @Test
    public void testUnsafe(){

        User user = new User();
        user.setId(1); // 只有基本类型才行

        // Unsafe
        Unsafe u = getUnsafe();

        long valueOffset = 0;
        try {
            valueOffset = u.objectFieldOffset
                    (User.class.getDeclaredField("id"));
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }

        System.out.println("-------------------------------");
        System.out.println("旧值:"+user.getId());
        u.compareAndSwapInt(user, valueOffset,1, 2);
        System.out.println("偏移量:"+valueOffset);
        System.out.println("新值:"+user.getId());
    }

    @Test
    public void testUnsafeArray(){

        User[] user = new User[5];
        User user1 = new User();
        System.out.println("-------------------------------");
        System.out.println("旧值："+user[0]);

        // Unsafe
        Unsafe u = getUnsafe();

        Class<?> ak = User[].class;
        int ABASE = u.arrayBaseOffset(ak);
        int scale = u.arrayIndexScale(ak);
        int ASHIFT = 31 - Integer.numberOfLeadingZeros(scale);

        u.compareAndSwapObject(user, ((long)0 << ASHIFT) + ABASE, null, user1); // 索引

        System.out.println("-------------------------------");
        System.out.println("新值："+user[0]);
    }

    @Test
    public void testAtomic(){
        AtomicInteger a = new AtomicInteger();
        a.incrementAndGet();
        // Atomic
    }

    @Test
    public void testReentrantLock(){
        ReentrantLock lock = new ReentrantLock();
        // lock
//        lock.lock(); // AQS
//        lock.unlock();

        // 开两个线程
        new Thread(new Runnable() {
            @Override
            public void run() {
                lock.lock();
                System.out.println("sout thired1");

                while (true){
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, "thread1").start();

        System.out.println("thread1 -> thread2");
        // 开两个线程
        new Thread(new Runnable() {
            @Override
            public void run() {
                lock.lock(); // park
                System.out.println("sout thired2");
            }
        }, "thread2").start();

        while (true){
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Test
    public void testCountDownLatch(){
        CountDownLatch count = new CountDownLatch(5);
        // count
        try {
            count.await();
            count.countDown();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testCyclicBarrier() {
        CyclicBarrier count = new CyclicBarrier(5);
        // count
        try {
            count.await();

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }

    }

    @Test // test use
    public void testCyclicBarrierUse() throws InterruptedException {
        CountDownLatch count1 = new CountDownLatch(5); // 辅助
        CyclicBarrier count = new CyclicBarrier(5);
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(10, 20, 1, TimeUnit.MINUTES, new ArrayBlockingQueue(5));
        for (int i = 0; i < 5; i++) {
            threadPoolExecutor.execute(() -> {
                try {
                    System.out.println("f11 ");
                    count.await();
                    System.out.println("f1 ");
                    count1.countDown();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            });
        }

        for (int i = 0; i < 4; i++) {
            count1.await();

            threadPoolExecutor.execute(() -> {
                try {
                    System.out.println("f21 ");
                    count.await();
                    System.out.println("f2 ");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            });
        }
        foreverRun();
    }

    @Test
    public void testSemaphore() {
        Semaphore count = new Semaphore(5);
        // count
        try {
            count.acquire();
            count.release();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    // getUnsafe
    public static Unsafe getUnsafe() {
        try {
            Field singletonInstanceField = Unsafe.class.getDeclaredField("theUnsafe");
            singletonInstanceField.setAccessible(true);
            return (Unsafe) singletonInstanceField.get(null);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static List<User> list = Lists.newArrayList();

    @Test
    public void testLockSupport(){
        System.out.println("a");
        LockSupport.park(this); // 线程阻塞
        System.out.println("b");

//        LockSupport.unpark();
        // count
//        count.await();
    }
//

    public static void main(String[] args) {
        int q = 0;

        // put
        ConcurrentHashMap map = new ConcurrentHashMap();
        map.putIfAbsent(null, null);

        HashMap map1 = new HashMap();
        map1.putIfAbsent(null, null);

        // Unsafe
        Unsafe u = Unsafe.getUnsafe();
        User[] user = new User[5];

        // ReentrantLock
        ReentrantLock lock = new ReentrantLock();
        lock.lock();
        lock.unlock();


        int[] a = new int[10];
        a[0] = 1;

        a[0>>2] = 1;
        // MAT
        while (true){
            for (int i = 0; i < 10; i++) {
                User t = new User();
                t.setId(q+i);
                t.setName("张三"+t.getId());
                list.add(t);
            }
            q++;
            try {
                Thread.sleep(180000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    public static void foreverRun(){
        while (true){
            Scanner s = new Scanner(System.in);
            s.nextLine();
        }
    }
}

class User{
    private int id;
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
