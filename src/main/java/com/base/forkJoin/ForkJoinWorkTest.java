package com.base.forkJoin;

import org.junit.Test;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.stream.LongStream;

/**
 * @ Author     ：zhouxiang.
 * @ Date       ：Created in 11:17 2020/9/30
 * @ Description：${description}
 * @ Modified By：
 * @Version: $version$
 */

public class ForkJoinWorkTest {


    @Test
    public void test() {
        //ForkJoin实现
        long l = System.currentTimeMillis();
        ForkJoinPool forkJoinPool = new ForkJoinPool();//实现ForkJoin 就必须有ForkJoinPool的支持
        ForkJoinTask<Long> task = new ForkJoinWork(0L, 10000000000L);//参数为起始值与结束值
        Long invoke = forkJoinPool.invoke(task);
        long l1 = System.currentTimeMillis();
        System.out.println("invoke = " + invoke + "  time: " + (l1 - l));
        //invoke = -5340232216128654848  time: 93520
        //ForkJoinWork forkJoinWork = new ForkJoinWork(0L, 10000000000L);
    }

    @Test
    public void test2() {
        //普通线程实现
        Long x = 0L;
        Long y = 10000000000L;
        long l = System.currentTimeMillis();
        for (Long i = 0L; i <= y; i++) {
            x += i;
        }
        long l1 = System.currentTimeMillis();
        System.out.println("invoke = " + x + "  time: " + (l1 - l));
        //invoke = -5340232216128654848  time: 146309
    }

    @Test
    public void test3() {
        //Java 8 并行流的实现
        long l = System.currentTimeMillis();
        long reduce = LongStream.rangeClosed(0, 10000000000L).parallel().reduce(0, Long::sum);
        //long reduce = LongStream.rangeClosed(0, 10000000000L).parallel().reduce(0, (a, b) -> a+b);
        long l1 = System.currentTimeMillis();
        System.out.println("invoke = " + reduce + "  time: " + (l1 - l));

        //invoke = -5340232216128654848  time: 5512
    }
    @Test
    public void test4() {
        //Java 8 并行流的实现
        long l = System.currentTimeMillis();
        long reduce = LongStream.rangeClosed(0, 10000000000L).sequential().reduce(0, Long::sum);
        //long reduce = LongStream.rangeClosed(0, 10000000000L).parallel().reduce(0, (a, b) -> a+b);
        long l1 = System.currentTimeMillis();
        System.out.println("invoke = " + reduce + "  time: " + (l1 - l));

        //invoke = -5340232216128654848  time: 10117


    }
}

