package com.base.hose;

import java.util.List;

public class TestListVo {
    private TestVo  testvo;
    private List<TestListVo> testListVo;

    public TestListVo(TestVo testvo, List<TestListVo> testListVo) {
        this.testvo = testvo;
        this.testListVo = testListVo;
    }


    public TestVo getTestvo() {
        return testvo;
    }

    public void setTestvo(TestVo testvo) {
        this.testvo = testvo;
    }

    public List<TestListVo> getTestListVo() {
        return testListVo;
    }

    public void setTestListVo(List<TestListVo> testListVo) {
        this.testListVo = testListVo;
    }
    public static TestListVo of(TestVo testvo, List<? extends TestListVo> children) {
        return new TestListVo(testvo, (List<TestListVo>) children);
    }
 }
