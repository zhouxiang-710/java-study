package com.base.hose;

public class TestVo implements DataObject.HierarchyLike {
    private Integer id;
    private String parentId;
    private String name;


    public TestVo(int id, String name) {
        this.id=id;
        this.name=name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public TestVo(Integer id, String parentId, String name) {
        this.id = id;
        this.parentId = parentId;
        this.name = name;
    }

    @Override
    public String toString() {
        return "TestVo{" +
                "id=" + id +
                ", parentId=" + parentId +
                ", name='" + name + '\'' +
                '}';
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String id() {
        return null;
    }

    @Override
    public String parentId() {
        return null;
    }
}
