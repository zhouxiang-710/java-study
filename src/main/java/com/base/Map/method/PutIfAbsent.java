package com.base.Map.method;


import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class PutIfAbsent {

    private Map<Integer ,String> map =new HashMap();
    @Before
    public void  addMap(){
        map.put(1,"张三");
        map.put(2,"李四");
    }
    /*
     *put
     */
    @Test
    public void put(){
        map.put(1,"wangWu");
        map.forEach((k,v)->{
            System.out.println("key:  "+ k + " value: "+v);
        });
    }
    /**
     *putIfAbsent  若不存在v，返回值为null，插入一对k-v;   若存在，则底层不覆盖，直接返回其v
     */
    @Test
    public void putIfAbsent(){
        map.putIfAbsent(1,"王五");
        String s = map.putIfAbsent(3, "王五");
        System.out.println(s);
        map.forEach((key,value) ->{
            System.out.println("key : " + key + " , value : " + value);
        });
    }
    /**
     * 8.computeIfAbsent 方法
     * 如果指定的key不存在，则通过指定的K -> V计算出新的值设置为key的值   return 最终的value
     * 指定的 key 存在时，不做任何操作，直接返回老值
     */
    @Test
    public void computeIfAbsent(){
        LinkedHashMap<String, String> hsahMap = new LinkedHashMap<>();
        map.computeIfAbsent(1, k->("王五1"));
        String s = map.computeIfAbsent(3, k -> (String.valueOf(k + 5)));
        System.out.println(s);
        map.forEach((key,value) ->{
            System.out.println("key : " + key + " , value : " + value);
        });
    }
    /**
     * 8.computeIfPresent 方法
     * key 存在时i，设置new value; 若new value为null ，删除该key
     */
    @Test
    public void computeIfPresent(){
        map.computeIfPresent(1, (k,v)->(null));
        String s = map.computeIfPresent(3, (k,v) -> (String.valueOf(k + 5)));
        System.out.println(s);
        map.forEach((key,value) ->{
            System.out.println("key : " + key + " , value : " + value);
        });
    }
}
