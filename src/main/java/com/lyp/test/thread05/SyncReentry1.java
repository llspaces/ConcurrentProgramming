package com.lyp.test.thread05;

/**
 * <p>@filename SyncReentry1</p>
 * <p>
 * <p>@description synchronized的重入</p>
 *
 * @author liyupeng
 * @version 1.0
 * @since 2019/1/3 15:38
 **/
public class SyncReentry1 {

    public synchronized void m1(){
        System.out.println("method1...");
        m2();
    }

    public synchronized void m2(){
        System.out.println("method2...");
        m3();
    }

    public synchronized void m3(){
        System.out.println("method3...");
    }

    public static void main(String[] args) {
        final SyncReentry1 sr1 = new SyncReentry1();

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                sr1.m1();
            }
        });

        t1.start();
    }

}
