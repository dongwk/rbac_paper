/*
 * Copyright (C) 1997-2020 康成投资（中国）有限公司
 *
 * http://www.rt-mart.com
 *
 * 版权归本公司所有，不得私自使用、拷贝、修改、删除，否则视为侵权
 */
package com.app.core.thread;


import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.CountDownLatch;

/**
 * spring 线程池配置
 * @author dongwk
 * @date 2020-09-10 10:39
 **/
@Slf4j
public class MultiThreadTaskUtils {
    @Setter
    private static ThreadPoolTaskExecutor threadPoolTaskExecutor;

    public static <T> void submit(String name, int count, int max, MultiThreadTask<T> multiThreadTask) {
        CountDownLatch countDownLatch = new CountDownLatch(count);
        ArrayBlockingQueue<Integer> queue = new ArrayBlockingQueue(max);

        for (int i = 1; i <= count; i++) {
            final int tmpi = i;
            try {
                queue.put(1);
                T readyRet = multiThreadTask.ready(tmpi);
                threadPoolTaskExecutor.submit(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            multiThreadTask.exec(tmpi, readyRet);
                        } finally {
                            try {
                                queue.take();
                            } catch (InterruptedException e) {
                                log.error(e.getMessage());
                            }
                            countDownLatch.countDown();
                        }
                    }
                });
            } catch (InterruptedException e) {
                countDownLatch.countDown();
                e.printStackTrace();
            }
        }

        try {
            if (countDownLatch != null) {
                countDownLatch.await();
            }
        } catch (InterruptedException e) {
            log.error(e.getMessage());
        }
    }
}
