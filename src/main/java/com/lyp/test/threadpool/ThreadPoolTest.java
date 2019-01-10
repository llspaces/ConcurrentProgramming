package com.lyp.test.threadpool;

import com.lyp.test.provider_consumer.Consumer;
import com.lyp.test.provider_consumer.Entity;
import com.lyp.test.provider_consumer.Provider;

import java.util.concurrent.*;

/**
 * <p>@filename ThreadPoolTest</p>
 * <p>
 * <p>@description provider consumer测试类，使用自定义线程池初始化方法</p>
 *
 * @author liyupeng
 * @version 1.0
 * @since 2019/1/10 11:53
 **/
public class ThreadPoolTest {

    public static void main(String[] args) {
        //内存缓存区
        BlockingQueue<Entity> queue = new LinkedBlockingQueue<>(10);
        //生产者
        Provider p1 = new Provider(queue);
        Provider p2 = new Provider(queue);
        Provider p3 = new Provider(queue);
        //消费者
        Consumer c1 = new Consumer(queue);
        Consumer c2 = new Consumer(queue);
        Consumer c3 = new Consumer(queue);

        //Executors.defaultThreadFactory()
        //defaultHandler = new ThreadPoolExecutor.AbortPolicy()
        ThreadPoolConfig config = new ThreadPoolConfig(10,20,60L,
            TimeUnit.SECONDS, new SynchronousQueue<>(),Executors.defaultThreadFactory(),new ThreadPoolExecutor.AbortPolicy());

        ExecutorService executorService = ThreadPoolFactory.create(config);
        executorService.execute(p1);
        executorService.execute(p2);
        executorService.execute(p3);
        executorService.execute(c1);
        executorService.execute(c2);
        executorService.execute(c3);

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        p1.stop();
        p2.stop();
        p3.stop();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        executorService.shutdown();

    }

}
