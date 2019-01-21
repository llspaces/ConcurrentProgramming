package com.lyp.test.concurrentUtils;

import com.lyp.test.threadpool.ThreadPoolConfig;
import com.lyp.test.threadpool.ThreadPoolFactory;

import java.util.concurrent.*;

public class UseSemaphore {  
  
    public static void main(String[] args) {  
        // 线程池  
        ThreadPoolConfig config = new ThreadPoolConfig(10,20,60L,
            TimeUnit.SECONDS, new SynchronousQueue<>(),Executors.defaultThreadFactory(),new ThreadPoolExecutor.AbortPolicy());

        ExecutorService executorService = ThreadPoolFactory.create(config);
        // 只能5个线程同时访问  
        final Semaphore semp = new Semaphore(5);  
        // 模拟20个客户端访问  
        for (int index = 0; index < 20; index++) {  
            final int NO = index;  
            Runnable run = new Runnable() {
                @Override
                public void run() {  
                    try {  
                        // 获取许可  
                        semp.acquire();  
                        System.out.println("Accessing: " + NO);  
                        //模拟实际业务逻辑
                        TimeUnit.MILLISECONDS.sleep(5000);
                        // 访问完后，释放
                        semp.release();  
                    } catch (InterruptedException e) {  
                    }  
                }  
            };
            executorService.execute(run);
        } 
        
        try {
			Thread.sleep(10);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
        
        //System.out.println(semp.getQueueLength());

        // 退出线程池  
        executorService.shutdown();
    }  
  
}  
