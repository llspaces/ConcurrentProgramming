package com.lyp.test.master_worker;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * <p>@filename Master</p>
 * <p>
 * <p>@description Master进程</p>
 *
 * @author liyupeng
 * @version 1.0
 * @since 2019/1/9 13:00
 **/
public class Master {
    //1.盛放任务的容器
    private ConcurrentLinkedQueue<Task> tasks = new ConcurrentLinkedQueue<>();
    //2.盛放workers的集合
    private HashMap<String, Thread> workers = new HashMap<>();
    //3.盛放每一个worker的执行结果
    private ConcurrentHashMap<String, Object> resultMap = new ConcurrentHashMap<>();

    //构造方法

    public Master(Worker worker, int workerCount) {
        worker.setTasks(this.tasks);
        worker.setResultMap(this.resultMap);

        for(int i = 0; i< workerCount; i ++) {
            this.workers.put(Integer.toString(i), new Thread(worker));
        }
    }

    //提交任务
    public void submit(Task task){
        this.tasks.add(task);
    }
    //执行方法，启动所有worker执行任务
    public void execute() {
        for (Map.Entry<String, Thread> worker : workers.entrySet()){
            worker.getValue().start();
        }
    }
    //判断是否执行完毕
    public boolean isDone(){
        boolean flag = true;
        for (Map.Entry<String, Thread> worker : workers.entrySet()){
            if(worker.getValue().getState() != Thread.State.TERMINATED){
                flag = false;
            }
        }
        return flag;
    }
    //计算结果
    public int getResult(){
        int result = 0;
        for (Map.Entry<String, Object> rs : resultMap.entrySet()){
           result += (Integer) rs.getValue();
        }
        return result;
    }

}
