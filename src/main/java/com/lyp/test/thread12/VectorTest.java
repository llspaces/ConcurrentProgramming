package com.lyp.test.thread12;

import java.util.Iterator;
import java.util.Vector;
import java.util.concurrent.TimeUnit;

/**
 * <p>@filename VectorTest</p>
 * <p>
 * <p>@description 多线程使用Vector或者HashTable的示例（简单线程同步问题）</p>
 *
 * @author liyupeng
 * @version 1.0
 * @since 2019/1/7 10:53
 **/
public class VectorTest {
    public static void main(String[] args) {
        final Vector<String> vector = new Vector<String>();
        for(int i = 0;i <= 100; i++){
            vector.add("vector" + i);
        }
        test2(vector);
        //test1(vector);
    }

    public static void test1(Vector<String> vector){
        for(Iterator<String> iterator = vector.iterator() ; iterator.hasNext();) {
            String string = iterator.next();
            //java.util.ConcurrentModificationException
            vector.remove(5);
        }
    }

    public static void test2(final Vector<String> vector){
        for (int i = 1 ; i <= 10; i++){
            new Thread(new Runnable() {
                public void run() {
                    while(!vector.isEmpty()){
                        System.out.println(Thread.currentThread().getName() + "---" + vector.remove(0));
                        try {
                            TimeUnit.MILLISECONDS.sleep(500);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    System.out.println(Thread.currentThread().getName() + ": Empty.");
                }
            }, "t"+i).start();
        }
    }

}
