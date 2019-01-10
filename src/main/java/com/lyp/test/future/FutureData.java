package com.lyp.test.future;

/**
 * <p>@filename FutureData</p>
 * <p>
 * <p>@description Future数据，构造很快，但是是一个虚拟的数据，需要装配RealData</p>
 *
 * @author liyupeng
 * @version 1.0
 * @since 2019/1/9 11:38
 **/
public class FutureData implements Data{

    private RealData realData;

    private boolean isReady = false;

    public synchronized void setRealData(RealData realData){
        //如果已经装载完毕了，就直接返回
        if(isReady){
            return;
        }
        //如果没装载，进行装载真实对象
        this.realData = realData;
        isReady = true;
        //进行通知
        notify();
    }

    public synchronized String getRequest() {
        //如果没装载好 程序就一直处于阻塞状态
        while(!isReady){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        //装载好直接获取数据即可
        return this.realData.getRequest();
    }
}
