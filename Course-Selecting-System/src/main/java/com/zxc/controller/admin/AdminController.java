package com.zxc.controller.admin;

import com.zxc.model.*;
import com.zxc.service.CourseService;
import com.zxc.service.PageService;
import com.zxc.service.ProfessionalTitleService;
import com.zxc.service.UserService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("admin")
public class AdminController {
    @Resource
    private UserService userService;
    @Resource
    private PageService pageService;
    @Resource
    private CourseService courseService;

    @Autowired
    private ProfessionalTitleService professionalTitleService;

    @RequestMapping("/adminIndex")
    public String adminIndex(){
        return "admin/adminIndex";
    }


    @RequestMapping("/adminInfo")
    public String adminInfo(@RequestParam("adminId") int id, Model model){
        model.addAttribute("admin",userService.getAdminInfoById(id));
        return "admin/adminInfo";
    }
    @RequestMapping("/editAdminPass")
    public String editAdminPass(){
        return "admin/editAdminPass";
    }

    @RequestMapping("/changeAdminPass")
    public String changPass(@RequestParam("prepass") String prepass, @RequestParam("nowpass") String nowpass, Model model, HttpServletRequest request){
        int id=(int)request.getSession().getAttribute("adminId");
        if(userService.checkAccount(id,prepass)==0){//查找数据库
            model.addAttribute("msg","原始密码输入错误!");
            return "admin/editAdminPass";
        }
        else{
            Admin admin =new Admin();
            admin.setAdminId(id);
            admin.setAdminPass(nowpass);
            userService.changeAdminPass(admin);
            model.addAttribute("admin",userService.getAdminInfoById(id));
            return "admin/adminInfo";
        }
    }

    @RequestMapping("/manageStudent")
    public String manageStudent(@Param("page") int page, Model model,HttpServletRequest request) {
        model.addAttribute("paging",pageService.subList(page,userService.queryAllStudent()));
        return "admin/studentList";
    }

    @RequestMapping("/manageTeacher")
    public String manageTeacher(@Param("page") int page, Model model,HttpServletRequest request) {
        List<Teacher> teaList = userService.queryAllTeacher();
        for(Teacher teacher : teaList) {
            teacher.setpName(professionalTitleService.queryPNameByPId(teacher.getpId()));
        }
        model.addAttribute("paging",pageService.subList(page,teaList));
        return "admin/teacherList";
    }

    @RequestMapping("searchTeacher")//搜索框输入老师编号
    public String searchTeacher(@RequestParam("teaId") int id,Model model) {
        List<Teacher> list = new ArrayList<>();
        try {
            Teacher teacher = userService.getTeaInfoById(id);
            teacher.setpName(professionalTitleService.queryPNameByPId(teacher.getpId()));
            list.add(teacher);
        } catch (NullPointerException e) {
            System.out.println(e);
        }
        model.addAttribute("paging",pageService.subList(1,list));
        return "admin/teacherList";
    }
    @RequestMapping("searchStudent")
    public String searchStudent(@RequestParam("stuId") int id,Model model) {
        List<Student> list = new ArrayList<>();
        try {
            list.add(userService.getStuInfoById(id));
        } catch (NullPointerException e) {
            System.out.println(e);
        }
        model.addAttribute("paging",pageService.subList(1,list));
        return "admin/studentList";
    }

    @RequestMapping("preInsertStudent")
    public String toInsertStudent(Model model) {
        List<Institution> list = courseService.queryAllIns();
        model.addAttribute("insList",list);//下拉列表展示用
        return "admin/insertStudent";
    }

    @RequestMapping("insertStudent")
    public String insertStudent(Student student,Model model) {//Student类型接收表单数据，属性名和表单项的name一致
        userService.insertStudent(student);
        model.addAttribute("paging",pageService.subList(1,userService.queryAllStudent()));
        return "admin/studentList";
    }

    @RequestMapping("removeStudent")
    public String removeStudent(@RequestParam("stuId") int id, Model model) {
        System.out.println(id + "hello...");
        userService.removeStudent(id);
        model.addAttribute("paging",pageService.subList(1,userService.queryAllStudent()));
        return "admin/studentList";
    }

    @RequestMapping("editStudent")
    public String editStudent(@RequestParam("stuId") int id,Model model) {
        Student student = userService.getStuInfoById(id);
        model.addAttribute("student",student);//查找数据库，再把数据返回页面数据回显
        model.addAttribute("insList",courseService.queryAllIns());//下拉列表
        System.out.println("hello...edit...");
        return "admin/editStudent";
    }
    @RequestMapping("changeStudent")
    public String changeStudent(Student student,Model model) {
        System.out.println(student.getSex() + student.getStuName() + student.getStuId());
        userService.updateStudent(student);
        model.addAttribute("paging",pageService.subList(1,userService.queryAllStudent()));
        return "admin/studentList";
    }

    @RequestMapping("preInsertTeacher")
    public String toInsertTeacher(Model model) {
        List<ProfessionalTitle> titleList = professionalTitleService.queryAllTitles();
        model.addAttribute("titleList",titleList);
        return "admin/insertTeacher";
    }

    @RequestMapping("insertTeacher")
    public String addTeacher(Teacher teacher,Model model) {//用一个pojo来接收表单数据
        userService.insertTeacher(teacher);
        model.addAttribute("paging",pageService.subList(1,userService.queryAllTeacher()));
        return "admin/teacherList";
    }

    @RequestMapping("removeTeacher")//假删除老师
    public String removeTeacher(@RequestParam("teaId")int teaId,Model model) {
        userService.removeTeacher(teaId);
        model.addAttribute("paging",pageService.subList(1,userService.queryAllTeacher()));
        return "admin/teacherList";
    }

    @RequestMapping("editTeacher")
    public String editTeacher(@RequestParam("teaId") int teaId, Model model) {
        Teacher teacher = userService.getTeaInfoById(teaId);
        teacher.setpName(professionalTitleService.queryPNameByPId(teacher.getpId()));
        model.addAttribute("titleList",professionalTitleService.queryAllTitles());//下拉列表使用
        model.addAttribute("teacher",teacher);//数据回显页面
        return "admin/editTeacher";
    }

    @RequestMapping("changeTeacher")
    public String changeTeacher(Teacher teacher,Model model) {
        userService.updateTeacher(teacher);
        model.addAttribute("paging",pageService.subList(1,userService.queryAllTeacher()));
        return "admin/teacherList";
    }
}
