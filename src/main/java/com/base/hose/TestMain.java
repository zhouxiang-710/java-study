package com.base.hose;

import java.util.ArrayList;
import java.util.List;

public class TestMain {
    public static void main(String[] args) {
        TestVo bean1 = new TestVo(1, "bean1");
        TestVo bean2 = new TestVo(2, "1", "bean1-1");
        TestVo bean3 = new TestVo(3, "2", "bean1-1-1");
        List<Object> ls = new ArrayList<>();
        ls.add(bean1);
        ls.add(bean2);
        ls.add(bean3);

        //DataObject.makeTree(ls,"", (p,children)->TestListVo.of(p,children));

    }
}
