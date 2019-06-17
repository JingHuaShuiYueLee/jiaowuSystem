package com.zxc.model;

import java.util.List;

public class Course {
    private int classId;
    private String className;
    private int classNum;
    private int teaId;
    private String teaName;//表没有的字段这里可以有
    private int classChooseNum;
    private List<String> classLimitInsName;//表没有的字段这里可以有
    private int score;//表没有的字段这里可以有
    private int isChoose;//表没有的字段这里可以有
    private int isScored;//表没有的字段这里可以有
    private double credit;

    public double getCredit() {
        return credit;
    }

    public void setCredit(double credit) {
        this.credit = credit;
    }

    public int getIsScored() {
        return isScored;
    }

    public void setIsScored(int isScored) {
        this.isScored = isScored;
    }

    public void setClassId(int classId) {
        this.classId = classId;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public void setClassNum(int classNum) {
        this.classNum = classNum;
    }

    public void setTeaId(int teaId) {
        this.teaId = teaId;
    }

    public void setClassChooseNum(int classChooseNum) {
        this.classChooseNum = classChooseNum;
    }

    public void setClassLimitInsName(List<String> classLimitInsName) {
        this.classLimitInsName = classLimitInsName;
    }

    public void setTeaName(String teaName) {
        this.teaName = teaName;
    }

    public void setIsChoose(int isChoose) {
        this.isChoose = isChoose;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getClassId() {
        return classId;
    }

    public String getClassName() {
        return className;
    }

    public int getClassNum() {
        return classNum;
    }

    public int getTeaId() {
        return teaId;
    }

    public int getClassChooseNum() {
        return classChooseNum;
    }

    public List<String> getClassLimitInsName() {
        return classLimitInsName;
    }

    public String getTeaName() {
        return teaName;
    }

    public int getIsChoose() {
        return isChoose;
    }

    public int getScore() {
        return score;
    }
}

