package com.zxc.controller.common;

import com.zxc.service.UserService;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
//@SessionAttributes({"username","teaid","stuid","adminId"})
public class LoginController {
    @Resource
    private UserService userService;

    @RequestMapping("login")
    public String login(){
        return "login";
    }

    @RequestMapping(value = "check",method = RequestMethod.POST)
    public String checkAccount(@RequestParam("userid") int id, @RequestParam("userpass") String pass, Model model, HttpSession session) {
        if (userService.checkAccount(id, pass) == 2) {
            //model.addAttribute("username",userService.getTeaNameById(id));
            //model.addAttribute("teaid",id);
            session.setAttribute("username",userService.getTeaNameById(id));
            session.setAttribute("teaid",id);
            session.setAttribute("status","教师");
            return "redirect:teacher/teacherIndex";
        }
        else if(userService.checkAccount(id, pass) == 1){
            //model.addAttribute("username",userService.getStuNameById(id));
            //model.addAttribute("stuid",id);
            session.setAttribute("username",userService.getStuNameById(id));
            session.setAttribute("stuid",id);
            session.setAttribute("status","学生");
            return "redirect:student/studentIndex";
        }
        else if(userService.checkAccount(id, pass) == 3){
            //model.addAttribute("username",userService.getAdminNameById(id));
            //model.addAttribute("adminId",id);
            session.setAttribute("username",userService.getAdminNameById(id));
            session.setAttribute("adminId",id);
            session.setAttribute("status","管理员");
            return "redirect:admin/adminIndex";
        }
        else{
            model.addAttribute("msg","密码错误");
            //这里不加redirect，否则前端el取不到值
            return "login";
        }
    }

    @RequestMapping("exit")
    public String exit(HttpServletRequest request){
        request.getSession().invalidate();
        return "login";
    }
}
