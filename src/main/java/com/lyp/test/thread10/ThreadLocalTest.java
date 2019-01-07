package com.lyp.test.thread10;

import java.util.concurrent.TimeUnit;

/**
 * <p>@filename ThreadLocalTest</p>
 * <p>
 * <p>@description ThreadLocal概念：线程局部变量，无锁，是一种多线程间并发访问变量的解决方案</p>
 *
 * @author liyupeng
 * @version 1.0
 * @since 2019/1/4 18:27
 **/
public class ThreadLocalTest {
    public static ThreadLocal<String> threadLocal = new ThreadLocal<String>();

    public void setValue(String value){
        threadLocal.set(value);
    }

    public String getValue(){
        return threadLocal.get();
    }

    public static void main(String[] args) {
        final ThreadLocalTest threadLocalTest = new ThreadLocalTest();

        Thread t1 = new Thread(new Runnable() {
            public void run() {
                threadLocalTest.setValue("张三");
                System.out.println(threadLocalTest.getValue());
            }
        }, "t1");

        Thread t2 = new Thread(new Runnable() {
            public void run() {
                try {
                    TimeUnit.MILLISECONDS.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(threadLocalTest.getValue());
            }
        }, "t2");

        t1.start();
        t2.start();
    }

}
