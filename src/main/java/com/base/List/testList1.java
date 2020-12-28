package com.base.List;


import java.util.ArrayList;
import java.util.List;

public class testList1 {
    public static void main(String[] args) {

        List<String> list = new ArrayList<String>();
        list.add("a");
        list.add("b");
        list.add("c");
        list.add("c");
        list.add("d");
        list.add("e");
        System.out.println(list);

        exChange(list, 2, 4);
        System.out.println(list);

        String[] str = new String[6];
        list.toArray(str);
        resort(str);
        for (int i = 0; i < str.length; i++) {
            System.out.print(str[i]+" ");

        }
    }

    /*
     * (1)编写一个泛形方法，实现指定位置数组元素的交换
     */
    public static <E> List<E> exChange(List<E> list ,int arg1,int arg2){
        E e = list.get(arg2);
        list.set(arg2, list.get(arg1));
        list.set(arg1, e);
        return list;
    }

    /*
     * (2)编写一个泛形方法，接收一个任意数组，并反转数组中的所有元素
     *    就是一个普通的折半换位
     */
    public static <E> E[] resort(E[] arr ){
        for (int i = 0; i < arr.length/2; i++) {
            E med = null;
            med = (E) arr[i];
            arr[i] = arr[arr.length-i-1];
            arr[arr.length-i-1] = med;
        }
        return arr;
    }

}