package com.lyp.test.disruptor.secondkill;

import lombok.Data;

import java.io.Serializable;

/**
 * <p>@filename SecondKillEvent</p>
 * <p>
 * <p>@description 事件对象（秒杀事件）</p>
 *
 * @author liyupeng
 * @version 1.0
 * @since 2019/1/10 16:59
 **/
@Data
public class SecondKillEvent implements Serializable{
    private static final long serialVersionUID = 1L;

    private long secondKillId;
    private long userId;

}
