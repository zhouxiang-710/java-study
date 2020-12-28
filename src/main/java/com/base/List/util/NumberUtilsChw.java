package com.base.List.util;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class NumberUtilsChw {

    /**
     * 判断数字是否是正数
     */
    public static boolean checkNumberLargerZero(Integer number){
        return (number!=null && number>0);
    }

    public static boolean checkNumberLargerZero(Long number){
        return (number!=null && number>0);
    }

    /**
     * 获取字符串中最左边的数字
     */
    public static int getFirstNumberInString(String string){
        return getIndexNumberInString(string,0);
    }

    /**
     * 获取字符串中左边第（index+1）个数字
     */
    public static int getIndexNumberInString(String string,int index){
        if(string==null || string.isEmpty()){
            return 0;
        }
        byte[] bytes = new byte[9];
        int find=0;
        int location=0;
        for (byte b : string.getBytes()) {
            boolean is = false;
            if(b>47 && b<58){
                is=true;
                bytes[location]=b;
                location++;
            }
            if((!is && location>0) || location>8){
                if(find<index){
                    find++;
                    bytes = new byte[9];
                    location=0;
                }else {
                    break;
                }
            }
        }
        if(find<index || location==0){
            return 0;
        }
        Integer integer = Integer.valueOf(new String(bytes).trim());
        return integer;
    }

    /**
     * 根据字符串中包含的数字比较字符串
     * 可用于比较题号、班级
     */
    public static int compareMixNumberString(String str1,String str2){
        int count=0;
        while (count<3){
            int i = getIndexNumberInString(str1, count) - getIndexNumberInString(str2, count);
            if(i==0){
                count++;
            }else {
                return i;
            }
        }
        return 0;
    }

    public static void main(String[] args) {
        String s = "你好";
        for (byte b : s.getBytes()) {
            System.out.println(b );

        }
        System.out.println("--------------" );

        String s2 = "AZaz";
        for (byte b : s2.getBytes()) {
            System.out.println(b );

        }
        System.out.println("--------------" );

        String s3 = "134";
        for (byte b : s3.getBytes()) {
            System.out.println(b );

        }

        List<String> test=new ArrayList<>();
        test.add("六1");
        test.add("英语六7");
        test.add("数学六4");
        test.add("语文五7");

        test.add("六3");
        test.add("六2");

        /*test.add("61-70(1)");
        test.add("61-70(2)");
        test.add("55-59");
        test.add("5");
        test.add("3");*/
        test.sort(NumberUtilsChw::compareMixNumberString);
        System.out.println(test);
        List<String> list = new ArrayList<>();
        list.add("高一11班");
        list.add("高二5班");
        list.add("高一7班");
        list.add("高二6班");

        //list.sort(NumberUtilsChw::compareMixNumberString);
        list.sort(Comparator.comparing(NumberUtilsChw::getFirstNumberInString));
        System.out.println(list);
    }


}
