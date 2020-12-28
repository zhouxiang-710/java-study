package com.base.Map;

/**
 * @ Author     ：zhouxiang.
 * @ Date       ：Created in 20:18 2020/7/23
 * @ Description：${description}
 * @ Modified By：
 * @Version: $version$
 */
public class App {
    public static void main(String[] args) {
     Map<String, String> map = new HashMap<String, String>();
     //App map=new App();
     map.put("张三", "张三");
     map.put("李四", "李四");
     map.put("王五", "王五");
     map.put("B哥", "帮B哥转发下吧");
     System.out.println(map.get("B哥"));
    }
    public void put(String key, String value){
        System.out.printf("key:%s:::::::::hash值:%s::::存储位置:%s\r\n", key, key.hashCode(), Math.abs(key.hashCode() % 15));
    }



}