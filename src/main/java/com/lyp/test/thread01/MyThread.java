package com.lyp.test.thread01;

/**
 * <p>@filename MyThread</p>
 * <p>
 * <p>@description Thread</p>
 *
 * @author liyupeng
 * @version 1.0
 * @since 2019/1/2 17:23
 **/
public class MyThread extends Thread{

    private int count = 10;

    @Override
    public synchronized void run() {
        count--;
        System.out.print(count + ": ");
        System.out.println(Thread.currentThread().getName() + "\t count: " + count);
    }

    public static void main(String[] args) {
        MyThread myThread = new MyThread();
        Thread t1 = new Thread(myThread, "t1");
        Thread t2 = new Thread(myThread, "t2");
        Thread t3 = new Thread(myThread, "t3");
        Thread t4 = new Thread(myThread, "t4");
        Thread t5 = new Thread(myThread, "t5");
        Thread t6 = new Thread(myThread, "t6");
        Thread t7 = new Thread(myThread, "t7");
        Thread t8 = new Thread(myThread, "t8");
        Thread t9 = new Thread(myThread, "t9");
        Thread t10 = new Thread(myThread, "t10");
        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();
        t6.start();
        t7.start();
        t8.start();
        t9.start();
        t10.start();
    }
}
