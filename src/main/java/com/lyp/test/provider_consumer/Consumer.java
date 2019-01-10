package com.lyp.test.provider_consumer;

import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * <p>@filename Consumer</p>
 * <p>
 * <p>@description 消费者</p>
 *
 * @author liyupeng
 * @version 1.0
 * @since 2019/1/9 18:04
 **/
public class Consumer implements Runnable{

    private BlockingQueue<Entity> queue;

    public Consumer(BlockingQueue<Entity> queue) {
        this.queue = queue;
    }

    //Random对象
    private static Random random = new Random();

    @Override
    public void run() {
        while(true){
            //进行数据处理。休眠0 - 1000毫秒模拟耗时
            try {
                Entity entity = this.queue.take();
                TimeUnit.MILLISECONDS.sleep(random.nextInt(2000));
                System.out.println("当前消费线程：" + Thread.currentThread().getName() + "， 消费成功，消费数据为id: " + entity.getId());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
