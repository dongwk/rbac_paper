//package com;
//
//import org.junit.Test;
//import redis.clients.jedis.Jedis;
//import redis.clients.jedis.JedisPubSub;
//
//import java.utils.ArrayList;
//import java.utils.HashMap;
//import java.utils.List;
//import java.utils.Map;
//import java.utils.concurrent.locks.LockSupport;
//
///**
// * @author dongwk
// * @date 2018/9/7 15:45
// **/
//public class RedisTest {
//
//    static RedisProperties.Jedis jedis = new Jedis("192.168.7.102");
//    static{
//        jedis.auth("Abcd!@#456789");
//        jedis.select(1);
//    }
//    private final String key = "a";
//    private final String val = "1";
//
//
//    @Test
//    public void ping(){
//        //连接本地的 Redis 服务
//        System.out.println("连接成功");
//        //查看服务是否运行
//        System.out.println("服务正在运行: "+jedis.ping());
//    }
//
//    /**
//     * set 集合
//     */
//    @Test
//    public void set(){
//        jedis.sadd(key, val);
//        System.out.println(jedis.scan(key));
//    }
//
//    /**
//     * pub/sub 发布订阅,dingyue
//     */
//    @Test
//    public void sub(){
//        // 订阅
//        JedisPubSub sub = new JedisPubSub() {
//            @Override
//            public void onMessage(String channel, String message) {
//                System.out.println("channel:" + channel + ", message:" + message + ",thread:" + Thread.currentThread().getName());
//            }
//        };
//
//        // 线程1
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                System.out.println("a");
//                jedis.subscribe(sub, "aaaaa");
//            }
//        }, "thread1").start();
//
//        // 主线程
//        try {
//            Thread.sleep(3000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        System.out.println("b");
//        jedis.subscribe(sub, "aaaaa");
//    }
//
//    /**
//     * pub/psub 发布订阅,fabu
//     */
//    @Test
//    public void pub(){
//        jedis.publish("aaaaa", "11");
//        //jedis.publish("aaaaa", "12");
//    }
//
//    /**
//     * 线程循环
//     */
//    @Test
//    public void testPrt() {
//
//    }
//
//    @Test
//    public void testPrt1(){
//        // TODO 自动生成的方法存根
//        Observers observers1 = new Observers("王大锤");
//        Observers observers2 = new Observers("黄晓明");
//        Observers observers3 = new Observers("郑恺");
//        Observers observers4 = new Observers("邓超");
//
//        Angelababy angelababy = new Angelababy();
//
//        // 注册添加观察者
//        angelababy.addObservers(observers1);
//        angelababy.addObservers(observers2);
//        angelababy.addObservers(observers3);
//        angelababy.addObservers(observers4);
//
//        // 发出通知消息
//        angelababy.notifyAllObservers("我不开心");
//    }
//}
//
//// 观察 --
//class Guancha{
//    Map<String, List<IObserver>> map = new HashMap<>();
//
//    public void sub(IObserver observer, String chnnel){
//
//        while(true) {
//            // 拉
//            LockSupport.park();
//        }
//    }
//
//    public void pub(String chnnel, String msg){
//        List<IObserver> list = map.get(chnnel);
//        if (list != null && list.size() > 0){
//            for (IObserver iObserver : list) {
//                if (iObserver != null) iObserver.updateMsg(msg);
//            }
//        }
//    }
//}
//
//// 线程
//interface IPrettyGirl {
//   void addObservers(IObserver observers);//添加观察者
//
//   void deleteObservers(IObserver observers);//删除观察者
//
//   void notifyAllObservers(String msg);//通知所有的观察者
//}
//interface IObserver {
//    void updateMsg(String msg);
//}
//class Angelababy implements IPrettyGirl {
//
//    // 观察者集合
//    private List<IObserver> mList = new ArrayList<IObserver>();// 注意这里集合的泛型用的是接口类型
//
//    public void addObservers(IObserver observers) {
//        mList.add(observers);
//    }
//
//    @Override
//    public void deleteObservers(IObserver observers) {
//        mList.remove(observers);
//    }
//
//    @Override
//    public void notifyAllObservers(String msg) {
//        for (IObserver list : mList) {
//            list.updateMsg(msg);
//        }
//    }
//}
//class Observers implements IObserver {
//    private String name;
//
//    public  Observers(String name) {
//        this.name = name;
//    }
//
//    @Override
//    public void updateMsg(String msg) {
//        // TODO 自动生成的方法存根
//        if ("我不开心".equals(msg)) {
//            System.out.println(name+"说： 多喝热水");
//        }
//    }
//
//}