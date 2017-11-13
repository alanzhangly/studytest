package com.alan.todo;

import java.util.BitSet;

/**
 * 布隆过滤器, 用于判定某个URL是否已经存在
 * @author Ke Zhang
 * @date 2017/10/24
 */
public class SimpleBloomFilter {

    //目前能处理4194304个URL
    private static final int DEFAULT_SIZE = 2 << 24;
    //一个URL对应8 位，误判率为0.021577
    private static final int[] seeds = new int[] { 7, 11, 13, 31, 37, 61, 73, 96};
    //容量为DEFAULT_SIZE的位存储器
    private BitSet bits = new BitSet(DEFAULT_SIZE);
    //随机数产生器
    private SimpleHash[] func = new SimpleHash[seeds.length];

    public static void main(String[] args) {
        String value = "stone2083@yahoo.cn";
//        SimpleBloomFilter filter = new SimpleBloomFilter();
//        System.out.println(filter.contains(value));
//        filter.add(value);
//        System.out.println(filter.contains(value));
        System.out.println(2 << 21);  //3355 4432
//        for (SimpleHash f : new SimpleBloomFilter().func) {
//            System.out.println(f.hash(value));
//        }
    }

    public SimpleBloomFilter() {
        //初始化随机数产生器
        for (int i = 0; i < seeds.length; i++) {
            func[i] = new SimpleHash(DEFAULT_SIZE, seeds[i]);
        }
    }

    // 覆盖方法，把URL 添加进来
    public void add(CrawlUrl value) {
        if (value != null) {
            add(value.getOriUrl());
        }
    }

    // 覆盖方法，把URL 添加进来
    public void add(String value) {
        for (SimpleHash f : func) {
            bits.set(f.hash(value), true);
        }
    }

    // 覆盖方法，是否包含URL
    public boolean contains(CrawlUrl value) {
        return contains(value.getOriUrl());
    }

    // 覆盖方法，是否包含URL
    public boolean contains(String value) {
        if (value == null) {
            return false;
        }
        boolean ret = true;
        for (SimpleHash f : func) {
            ret = ret && bits.get(f.hash(value));
        }
        return ret;
    }

    public static class SimpleHash {

        private int cap;
        private int seed;

        public SimpleHash(int cap, int seed) {
            this.cap = cap;
            this.seed = seed;
        }

        public int hash(String value) {
            int result = 0;
            int len = value.length();
            for (int i = 0; i < len; i++) {
                result = seed * result + value.charAt(i);
            }
            return (cap - 1) & result;
        }
    }
}
