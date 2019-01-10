package com.lyp.test.future;

import java.util.concurrent.TimeUnit;

/**
 * <p>@filename RealData</p>
 * <p>
 * <p>@description  真实数据，其构造是比较慢的</p>
 *
 * @author liyupeng
 * @version 1.0
 * @since 2019/1/9 11:39
 **/
public class RealData implements Data{
    private String result;

    public RealData(String param) {
        System.out.println("根据" + param + "进行查询，这是一个很耗时的操作..");
        try {
            TimeUnit.MILLISECONDS.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("操作完毕，获取结果");
        this.result = param + "的查询结果";
    }

    public String getRequest() {
        return result;
    }
}
