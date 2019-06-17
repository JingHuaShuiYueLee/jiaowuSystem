package com.zxc.service;

import com.zxc.model.Admin;
import com.zxc.model.Student;
import com.zxc.model.Teacher;

import java.util.List;

public interface UserService {
    public int checkAccount(int id,String pass);
    public String getStuNameById(int id);
    public String getTeaNameById(int id);
    public String getAdminNameById(int id);
    public Student getStuInfoById(int id)throws NullPointerException;
    public Teacher getTeaInfoById(int id) throws NullPointerException;
    public Admin getAdminInfoById(int id);
    public void changeStuPass(Student student);
    public void changeTeaPass(Teacher teacher);
    public List<Teacher> queryAllTeacher();
    public List<Student> queryAllStudent();
    public void changeAdminPass(Admin admin);
    public void insertStudent(Student student);
    public void removeStudent(int id);
    public void updateStudent(Student student);
    public void insertTeacher(Teacher teacher);
    public void removeTeacher(int teaId);
    public void updateTeacher(Teacher teacher);

}
