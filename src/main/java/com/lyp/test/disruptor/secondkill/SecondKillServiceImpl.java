package com.lyp.test.disruptor.secondkill;

import java.util.concurrent.TimeUnit;

/**
 * <p>@filename SecondKillServiceImpl</p>
 * <p>
 * <p>@description 秒杀业务处理实现类</p>
 *
 * @author liyupeng
 * @version 1.0
 * @since 2019/1/10 17:17
 **/
public class SecondKillServiceImpl implements SecondKillService{

    @Override
    public void startSecondKill(long secondKillId, long userId) {
        //秒杀业务实现
        System.out.println("user:" + userId + "参与了秒杀活动，id为:" + secondKillId);
        //模拟业务处理耗时
        try {
            TimeUnit.MILLISECONDS.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
