package InterviewFromXiongJG;

import InterviewFromXiongJG.test1.User;

import java.util.ArrayList;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @ Author     ：zhouxiang.
 * @ Date       ：Created in 22:50 2021/3/7
 * @ Description：${description}
 * @ Modified By：
 * @Version: $version$
 */
public class test {
    public static void main(String[] args) {

        ArrayList<User> users = new ArrayList<>();
        User a = new User("张三", 15);
        User b = new User("张三", 15);
        User c = new User("张三", 13);
        User d = new User("张三", 13);
        User e = new User("张三", 16);
        User f = new User("张三", 15);
        users.add(a);
        users.add(b);
        users.add(c);
        users.add(d);
        users.add(e);
        users.add(f);
        Map<Integer, Long> collect = users.stream().collect(Collectors.groupingBy(h -> h.getAge(), Collectors.counting()));
        collect.forEach((k,v)->{
            System.out.println("k="+k+",v="+v);
        });
    }

}
