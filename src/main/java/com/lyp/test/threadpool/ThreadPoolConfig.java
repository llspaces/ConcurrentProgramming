package com.lyp.test.threadpool;

import lombok.Data;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

/**
 * <p>@filename ThreadPoolConfig</p>
 * <p>
 * <p>@description 线程池配置实体类</p>
 *
 * @author liyupeng
 * @version 1.0
 * @since 2019/1/10 11:45
 **/
@Data
public class ThreadPoolConfig {
    private int corePoolSize;
    private int maximumPoolSize;
    private long keepAliveTime;
    private TimeUnit timeUnit;
    private BlockingQueue<Runnable> workQueue;
    private ThreadFactory threadFactory;
    private RejectedExecutionHandler handler;

    public ThreadPoolConfig(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit timeUnit,
        BlockingQueue<Runnable> workQueue, ThreadFactory threadFactory, RejectedExecutionHandler handler) {
        this.corePoolSize = corePoolSize;
        this.maximumPoolSize = maximumPoolSize;
        this.keepAliveTime = keepAliveTime;
        this.timeUnit = timeUnit;
        this.workQueue = workQueue;
        this.threadFactory = threadFactory;
        this.handler = handler;
    }

}
