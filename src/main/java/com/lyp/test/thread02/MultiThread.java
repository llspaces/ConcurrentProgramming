package com.lyp.test.thread02;

import java.util.concurrent.TimeUnit;

/**
 * <p>@filename MultiThread</p>
 * <p>
 * <p>@description 多个对象多把锁</p>
 *
 * @author liyupeng
 * @version 1.0
 * @since 2019/1/3 11:04
 **/
public class MultiThread {

    private /** static */ int num = 0;

    /**
     * 在方法上加static，静态方法加synchronized，调用该方法所获得的锁是类的锁，和实例化对象不相干
     *
     * @param str
     * @throws Exception
     */
    public /** static */ synchronized void print (String str) throws Exception{
        if("a".equals(str)){
            num = 100;
            System.out.println("tag a set num over ! ");
            TimeUnit.MILLISECONDS.sleep(1000);
        } else if("b".equals(str)){
            num = 200;
            System.out.println("tag b set num over ! ");
        }
        System.out.println("tag " + str + "-num: " + num);
    }

    public static void main(String[] args) {
        /**
         * 一个对象一把锁,m1和m2获得的锁不同，所以两个线程可以一起执行，不许等待锁释放
         */
        final MultiThread m1 = new MultiThread();
        final MultiThread m2 = new MultiThread();
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    m1.print("a");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    m2.print("b");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        t1.start();
        t2.start();
    }

}
