package com.lyp.test.disruptor.secondkill;



import com.lmax.disruptor.EventHandler;

/**
 * <p>@filename SecondKillEventConsumer</p>
 * <p>
 * <p>@description 消费者(秒杀处理器)</p>
 *
 * @author liyupeng
 * @version 1.0
 * @since 2019/1/10 17:03
 **/
public class SecondKillEventConsumer implements EventHandler<SecondKillEvent> {
    //业务处理、这里是无法注入的，需要手动获取，见源码
    private SecondKillService seckillService = new SecondKillServiceImpl();


    @Override
    public void onEvent(SecondKillEvent secondKillEvent, long l, boolean b) throws Exception {
        seckillService.startSecondKill(secondKillEvent.getSecondKillId(), secondKillEvent.getUserId());
    }
}
