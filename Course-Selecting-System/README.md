# Course-Selecting-System
基于ssm框架的简单选课系统

## 基本功能
### 学生部分
#### 登录、修改密码
#### 查询个人信息
#### 查询排课情况、根据老师姓名/学院筛选、进行选课、查询个人选课情况、查询成绩

### 教师部分
#### 登录、修改密码
#### 查询个人信息
#### 查询个人开课情况、添加、修改、删除课程
#### 查看课程选课情况、删除学生、评分

## 开发环境
### 后台基于maven使用SSM框架整合(Spring、SpringMVC、Mybatis)
### 前端jsp页面样式基于layui框架，bootstrap和jquery
### ide：Intellij IDEA 2017.2.4
### java版本：jdk1.8.0_152
### 数据库版本：mysql 5.7.18-1

注意：这个系统原来是github另外一个作者开发的，现在这个我只是在他/她的基础上进行了改善，增加了一些功能和修复了一些bug
## 数据库设计
有学生、教师、学院、课程、课程的学院限制、课程选择、管理员、教师职称表八张表
数据库名字：jiaowuSystem
先用Navicat建一个数据库，然后运行以下脚本文件

## 简单展示
学生
### 登录
数据表中有10个学生账号（2018000001--2018000010）和4个教师账号（2018100001--2018100004），密码和账号一样
![登录](https://github.com/Zeng1998/Course-Selecting-System/raw/master/Screenshots/1.png)
### 首页
![首页](https://github.com/Zeng1998/Course-Selecting-System/raw/master/Screenshots/2.png)
### 个人信息
![个人信息](https://github.com/Zeng1998/Course-Selecting-System/raw/master/Screenshots/3.png)
### 修改密码
![修改密码](https://github.com/Zeng1998/Course-Selecting-System/raw/master/Screenshots/4.png)
### 开课列表
![开课列表](https://github.com/Zeng1998/Course-Selecting-System/raw/master/Screenshots/5.png)
### 课程详情
![课程详情](https://github.com/Zeng1998/Course-Selecting-System/raw/master/Screenshots/6.png)
### 选课确认
![选课确认](https://github.com/Zeng1998/Course-Selecting-System/raw/master/Screenshots/7.png)
### 已选列表
![已选列表](https://github.com/Zeng1998/Course-Selecting-System/raw/master/Screenshots/8.png)
在已选列表中可以看到老师已经给分的课程成绩和获得学分，如果老师没给分的课程那么成绩不会显示（不是上面那个图）。
### 退课
![退课](https://github.com/Zeng1998/Course-Selecting-System/raw/master/Screenshots/9.png)
如果老师已经打分了，那么就不可以退课；如果老师没打分就可以退课（通过数据库表的isScored字段进行控制）
### 我的成绩页面
增加了我的成绩页面，可以看到学生所修的总学分和学分绩gpa，只有大于60分才能拿到该课程学分。学分绩gpa计算方式自己百度学分绩计算方式。

教师
### 开课列表
![开课列表](https://github.com/Zeng1998/Course-Selecting-System/raw/master/Screenshots/10.png)
### 添加新课程
![添加新课程](https://github.com/Zeng1998/Course-Selecting-System/raw/master/Screenshots/11.png)
### 修改课程
![修改课程](https://github.com/Zeng1998/Course-Selecting-System/raw/master/Screenshots/12.png)
### 删除课程
![删除课程](https://github.com/Zeng1998/Course-Selecting-System/raw/master/Screenshots/13.png)
### 课程管理
![课程管理](https://github.com/Zeng1998/Course-Selecting-System/raw/master/Screenshots/14.png)
### 评分
![评分](https://github.com/Zeng1998/Course-Selecting-System/raw/master/Screenshots/15.png)
（评分会改变数据库表中isScored字段的值，一旦老师评分了那么isScored变成1，没评分是0）
### 删除学生
![删除学生](https://github.com/Zeng1998/Course-Selecting-System/raw/master/Screenshots/16.png)

管理员
管理员用来对全校师生进行管理
###首页（基本同上）
###个人资料（基本同上）
###管理学生
对学生进行增删改查
因为会有新生进来-对应插入学生
会有学生退学毕业-对应删除学生（这里我的实现是真实删除，用delete的sql语句）
会有学生信息变更-对应学生会转专业或者忘记密码等等
查询学生
###管理老师
对老师进行增删改查
因为会有新老师入职进来-对应插入老师
会有老师离职或者退休-对应删除老师（这里我的实现是假删除，用update的sql语句，设置一个标志变量isWork，为1表示在职状态，为0表示离职或退休）
会有老师信息变更-对应老师职称变更
查询老师

