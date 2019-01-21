package com.lyp.test.bloomfilter;

import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnel;
import com.google.common.hash.Funnels;

/**
 * <p>@filename GuavaBloomFilter</p>
 * <p>
 * <p>@description 测试Guava包下已实现的布隆过滤器</p>
 *
 * @author liyupeng
 * @version 1.0
 * @since 2019/1/14 14:51
 **/
public class GuavaBloomFilter {

    public static BloomFilter<Integer> getBloomFilter(Funnel<Integer> funnel, int expectedInsertions, double fpp){
        return BloomFilter.create(funnel, expectedInsertions, fpp);
    }

    public static Boolean contains(BloomFilter<Integer> filter, Integer obj) {
        return filter.mightContain(obj);
    }

}
