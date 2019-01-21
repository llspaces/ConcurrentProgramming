package com.lyp.test.disruptor.secondkill;

import com.lmax.disruptor.EventTranslatorVararg;
import com.lmax.disruptor.RingBuffer;

/**
 * <p>@filename SecondKillEventProducer</p>
 * <p>
 * <p>@description 生产者（使用translator方式）</p>
 *
 * @author liyupeng
 * @version 1.0
 * @since 2019/1/10 17:04
 **/
public class SecondKillEventProducer {

    private final static EventTranslatorVararg<SecondKillEvent> translator = new EventTranslatorVararg<SecondKillEvent>() {
        @Override
        public void translateTo(SecondKillEvent seckillEvent, long seq, Object... objs) {
            seckillEvent.setSecondKillId((Long) objs[0]);
            seckillEvent.setUserId((Long) objs[1]);
        }
    };

    private final RingBuffer<SecondKillEvent> ringBuffer;

    public SecondKillEventProducer(RingBuffer<SecondKillEvent> ringBuffer){
        this.ringBuffer = ringBuffer;
    }

    public void secondKill(long secondKillId, long userId){
        this.ringBuffer.publishEvent(translator, secondKillId, userId);
    }
}
