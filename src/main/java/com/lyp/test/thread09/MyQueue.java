package com.lyp.test.thread09;

import java.util.LinkedList;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * <p>@filename MyQueue</p>
 * <p>
 * <p>@description wait和notify模拟一个BlockQueue</p>
 *
 * @author liyupeng
 * @version 1.0
 * @since 2019/1/4 17:49
 **/
public class MyQueue {
    //模拟实现LinkedBlockingQueue的put和take方法
    //1. 需要一个承装元素集合
    private LinkedList<Object> list = new LinkedList<Object>();
    //2. 需要一个计数器
    private AtomicInteger count = new AtomicInteger(0);
    //3. 需要设定上下限
    private final int minSize = 0;
    private final int maxSize;

    //4. 构造方法
    public MyQueue(int maxSize) {
        this.maxSize = maxSize;
    }

    //5. 初始化一个object，用于加锁
    private final Object lock = new Object();

    //put(anObject): 把anObject加到BlockingQueue里,如果BlockingQueue没有空间,则调用此方法的线程被阻断，直到BlockingQueue里面有空间再继续.
    public void put(Object object) {
        synchronized (lock) {
            while(count.get() == this.maxSize){
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            //加入元素
            list.add(object);
            //计数器累加
            count.incrementAndGet();
            //唤醒另外线程
            lock.notify();
            System.out.println("新加入的元素为：" + object);
        }
    }

    //take: 取走BlockingQueue里排在首位的对象,若BlockingQueue为空,阻断进入等待状态直到BlockingQueue有新的数据被加入.
    public Object take(){
        Object ret = null;
        synchronized (lock) {
            while(count.get() == this.minSize) {
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            //移除首位元素
            ret = list.removeFirst();
            //计数器递减
            count.decrementAndGet();
            //唤醒另外线程
            lock.notify();
            System.out.println("取出的元素为：" + ret);
        }
        return ret;
    }

    public int size(){
        return this.count.get();
    }

}
