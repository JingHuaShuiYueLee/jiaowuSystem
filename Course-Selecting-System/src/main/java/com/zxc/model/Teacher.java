package com.zxc.model;

public class Teacher {
    private int teaId;
    private String teaName;
    private String teaPass;
    private String sex;
    private int isWork;//是否正在任职状态，1表示正在任职，0表示离职或退休
    private int pId;
    private String pName;//增加表没有的字段

    public String getpName() {
        return pName;
    }

    public void setpName(String pName) {
        this.pName = pName;
    }

    public int getpId() {
        return pId;
    }

    public void setpId(int pId) {
        this.pId = pId;
    }

    public int getIsWork() {
        return isWork;
    }

    public void setIsWork(int isWork) {
        this.isWork = isWork;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public void setTeaId(int teaId) {
        this.teaId = teaId;
    }

    public void setTeaName(String teaName) {
        this.teaName = teaName;
    }

    public void setTeaPass(String teaPass) {
        this.teaPass = teaPass;
    }

    public int getTeaId() {
        return teaId;
    }

    public String getTeaName() {
        return teaName;
    }

    public String getTeaPass() {
        return teaPass;
    }
}
