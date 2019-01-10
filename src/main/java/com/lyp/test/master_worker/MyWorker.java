package com.lyp.test.master_worker;

import java.util.concurrent.TimeUnit;

/**
 * <p>@filename MyWorker</p>
 * <p>
 * <p>@description TODO</p>
 *
 * @author liyupeng
 * @version 1.0
 * @since 2019/1/9 18:48
 **/
public class MyWorker extends Worker {

    @Override
    public void run() {
        while (true){
            Task task = this.tasks.poll();
            if(task == null){
                break;
            }
            Object result = handle(task);
            this.resultMap.put(task.getId(), result);
        }
    }

    @Override
    public Object handle(Task task) {
        Object result = null;

        try {
            //模拟任务处理的耗时
            TimeUnit.MILLISECONDS.sleep(1000);
            result = task.getPrice();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return result;
    }
}
