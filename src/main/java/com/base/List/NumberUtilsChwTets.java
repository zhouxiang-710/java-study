package com.base.List;

import com.base.List.Vo.SimpleQuestionVO;
import com.base.List.util.NumberUtilsChw;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @ Author     ：zhouxiang.
 * @ Date       ：Created in 16:31 2020/9/10
 * @ Description：${description}
 * @ Modified By：
 * @Version: $version$
 */
public class NumberUtilsChwTets {
    public static List<SimpleQuestionVO> list=new ArrayList<>();
    {
        SimpleQuestionVO gardeVo = new SimpleQuestionVO();
        gardeVo.setQuestionNumber("14");
        gardeVo.setQuestionName("张三");

        SimpleQuestionVO gardeVo1 = new SimpleQuestionVO();
        gardeVo1.setQuestionNumber("13");
        gardeVo1.setQuestionName("李四");

        SimpleQuestionVO gardeVo2 = new SimpleQuestionVO();
        gardeVo2.setQuestionNumber("17");
        gardeVo2.setQuestionName("王五");

        SimpleQuestionVO gardeVo3 = new SimpleQuestionVO();
        gardeVo3.setQuestionNumber("11");
        gardeVo3.setQuestionName("赵柳");

        list =new ArrayList<>();
        list.add(gardeVo);
        list.add(gardeVo1);
        list.add(gardeVo2);
        list.add(gardeVo3);
        System.out.println("原list——>"+list);
    }
    @Test
    public void sort(){
       // list.sort((o1,o2) -> NumberUtilsChw.compareMixNumberString(o1.getQuestionNumber(),o2.getQuestionNumber()));
        list.sort((o1,o2)->NumberUtilsChw.compareMixNumberString(o1.getQuestionNumber(),o2.getQuestionNumber()));
        System.out.println(list);
    }


}
