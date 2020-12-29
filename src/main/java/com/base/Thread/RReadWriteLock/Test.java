package com.base.Thread.RReadWriteLock;

import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.redisson.api.RReadWriteLock;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @ Author     ：zhouxiang.
 * @ Date       ：Created in 16:44 2020/12/24
 * @ Description：${description}
 * @ Modified By：
 * @Version: $version$
 */
public class Test {
    public static void main(String[] args) {
        Config config = new Config();
        config.useSingleServer().setAddress("redis://192.168.2.241:6379");//.setPassword("123456");
        RedissonClient client = Redisson.create(config);


        RReadWriteLock readWriteLock = client.getReadWriteLock("read_write_lock");
        RLock readLock = readWriteLock.readLock();
        RLock writeLock = readWriteLock.writeLock();

        System.out.println("读锁测试：");
        ExecutorService executorService = Executors.newFixedThreadPool(5);

        ExecutorService executorService4 = Executors.newSingleThreadExecutor();
        ExecutorService executorService5 = Executors.newCachedThreadPool();
        ExecutorService executorService6 = Executors.newScheduledThreadPool(5);
        ExecutorService executorService7 = Executors.newWorkStealingPool(5);

        for (int i = 0; i < 10; i++) {
            executorService.submit(() -> {
                try {
                    readLock.lock();
                    System.out.println("线程 " + Thread.currentThread().getId() + " 获得锁：" + System.currentTimeMillis());
                    Thread.sleep(1000);
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    System.out.println("线程 " + Thread.currentThread().getId() + " 释放锁：" + System.currentTimeMillis());
                    readLock.unlock();
                }
            });
        }

        try {
            Thread.sleep(10000);
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("写锁测试：");
        ExecutorService executorService2 = Executors.newFixedThreadPool(5);
        for (int i = 0; i < 10; i++) {
            executorService2.submit(() -> {
                try {
                    writeLock.lock();
                    System.out.println("线程 " + Thread.currentThread().getId() + " 获得锁：" + System.currentTimeMillis());
                    Thread.sleep(1000);
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    System.out.println("线程 " + Thread.currentThread().getId() + " 释放锁：" + System.currentTimeMillis());
                    writeLock.unlock();
                }
            });
        }
    }
}