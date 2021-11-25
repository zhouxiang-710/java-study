package InterviewFromXiongJG.test1;

import lombok.Data;

@Data
public class User {
    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }

    private String name;
    private int age;

}
