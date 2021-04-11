package com.base.StreamApi;

import com.base.Vo.GradeVo;
import org.junit.Test;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

/**
 * @ Author     ：zhouxiang.
 * @ Date       ：Created in 15:29 2020/7/23
 * @ Description：${description}
 * @ Modified By：
 * @Version: $version$
 */
public class StreamApitest1 {
    @Test
    public void test(){
        list=new ArrayList<>();
        String s = list.stream().map(GradeVo::getStudentName).collect(toList()).toString();
        System.out.println("这个是"+s.substring(1,s.length()-1));
        System.out.println("a"+Runtime.getRuntime().availableProcessors());
    }


    public static List<GradeVo> list=new ArrayList<>();
     //向list中塞值
    {   GradeVo gradeVo = new GradeVo();
        gradeVo.setGradeId(11);
        gradeVo.setStudentName("张三");

        GradeVo gradeVo1 = new GradeVo();
        gradeVo1.setGradeId(12);
        gradeVo1.setStudentName("李四");

        GradeVo gradeVo2 = new GradeVo();
        gradeVo2.setGradeId(13);
        gradeVo2.setStudentName("王五");

        GradeVo gradeVo3 = new GradeVo();
        gradeVo3.setGradeId(13);
        gradeVo3.setStudentName("赵柳");

        list =new ArrayList<>();
        list.add(gradeVo);
        list.add(gradeVo1);
        list.add(gradeVo2);
        list.add(gradeVo3);
        System.out.println("原list——>"+list);
    }
    /*TOdo 获取流
     */
    @Test
    public  void getStream(){

        //todo 集合
        // 这种数据源较为常用，通过stream()方法即可获取流对象：
        List<GradeVo> list = new ArrayList<GradeVo>();
        Stream<GradeVo> stream = list.stream();

        //todo 数组
        //通过Arrays类提供的静态函数stream()获取数组的流对象：
        String[] names = {"chaimm","peter","john"};
        Stream<String> stream2 = Arrays.stream(names);

        //todo 值
        //直接将几个值变成流对象：
        Stream<String> stream3 = Stream.of("chaimm","peter","john");
        System.out.println(stream3);

        //todo 文件
        Stream lines = null;
        try {
            lines = Files.lines(Paths.get("C:\\Users\\zhouxiang\\Desktop\\test.txt"), Charset.defaultCharset());
            Object[] objects = lines.toArray();
            System.out.println(objects);  // 经过测试，文件中如果是 hello <换行>  你好  ，结果输出 hello <换行>  你好
        } catch (IOException e) {
            e.printStackTrace();
        }

       // PS：Java7简化了IO操作，把打开IO操作放在try后的括号中即可省略关闭IO的代码。
    }

    /*TODO filter 筛选过滤
     */
    @Test
    public void filterTest(){
        //筛选filter
        List<GradeVo> result = list.stream()
                .filter(GradeVo::isHighGrade)
                .collect(toList());
        System.out.println("筛选filter->"+result);


    }
    /*todo distinct 去重
    */
    @Test
    public void distinct(){
        //去重distinct
        List<GradeVo> collect = list.stream().distinct().collect(toList());

        list.stream().distinct().collect(toList()).forEach(s->{
            System.out.println(s.toString());
        });//此处代码，是仅仅为了对比与System.out::println的区别。
        list.stream().distinct().collect(toList()).forEach(System.out::println);
        System.out.println("去重distinct->"+collect);
    }
    /*todo limit 截取
     *截取流的前N个元素
     */
    @Test
    public void limit(){
        list.stream().limit(2).collect(toList()).forEach(System.out::println);
    }
    /*todo skip
     *跳过流的前n个元素：
     */
    @Test
    public void skip(){
        list.stream().skip(2).collect(toList()).forEach(System.out::println);
    }
    /*todo 映射
    *对流中的每个元素执行一个函数，使得元素转换成另一种类型输出。
    * 流会将每一个元素输送给map函数，并执行map中的Lambda表达式，最后将执行结果存入一个新的流中。
    * 如，获取每个人的姓名(实则是将Perosn类型转换成String类型)
     */
    @Test
    public void map(){
        List<String> collect = list.stream().map(GradeVo::getStudentName).collect(toList());
        List<Integer> collect1 = list.stream().map(GradeVo::getGradeId).collect(toList());
        collect1.forEach(System.out::println);
    }

    /*todo 合并多个流

     */
    @Test
    public void flatMap(){
        List<String> list = new ArrayList<String>();
        list.add("I am a boy");
        list.add("I love the girl");
        list.add("But the girl loves another girl");
        Stream<Stream<String>> streamStream =
                 list.stream()
                .map(line -> line.split(" "))
                .map(Arrays::stream);//步骤一.这一步会将流变成一个个小流，所以应该用flatMap
        Stream<String> stringStream = list.stream()//Stream<string>
                .map(line -> line.split(" "))//Stream<String[]>
                .flatMap(Arrays::stream);//步骤二.map改为flatMap
        List<String> collect = list.stream()//Stream<string>
                .map(line -> line.split(" "))//Stream<String[]>
                .flatMap(Arrays::stream)//Stream<string>
                .distinct()//步骤三.去重
                .collect(toList());//转为list
        collect.forEach(System.out::println);
        
    }
    @Test
    /*todo anyMatch 是否匹配任意元素
    
     */
    public void anyMatch(){
        boolean b = list.stream().anyMatch(GradeVo::isHighGrade);
        boolean b1 = list.stream().anyMatch(s -> {
            if (s.getGradeId() > 50) {
                return true;
            }else{
                return false;
            }
        });
        System.out.println(b);
        System.out.println(b1);
    }
    /*todo allMatch 是否匹配所有元素

     */
    @Test
    public void allMatch(){
        boolean b = list.stream().allMatch(GradeVo::isHighGrade);
        System.out.println(b);
    }
}
