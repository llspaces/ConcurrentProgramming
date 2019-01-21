package com.lyp.test.disruptor.secondkill;

/**
 * <p>@filename SecondKillService</p>
 * <p>
 * <p>@description 秒杀业务处理接口</p>
 *
 * @author liyupeng
 * @version 1.0
 * @since 2019/1/10 17:14
 **/
public interface SecondKillService {
    public void startSecondKill(long secondKillId, long userId);
}
