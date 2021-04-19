package inteviewQues;

import java.util.ArrayList;
import java.util.List;

/**
 * @ Author     ：zhouxiang.
 * @ Date       ：Created in 14:57 2021/4/19
 * @ Description：实现一个一位数字的四则运算计算函数，输入是一个char数组，输出是计算结果。比如，输入是 char[] inputs = {'1','+','2','*','3'}，输出是7。输入的字符里面，如果是数字，则只有1位；如果是符号，则只有加减乘除四个运算符，没有括号
 * @ Modified By：
 * @Version: $version$
 */
public class Caculation {
    public static double doCalculate(char[] expression) {
        if (null == expression || expression.length == 0) {
            return 0;
        }
        List<String> que = new ArrayList<>();
        for (int i = 0; i < expression.length; i++) {
            if ((expression[i] >= '0' & expression[i] <= '9') || expression[i] == '+' || expression[i] == '-') {
                que.add(expression[i] + "");
            } else if (expression[i] == '*') {
                double pre = Double.parseDouble(que.get(que.size() - 1));
                double succ = Double.parseDouble(expression[++i] + "");
                double temp = pre * succ;
                que.remove(que.size() - 1);
                que.add(temp + "");
            } else if (expression[i] == '/') {
                double pre = Double.parseDouble(que.get(que.size() - 1));
                double succ = Double.parseDouble(expression[++i] + "");
                double temp = pre * 1.0 / succ;
                que.remove(que.size() - 1);
                que.add(temp + "");
            }
        }
        double ret = Double.parseDouble(que.get(0));
        for (int i = 1; i < que.size(); i++) {
            // 加减运算
            String val = que.get(i);
            if (val.equals("+")) {
                ret = ret + Double.parseDouble(que.get(++i));
            } else if (val.equals("-")) {
                ret = ret - Double.parseDouble(que.get(++i));
            }
        }
        return ret;
    }
    public static void main(String[] args) {
        char[] expression = {'3', '+', '2', '*', '5'};
        System.out.println(doCalculate(expression));
    }
}