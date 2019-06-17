package com.zxc.dao;

import com.zxc.model.Admin;
import com.zxc.model.Student;
import com.zxc.model.Teacher;

import java.util.List;

public interface UserDao {
    public Student selectStuById(int id);
    public Teacher selectTeaById(int id);
    public Admin selectAdminById(int id);
    public void updateStuPass(Student student);
    public void updateTeaPass(Teacher teacher);
    public List<Teacher> queryAllTeacher();
    public List<Student> queryAllStudent();
    public void updateAdminPass(Admin admin);
    public void insertStudent(Student student);
    public void removeStudent(int stuId);
    public void updateStudent(Student student);
    public String queryInsNameById(int insId);
    public void insertTeacher(Teacher teacher);
    public void deleteTeacher(int teaId);
    public void updateTeacher(Teacher teacher);
}
