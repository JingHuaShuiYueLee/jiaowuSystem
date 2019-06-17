package com.zxc.model;

//选课学院限制
public class Course_limit {//多对多的关系要建立一个表,课程和学院是多对多的关系
    private int classId;
    private int insId;

    public void setClassId(int classId) {
        this.classId = classId;
    }

    public void setInsId(int insId) {
        this.insId = insId;
    }

    public int getClassId() {
        return classId;
    }

    public int getInsId() {
        return insId;
    }
}
