package com.lyp.test.disruptor.secondkill;

import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;
import java.util.concurrent.ThreadFactory;

/**
 * <p>@filename SecondKillEventMain</p>
 * <p>
 * <p>@description 测试类</p>
 *
 * @author liyupeng
 * @version 1.0
 * @since 2019/1/10 17:10
 **/
public class SecondKillEventMain {

    public static void main(String[] args) {
        producerWithTranslator();
    }
    public static void producerWithTranslator(){

        SecondKillEventFactory factory = new SecondKillEventFactory();
        int ringBufferSize = 1024;
        ThreadFactory threadFactory = new ThreadFactory() {
            @Override
            public Thread newThread(Runnable runnable) {
                return new Thread(runnable);
            }
        };

        //创建disruptor
        Disruptor<SecondKillEvent> disruptor = new Disruptor<SecondKillEvent>(factory, ringBufferSize, threadFactory);
        //连接消费事件方法
        disruptor.handleEventsWith(new SecondKillEventConsumer());
        //启动
        disruptor.start();
        RingBuffer<SecondKillEvent> ringBuffer = disruptor.getRingBuffer();
        SecondKillEventProducer producer = new SecondKillEventProducer(ringBuffer);
        for(long i = 1; i <= 10; i++){
            producer.secondKill(i, i);
        }
        //关闭 disruptor，方法会堵塞，直至所有的事件都得到处理；
        disruptor.shutdown();
    }
}
