package com.zxc.service.impl;

import com.zxc.dao.CourseDao;
import com.zxc.dao.TitleDao;
import com.zxc.dao.UserDao;
import com.zxc.model.*;
import com.zxc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private CourseDao courseDao;

    @Autowired
    private TitleDao titleDao;

    @Override
    public int checkAccount(int id, String pass) {
        if(Integer.toString(id).charAt(4)=='1'){
            if(userDao.selectTeaById(id).getTeaPass().equals(pass))
                return 2;
            else
                return 0;
        }
        else if(Integer.toString(id).charAt(4)=='2'){
            if(userDao.selectAdminById(id).getAdminPass().equals(pass))
                return 3;
            else
                return 0;
        }
        else {
            if(userDao.selectStuById(id).getStuPass().equals(pass))
                return 1;
            else
                return 0;
        }
    }

    @Override
    public String getStuNameById(int id) {
        return userDao.selectStuById(id).getStuName();
    }

    @Override
    public String getTeaNameById(int id) {
        return userDao.selectTeaById(id).getTeaName();
    }

    @Override
    public String getAdminNameById(int id) {
        return userDao.selectAdminById(id).getAdminName();
    }

    @Override
    public Student getStuInfoById(int id) {
        return userDao.selectStuById(id);
    }

    @Override
    public Teacher getTeaInfoById(int id) {
        return userDao.selectTeaById(id);
    }

    @Override
    public Admin getAdminInfoById(int id) {
        return userDao.selectAdminById(id);
    }

    @Override
    public void changeStuPass(Student student) {
        userDao.updateStuPass(student);
    }

    @Override
    public void changeTeaPass(Teacher teacher) {
        userDao.updateTeaPass(teacher);
    }

    @Override
    public List<Teacher> queryAllTeacher() {
        return userDao.queryAllTeacher();
    }

    @Override
    public List<Student> queryAllStudent() {
        return userDao.queryAllStudent();
    }

    @Override
    public void changeAdminPass(Admin admin) {
        userDao.updateAdminPass(admin);
    }

    @Override
    public void insertStudent(Student student) {
        int insId = student.getInsId();
        student.setInsName(userDao.queryInsNameById(insId));
        userDao.insertStudent(student);
    }

    @Override
    public void removeStudent(int id) {//涉及到三张表：course、Course_choose、student表
        List<Integer> list = courseDao.queryCourseIdByStuId(id);
        for(int i : list) {
            Course course = courseDao.queryCourseInfoById(i);
            courseDao.downChooseNum(course.getClassId());
            Course_choose cc = new Course_choose();
            cc.setClassId(i);
            cc.setStuId(id);
            courseDao.deleteCourseChoose(cc);
        }
        userDao.removeStudent(id);
    }

    @Override
    public void updateStudent(Student student) {
        student.setInsName(userDao.queryInsNameById(student.getInsId()));//设置好后再存进去
        userDao.updateStudent(student);
    }

    @Override
    public void insertTeacher(Teacher teacher) {
        userDao.insertTeacher(teacher);
    }

    @Override
    public void removeTeacher(int teaId) {
        //courseDao.deleteCourseByTeaId(teaId);把该老师教的所有课程删去
        userDao.deleteTeacher(teaId);//假删除，isWork为1即在职状态，为0就是退休或离职
    }

    @Override
    public void updateTeacher(Teacher teacher) {
        userDao.updateTeacher(teacher);
    }
}
