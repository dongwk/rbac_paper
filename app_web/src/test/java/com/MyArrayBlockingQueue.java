package com;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author dongwk
 * @date 2018/9/3 14:00
 **/
public class MyArrayBlockingQueue<T> {
    //维护的数据
    private final T[] datas;
    //数据的个数
    private int count;
    //插入取出的索引
    private int put_index;
    private int take_index;

    //锁
    private final Lock lock = new ReentrantLock();
    //定义两个条件，分别为“集合满”和“集合空”
    private Condition full = lock.newCondition();
    private Condition empty = lock.newCondition();

    //提供MyArrayBlockingQueue的构造方法，初始化T[]数据
    public MyArrayBlockingQueue() {
        this(10);
    }

    public MyArrayBlockingQueue(int maxSize) {
        this.datas = (T[]) new Object[maxSize];
    }

    public void put(T data){
        lock.lock();
        try {
            if(count == datas.length){
                //此时集合已经满了
                System.out.println("集合已满，请等待...");
                //使调用线程挂起
                full.await();
            }
            //不满则添加新元素
            datas[put_index++] = data;
            count++;

            //此时唤醒等待取数据的线程
            empty.signalAll();

        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            lock.unlock();
        }
    }
}
