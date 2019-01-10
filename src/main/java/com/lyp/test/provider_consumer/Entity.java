package com.lyp.test.provider_consumer;

import lombok.Data;

/**
 * <p>@filename Entity</p>
 * <p>
 * <p>@description 数据实体</p>
 *
 * @author liyupeng
 * @version 1.0
 * @since 2019/1/9 18:04
 **/
@Data
public final class Entity {

    private String id;
    private String name;

    public Entity(String id, String name) {
        this.id = id;
        this.name = name;
    }
}
