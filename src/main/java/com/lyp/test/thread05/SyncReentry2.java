package com.lyp.test.thread05;

import java.sql.Time;
import java.util.concurrent.TimeUnit;

/**
 * <p>@filename SyncReentry2</p>
 * <p>
 * <p>@description synchronized的重入</p>
 *
 * @author liyupeng
 * @version 1.0
 * @since 2019/1/3 15:40
 **/
public class SyncReentry2 {

    static class Super{
        public int i = 10;
        public synchronized void operationSuper(){
            try{
                i--;
                System.out.println("super print i = " + i);
                TimeUnit.MILLISECONDS.sleep(100);
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }

    static class Suber extends Super {
        public synchronized void operationSuber(){
            try {
                while(i > 0) {
                    i--;
                    System.out.println("suber print i = " + i);
                    Thread.sleep(100);
                    this.operationSuper();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                Suber suber = new Suber();
                suber.operationSuber();
            }
        });

        t1.start();
    }
}
