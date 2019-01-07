package com.lyp.test.thread08;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * <p>@filename WaitAndNotify</p>
 * <p>
 * <p>@description 线程间的通信wait和notify,某面试题目</p>
 *
 * @author liyupeng
 * @version 1.0
 * @since 2019/1/4 15:11
 **/
public class WaitAndNotify {

    private volatile static List<String> list = new ArrayList();

    public void add(){
        list.add("liyupeng");
    }

    public int size(){
        return list.size();
    }

    public static void main(String[] args) {
        //test1();
        //test2();
        test3();
    }

    //不使用wait和notify，模拟线程通信
    public static void test1(){
        final WaitAndNotify waitAndNotify = new WaitAndNotify();

        Thread t1 = new Thread(new Runnable() {
            public void run() {
                for (int i = 0;i < 10; i ++){
                    waitAndNotify.add();
                    System.out.println("当前线程："+Thread.currentThread().getName()+",添加一个元素。");
                    try {
                        TimeUnit.MILLISECONDS.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        },"t1");

        Thread t2 = new Thread(new Runnable() {
            public void run() {
                while(true){
                    if(waitAndNotify.size() == 5){
                        System.out.println("list长度达到5，线程："+Thread.currentThread().getName()+"停止！");
                        throw new RuntimeException();
                    }
                }
            }
        },"t2");

        t1.start();
        t2.start();
    }

    //wait和notify 线程通信 (wait方法释放锁, notify方法不释放锁)
    public static void test2(){
        final WaitAndNotify waitAndNotify = new WaitAndNotify();

        // 1 实例化出来一个 lock
        // 当使用wait 和 notify 的时候 ， 一定要配合着synchronized关键字去使用
        final Object lock = new Object();

        Thread t1 = new Thread(new Runnable() {
            public void run() {
                synchronized (lock) {
                    for (int i = 0; i < 10; i++) {
                        waitAndNotify.add();
                        System.out.println("当前线程：" + Thread.currentThread().getName() + ",添加一个元素。");
                        try {
                            TimeUnit.MILLISECONDS.sleep(500);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        if(waitAndNotify.size() == 5) {
                            System.out.println("已经发出通知...");
                            lock.notify();//notify不释放锁，所以t2线程在t1执行完才结束
                        }
                    }
                }
            }
        },"t1");

        Thread t2 = new Thread(new Runnable() {
            public void run() {
                synchronized (lock) {
                    if (waitAndNotify.size() != 5) {
                        try {
                            System.out.println("t2线程进入...");
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    System.out.println("当前线程：" + Thread.currentThread().getName() + "收到通知线程停止..");
                    throw new RuntimeException();
                }

            }
        },"t2");

        t2.start();
        t1.start();
    }

    //CountDownLatch改造
    public static void test3(){
        final WaitAndNotify waitAndNotify = new WaitAndNotify();

        final CountDownLatch countDownLatch = new CountDownLatch(1);

        Thread t1 = new Thread(new Runnable() {
            public void run() {
                for (int i = 0; i < 10; i++) {
                    waitAndNotify.add();
                    System.out.println("当前线程：" + Thread.currentThread().getName() + ",添加一个元素。");
                    try {
                        TimeUnit.MILLISECONDS.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    if(waitAndNotify.size() == 5) {
                        System.out.println("已经发出通知...");
                        countDownLatch.countDown();
                    }
                }

            }
        },"t1");

        Thread t2 = new Thread(new Runnable() {
            public void run() {
                if (waitAndNotify.size() != 5) {
                    try {
                        System.out.println("t2线程进入...");
                        countDownLatch.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("当前线程：" + Thread.currentThread().getName() + "收到通知线程停止..");
                throw new RuntimeException();

            }
        },"t2");

        t2.start();
        t1.start();
    }
}
