package com.lyp.test.provider_consumer;

import java.sql.Time;
import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * <p>@filename Provider</p>
 * <p>
 * <p>@description 生产者</p>
 *
 * @author liyupeng
 * @version 1.0
 * @since 2019/1/9 18:04
 **/
public class Provider implements Runnable{
    //共享缓存区
    private BlockingQueue<Entity> queue;
    //多线程间是否启动变量，有强制从主内存中刷新的功能。即时返回线程的状态
    private volatile boolean isRunning = true;
    //id生成器
    private static AtomicInteger id = new AtomicInteger();
    //Random对象
    private static Random random = new Random();

    public Provider(BlockingQueue<Entity> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        while(isRunning){
            //随机休眠0 - 1000 毫秒 表示获取数据(产生数据的耗时)
            try {
                TimeUnit.MILLISECONDS.sleep(random.nextInt(2000));
                String id = createId();
                Entity entity = new Entity(id, "data"+id);
                System.out.println("当前线程:" + Thread.currentThread().getName() + ", 获取了数据，id为:" + id + ", 进行装载到公共缓冲区中...");
                if(!this.queue.offer(entity, 2, TimeUnit.SECONDS)){
                    System.out.println("提交缓冲区数据失败....");
                    //do something... 比如重新提交
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void stop(){
        this.isRunning = false;
    }

    private String createId(){
        return Integer.toString(id.incrementAndGet());
    }
}
