package com.base.controller;

import com.base.Vo.GradeVo;
import org.junit.Test;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@RestController
public class SomeController {

    @GetMapping("/some")
    public String someHandle() {
        return "hello spring boot world";
    }

    @Test
    public  void test2() {
        List<GradeVo> scoreAnalysis = new ArrayList<>();
        GradeVo gradeVo = new GradeVo();
        gradeVo.setGradeId(11);
        scoreAnalysis.add(gradeVo);
        System.out.println(scoreAnalysis);
    }
    @Test
    public void test(){
        do {
            System.out.println("hello--");
            return ;
        } while (true);
    }

    @Test
    public int[][] add(){
        HashMap<Object, Object> hashMap = new HashMap<>();
        hashMap.put(null,null);
        Hashtable<Object, Object> hashtable = new Hashtable<>();
        hashtable.put(null, null);
        System.out.println(hashtable.get("l"));
        ConcurrentHashMap<Object, Object> concurrentHashMap = new ConcurrentHashMap<>();
        Object put = concurrentHashMap.put("s", "s");
        int [][] a=  {{1,2,3},{2,3,4}};
        int k;
        for (int i = 0; i < 5; i++) {
            int j = test5();

            if(j==5){
                break;
            }else{
                //
            }
        }


        return a;
    }

    @Test
    public int test5(){
        String test = "1,2,3,4" ;
        List<String> strings = Arrays.asList(test);

        System.out.println(strings.get(1));
        test.split(",");
        return 5;
    }


}
class test{
    public static void main(String[] args) {
            String test = "1" ;
        String[] split = test.split(",");
       // List<String> strings = Arrays.asList(test,"a");
        List<String> list = Arrays.asList(split);
            System.out.println(list.get(0));
        //System.out.println(strings.get(1));
    }
}
