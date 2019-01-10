package com.lyp.test.future;

/**
 * <p>@filename Main</p>
 * <p>
 * <p>@description  系统启动，调用Client发出请求</p>
 *
 * @author liyupeng
 * @version 1.0
 * @since 2019/1/9 11:33
 **/
public class Main {

    public static void main(String[] args) {

        FutureClient futureClient = new FutureClient();
        Data data = futureClient.request("param1");
        System.out.println("请求已发送，可以执行其他操作！");

        String result = data.getRequest();
        System.out.println(result);

    }


}
