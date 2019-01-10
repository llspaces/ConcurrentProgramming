package com.lyp.test.future;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

/**
 * <p>@filename JDKFutureTest</p>
 * <p>
 * <p>@description concurrent包下有相关future的实现，需求：获取子线程运行的结果</p>
 *
 * @author liyupeng
 * @version 1.0
 * @since 2019/1/9 11:56
 **/
public class JDKFutureTest {

    public static void main(String[] args) {
        //随便模拟，计算主线程a和子线程t1返回值的和
        int a = 10;

        Callable<Integer> callable = new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                //模拟耗时3s
                TimeUnit.MILLISECONDS.sleep(3000);
                int result = 4 + 6;
                return result;
            }
        };
        FutureTask<Integer> futureTask = new FutureTask<Integer>(callable);
        Thread t1 = new Thread(futureTask,"t1");
        //启动子线程
        t1.start();


        while(!futureTask.isDone()){
            System.out.println("等待子线程执行完毕...");
            try {
                TimeUnit.MILLISECONDS.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        int result = 0;
        try {
            result = a + futureTask.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        System.out.println("执行结果为:" + result);

    }
}
