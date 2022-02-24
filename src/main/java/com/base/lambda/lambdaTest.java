package com.base.lambda;

import org.junit.Test;

import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.DoubleBinaryOperator;
import java.util.function.IntFunction;
import java.util.function.ToIntBiFunction;
import java.util.function.ToIntFunction;

/**
 * @author zhou xiang
 * @date 2022/2/24
 **/
public class lambdaTest {
    @Test
    public void test(){
        Comparator<Integer> com = (x, y) -> {
            System.out.println("函数式接口");
            return Integer.compare(x, y);
        };


        ToIntBiFunction toInt = (x, y) -> {
            System.out.println("函数式接口");
            int compare = Integer.compare((int)x,(int) y);
            return compare;
        };
        System.out.println(com.compare(5,6));
        System.out.println(toInt.applyAsInt(5,6));

        ToIntFunction<String> sToInt =
         (x)->{
            return Integer.valueOf(x);
        };
        System.out.println(sToInt.applyAsInt("6"));

        // 指定要创建的数组类型
      //  IntFunction<Integer[]> intFunction = Integer[]::new;
        IntFunction<Integer[]> intFunction = Integer[]::new;
         // 创建一个指定长度的数组
        Integer[] dates = intFunction.apply(10);
        System.out.println(dates.toString() +": length:"+dates.length);


        IntFunction<String> getMonthName = monthNo -> {
            Map<Integer, String> months = new HashMap<>();
            months.put(1, "January");
            months.put(2, "February");
            months.put(3, "March");
            months.put(4, "April");
            months.put(5, "May");
            months.put(6, "June");
            months.put(7, "July");
            months.put(8, "August");
            months.put(9, "September");
            months.put(10, "October");
            months.put(11, "November");
            months.put(12, "December");
            if(months.get(monthNo)!= null) {
                return months.get(monthNo);
            } else {
                return "The number must between 1 to 12";
            }
        };
        int input = 1;
        String month = getMonthName.apply(input);
        System.out.println("Month number "+ input +" is: "+ month);
        input = 10;
        System.out.println("Month number "+ input +" is: "+ getMonthName.apply(input));
        input = 15;
        System.out.println("Month number "+ input +" is: "+ getMonthName.apply(input));
    }

}
