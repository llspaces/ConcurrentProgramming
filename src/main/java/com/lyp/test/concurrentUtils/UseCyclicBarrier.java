package com.lyp.test.concurrentUtils;
import com.lyp.test.threadpool.ThreadPoolConfig;
import com.lyp.test.threadpool.ThreadPoolFactory;

import java.io.IOException;
import java.util.Random;
import java.util.concurrent.*;

public class UseCyclicBarrier {

	static class Runner implements Runnable {  
	    private CyclicBarrier barrier;  
	    private String name;  
	    
	    public Runner(CyclicBarrier barrier, String name) {  
	        this.barrier = barrier;  
	        this.name = name;  
	    }  
	    @Override  
	    public void run() {  
	        try {  
	            Thread.sleep(1000 * (new Random()).nextInt(5));  
	            System.out.println(name + " 准备OK.");  
	            barrier.await();  
	        } catch (InterruptedException e) {  
	            e.printStackTrace();  
	        } catch (BrokenBarrierException e) {  
	            e.printStackTrace();  
	        }  
	        System.out.println(name + " Go!!");  
	    }  
	} 
	
    public static void main(String[] args) throws IOException, InterruptedException {  
        CyclicBarrier barrier = new CyclicBarrier(3);  // 3 
		ThreadPoolConfig config = new ThreadPoolConfig(5,20,60L,
			TimeUnit.SECONDS, new LinkedBlockingQueue<>(),Executors.defaultThreadFactory(),new ThreadPoolExecutor.AbortPolicy());
		ExecutorService executorService = ThreadPoolFactory.create(config);

		executorService.submit(new Thread(new Runner(barrier, "zhangsan")));
		executorService.submit(new Thread(new Runner(barrier, "lisi")));
		executorService.submit(new Thread(new Runner(barrier, "wangwu")));

		executorService.shutdown();
    }  
  
}  
