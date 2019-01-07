package com.lyp.test.thread03;

import java.util.concurrent.TimeUnit;

/**
 * <p>@filename MyObject</p>
 * <p>
 * <p>@description 对象锁的同步与异步</p>
 *
 * @author liyupeng
 * @version 1.0
 * @since 2019/1/3 11:31
 **/
public class MyObject {

    public synchronized void method1(){
        System.out.println(Thread.currentThread().getName());
        try {
            TimeUnit.MILLISECONDS.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public /*synchronized*/ void method2() {
        System.out.println(Thread.currentThread().getName());
    }

    public static void main(String[] args) {
        final MyObject mo = new MyObject();
        /**
         * 分析：
         * t1线程先持有object对象的Lock锁，t2线程可以以异步的方式调用对象中的非synchronized修饰的方法
         * t1线程先持有object对象的Lock锁，t2线程如果在这个时候调用对象中的同步（synchronized）方法则需等待，也就是同步
         */
        Thread t1 = new Thread(new Runnable() {
            public void run() {
                mo.method1();
            }
        },"t1");

        Thread t2 = new Thread(new Runnable() {
            public void run() {
                mo.method2();
            }
        },"t2");

        t1.start();
        t2.start();
    }
}
