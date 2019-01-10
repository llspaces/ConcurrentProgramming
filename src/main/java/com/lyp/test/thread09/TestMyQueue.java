package com.lyp.test.thread09;

import java.util.concurrent.TimeUnit;

/**
 * <p>@filename TestMyQueue</p>
 * <p>
 * <p>@description 测试MyQueue</p>
 *
 * @author liyupeng
 * @version 1.0
 * @since 2019/1/4 18:07
 **/
public class TestMyQueue {

    public static void main(String[] args) {
        final MyQueue myQueue = new MyQueue(5);

        myQueue.put("a");
        myQueue.put("b");
        myQueue.put("c");
        myQueue.put("d");
        myQueue.put("e");

        System.out.println("当前queue长度为：" + myQueue.size());
        System.out.println("------------------------------");
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                myQueue.put("f");
                myQueue.put("g");
            }
        },"t1");
        t1.start();

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                myQueue.take();
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                myQueue.take();
            }
        },"t2");

        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        t2.start();

    }
}
