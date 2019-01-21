package com.lyp.test.bloomfilter;

import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;

/**
 * <p>@filename BloomFilterTest</p>
 * <p>
 * <p>@description main函数测试MyBloomFilter和Guava的工具包</p>
 *
 * @author liyupeng
 * @version 1.0
 * @since 2019/1/14 14:56
 **/
public class BloomFilterTest {
    //main函数测试
    public static void main(String[] args) {
        myBloomFilterTest();
        //guavaBloomFilterTest();
    }

    public static void guavaBloomFilterTest(){
        long star = System.currentTimeMillis();

        BloomFilter<Integer> bloomFilter = GuavaBloomFilter.getBloomFilter(Funnels.integerFunnel(), 10000000,0.1);

        for (int i = 0; i < 10000000; i++) {
            bloomFilter.put(i) ;
        }

        System.out.println(bloomFilter.mightContain(99999));
        System.out.println(bloomFilter.mightContain(88888));
        System.out.println(bloomFilter.mightContain(10000000));

        long end = System.currentTimeMillis();

        System.out.println("执行时间：" + (end - star));
    }

    public static void myBloomFilterTest(){
        long star = System.currentTimeMillis();
        double falsePositiveProbability = 0.1;
        int expectedSize = 10000000;
        MyBloomFilter<Integer> myBloomFilter = new MyBloomFilter<Integer>(falsePositiveProbability, expectedSize);
        for (int i = 0; i < 10000000; i++) {
            myBloomFilter.add(i) ;
        }

        System.out.println(myBloomFilter.contains(99999));
        System.out.println(myBloomFilter.contains(88888));
        System.out.println(myBloomFilter.contains(10000000));

        long end = System.currentTimeMillis();

        System.out.println("执行时间：" + (end - star));
    }

}
