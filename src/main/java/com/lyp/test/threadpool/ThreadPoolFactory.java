package com.lyp.test.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * <p>@filename ThreadPoolFactory</p>
 * <p>
 * <p>@description 创建线程池</p>
 *
 * @author liyupeng
 * @version 1.0
 * @since 2019/1/10 11:51
 **/
public class ThreadPoolFactory {

    public static ExecutorService create(ThreadPoolConfig config){
        return new ThreadPoolExecutor(config.getCorePoolSize(),config.getMaximumPoolSize(),
            config.getKeepAliveTime(),config.getTimeUnit(),config.getWorkQueue(),config.getThreadFactory(),config.getHandler());
    }

}
