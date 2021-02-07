package com.base.StreamApi;

import com.base.Vo.GradeVo;
import org.junit.Test;

import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * @ Author     ：zhouxiang.
 * @ Date       ：Created in 14:19 2020/9/30
 * @ Description：${description}
 * @ Modified By：
 * @Version: $version$
 */
public class testReduce {
    @Test
    public void testReduce() {
        Stream<Integer> stream = Arrays.stream(new Integer[]{1, 2, 3, 4, 5, 6, 7});

        //求集合元素只和
        Integer result = stream.reduce(1, Integer::sum);
        System.out.println(result);

        stream = Arrays.stream(new Integer[]{1, 2, 3, 4, 5, 6, 7});

        //求和
        stream.reduce((i, j) -> i + j).ifPresent(System.out::println);


        stream = Arrays.stream(new Integer[]{1, 2, 3, 4, 5, 6, 7});
        //求最大值
        stream.reduce(Integer::max).ifPresent(System.out::println);

        stream = Arrays.stream(new Integer[]{1, 2, 3, 4, 5, 6, 7});
        //求最小值
        stream.reduce(Integer::min).ifPresent(System.out::println);

        stream = Arrays.stream(new Integer[]{1, 2, 3, 4, 5, 6, 7});
        //做逻辑
        stream.reduce((i, j) -> i > j ? j : i).ifPresent(System.out::println);

        stream = Arrays.stream(new Integer[]{1, 2, 3, 4, 5, 6, 7});

        //求逻辑求乘积
        int result2 = stream.filter(i -> i % 2 == 0).reduce(1, (i, j) -> i * j);

        Optional.of(result2).ifPresent(System.out::println);


        GradeVo gradeVo = new GradeVo();
        Integer gradeId = 132;
        //Optional.of(gradeId).ifPresent(gradeVo::setGradeId);
        Optional.of(gradeId).ifPresent(a->{
            gradeVo.setGradeId(gradeId);
        });

        System.out.println(gradeVo);

    }

}
