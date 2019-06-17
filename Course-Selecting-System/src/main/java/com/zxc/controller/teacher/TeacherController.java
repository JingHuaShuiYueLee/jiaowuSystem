package com.zxc.controller.teacher;

import com.zxc.model.Teacher;
import com.zxc.service.CourseService;
import com.zxc.service.PageService;
import com.zxc.service.ProfessionalTitleService;
import com.zxc.service.UserService;
import com.zxc.service.impl.CourseServiceImpl;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

@Controller
@RequestMapping("teacher")
public class TeacherController {
    @Resource
    private UserService userService;
    @Resource
    private CourseService courseService;
    @Resource
    private PageService pageService;

    @Autowired
    private ProfessionalTitleService professionalTitleService;

    @RequestMapping("/teacherIndex")
    public String studentIndex(){
        return "teacher/teacherIndex";
    }

    @RequestMapping("/teacherInfo")
    public String studentInfo(@RequestParam("teaid") int id, Model model){
        Teacher teacher = userService.getTeaInfoById(id);
        teacher.setpName(professionalTitleService.queryPNameByPId(teacher.getpId()));
        model.addAttribute("teacher",teacher);
        return "teacher/teacherInfo";
    }

    @RequestMapping("/editTeaPass")
    public String editTeaPass(){
        return "teacher/editTeaPass";
    }

    @RequestMapping("/changeTeaPass")
    public String changPass(@RequestParam("prepass") String prepass, @RequestParam("nowpass") String nowpass, Model model, HttpServletRequest request){
        int id=(int)request.getSession().getAttribute("teaid");
        if(userService.checkAccount(id,prepass)==0){
            model.addAttribute("msg","原始密码输入错误!");
            return "teacher/editTeaPass";
        }
        else{
            Teacher teacher=new Teacher();
            teacher.setTeaId(id);
            teacher.setTeaPass(nowpass);
            userService.changeTeaPass(teacher);
            model.addAttribute("teacher",userService.getTeaInfoById(id));
            return "teacher/teacherInfo";
        }
    }

    @RequestMapping("/courseList")//该老师教授的所有课程
    public String courseList(@Param("page") int page, Model model,HttpServletRequest request){
        model.addAttribute("paging",pageService.subList(page,courseService.queryAllById((int)request.getSession().getAttribute("teaid"))));
        return "teacher/courseList";
    }

    @RequestMapping("/charuCourse")//地址栏显示charuCourse
    public String insertCourse(Model model){
        model.addAttribute("insList",courseService.queryAllIns());//下拉列表内容从数据库中得到
        return "teacher/insertCourse";//返回页面内容insertCourse
    }

    @RequestMapping("/editCourse")//编辑课程
    public String editCourse(@Param("courseid") int courseid, Model model){//编辑页面要实现数据回显
        model.addAttribute("courseInfo",courseService.queryInfoById(courseid));
        model.addAttribute("checkIns",courseService.selectCourseLimit(courseid));//返回该课程限制的学院id列表
        model.addAttribute("insList",courseService.queryAllIns());//返回所有的ins
        return "teacher/editCourse";
    }

    @RequestMapping("/insertCourseSuccess")//post方式提交用@Param
    public String insertCourseSuccess(@Param("content") String content,@Param("page") int page, Model model, HttpServletRequest request)throws UnsupportedEncodingException{
        String[] det= URLDecoder.decode(URLDecoder.decode(content,"utf-8"),"utf-8").split("\\|");
        //获取插入后的课程编号
        int courseId=courseService.insertCourse(det[0],det[1],det[3],(int)request.getSession().getAttribute("teaid"));//插入course表
        courseService.insertInsLimit(det[2],courseId);//插入course_limit表，这门课有哪些学院可以选
        model.addAttribute("paging",pageService.subList(page,courseService.queryAllById((int)request.getSession().getAttribute("teaid"))));
        return "teacher/courseList";//返回页面内容，地址栏仍然是insertCourseSuccess
    }

    @RequestMapping("/updateCourseSuccess")
    public String updateCourseSuccess(@Param("content") String content,@Param("page") int page, @Param("courseid") int courseid, Model model, HttpServletRequest request)throws UnsupportedEncodingException{
        String[] det= URLDecoder.decode(URLDecoder.decode(content,"utf-8"),"utf-8").split("\\|");
        System.out.println(det[0]+" "+det[1]+" "+det[2]);
        int courseId=courseService.updateCourse(det[0],det[1],det[3],(int)request.getSession().getAttribute("teaid"),courseid);//更新course表
        System.out.println(det[2]);
        courseService.updateInsLimit(det[2],courseId);//更新course_limit表，实际上是先删除旧的，再插入新的，这门课有哪些学院可以选
        model.addAttribute("paging",pageService.subList(page,courseService.queryAllById((int)request.getSession().getAttribute("teaid"))));
        return "teacher/courseList";
    }

    @RequestMapping("/deleteCourse")
    public String deleteCourse(@Param("courseid") int courseid, Model model,HttpServletRequest request){
        courseService.deleteCourse(courseid);//数据库中删除信息后
        model.addAttribute("paging",pageService.subList(1,courseService.queryAllById((int)request.getSession().getAttribute("teaid"))));
        //再从数据库中查信息
        return "teacher/courseList";
    }

    @RequestMapping("/detailCourse")//进入管理学生成绩的界面
    public String detailCourse(@Param("courseid") int courseid,@Param("page") int page,Model model,HttpServletRequest request){
        model.addAttribute("paging",pageService.subList(page,courseService.queryStuByCourseId(courseid)));
        model.addAttribute("className",courseService.queryCourse(courseid).getClassName());
        return "teacher/courseDetail";
    }

    @RequestMapping("/updateScore")//老师输入分数，点击评分按钮之后
    public String updateScore(@Param("courseid") int courseid,@Param("stuId") int stuId,@Param("score") int score,@Param("page") Integer page,Model model){
        courseService.updateScore(courseid,stuId,score);
        model.addAttribute("paging",pageService.subList(page,courseService.queryStuByCourseId(courseid)));
        return "teacher/courseDetail";
    }

    @RequestMapping("/searchStu")//搜索框输入学号找到学生
    public String searchStu(@Param("stuid") int stuid, @Param("courseid") int courseid, Model model){
        int page=1;
        model.addAttribute("paging",pageService.subList(page,courseService.queryStuByStuId(courseid,stuid)));
        return "teacher/courseDetail";
    }

    @RequestMapping("/deleteStuCourse")//删除学生及对应课程
    public String deleteStuCourse(@Param("stuid") int stuid,@Param("courseid") int courseid,Model model){
        courseService.deleteCourseChoose(stuid,courseid);
        model.addAttribute("paging",pageService.subList(1,courseService.queryStuByCourseId(courseid)));
        return "teacher/courseDetail";
    }
}
