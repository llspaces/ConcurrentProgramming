package com.lyp.test.master_worker;

import lombok.Data;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.TimeUnit;

/**
 * <p>@filename Worker</p>
 * <p>
 * <p>@description Worker进程</p>
 *
 * @author liyupeng
 * @version 1.0
 * @since 2019/1/9 13:00
 **/
@Data
public abstract class Worker implements Runnable{

    protected ConcurrentLinkedQueue<Task> tasks;
    protected ConcurrentHashMap<String, Object> resultMap;

    public Object handle(Task task) {
        return null;
    }
}
