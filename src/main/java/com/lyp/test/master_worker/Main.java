package com.lyp.test.master_worker;

import java.util.Random;

/**
 * <p>@filename Main</p>
 * <p>
 * <p>@description 主程序，创建初始化任务，开始任务，判断线程是否都执行完毕，获取结果</p>
 *
 * @author liyupeng
 * @version 1.0
 * @since 2019/1/9 13:00
 **/
public class Main {
    public static void main(String[] args) {

        Master master = new Master(new MyWorker(), 20);

        Random r = new Random();
        for(int i = 1; i <= 100; i++){
            Task t = new Task();
            t.setId(i + "");
            int price = r.nextInt(1000);
            System.out.println(price);
            t.setPrice(price);
            master.submit(t);
        }
        long start = System.currentTimeMillis();
        master.execute();
        while(true){
            if(master.isDone()){
                long end = System.currentTimeMillis() - start;
                int priceResult = master.getResult();
                System.out.println("最终结果：" + priceResult + ", 执行时间：" + end);
                break;
            }
        }
    }

}
