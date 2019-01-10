package com.lyp.test.future;

/**
 * <p>@filename FutureClient</p>
 * <p>
 * <p>@description 返回Data对象，立即返回FutureData，并开启ClientThread线程装配RealData</p>
 *
 * @author liyupeng
 * @version 1.0
 * @since 2019/1/9 11:37
 **/
public class FutureClient {

    public Data request(final String param){
        //1 我想要一个代理对象（Data接口的实现类）先返回给发送请求的客户端，告诉他请求已经接收到，可以做其他的事情
        final FutureData futureData = new FutureData();
        //2 启动一个新的线程，去加载真实的数据，传递给这个代理对象
        new Thread(new Runnable() {
            public void run() {
                //3 这个新的线程可以去慢慢的加载真实对象，然后传递给代理对象
                RealData realData = new RealData(param);
                futureData.setRealData(realData);
            }
        }).start();
        return futureData;
    }

}
