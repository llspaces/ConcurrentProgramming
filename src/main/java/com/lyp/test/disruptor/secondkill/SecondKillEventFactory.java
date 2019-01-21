package com.lyp.test.disruptor.secondkill;

import com.lmax.disruptor.EventFactory;

/**
 * <p>@filename SecondKillEventFactory</p>
 * <p>
 * <p>@description 事件生成工厂（用来初始化预分配事件对象）</p>
 *
 * @author liyupeng
 * @version 1.0
 * @since 2019/1/10 17:01
 **/
public class SecondKillEventFactory implements EventFactory<SecondKillEvent>{
    @Override
    public SecondKillEvent newInstance() {
        return new SecondKillEvent();
    }
}
