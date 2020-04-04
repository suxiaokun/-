package com.bysj.xqyj.suxk.controller;

import com.bysj.xqyj.suxk.pojo.Student;
import com.bysj.xqyj.suxk.pojo.Teacher;
import com.bysj.xqyj.suxk.pojo.User;
import com.bysj.xqyj.suxk.service.UserService;
import com.google.code.kaptcha.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class UserController {
    @Autowired
    private JavaMailSender mailSender;
    @Autowired
    private UserService userService;

    @RequestMapping("/")
    public String login(){
        return "login";
    }
    @RequestMapping("/forget")
    public String forgetpasswrd(){
        return "forgetPassword";
    }
    //登陆
    @RequestMapping("/logining")
    public String loging(User user, HttpServletRequest request, Model model,String code,HttpSession session ){
        String sessionCode = (String)request.getSession().getAttribute(Constants.KAPTCHA_SESSION_KEY);
        if (!code.equals(sessionCode)){
            model.addAttribute("msg","验证码错误");
            return "login";
        }
        String uid=user.getUid();
        User user1=userService.selUserById(uid);
        if (user1==null){
            model.addAttribute("msg","您的账号或密码输入有误，请重新输入");
            return "login";
        }else if (!user.getPassword().equals(user1.getPassword())){
            model.addAttribute("msg","您的账号或密码输入有误，请重新输入");
            return "login";
        }else if (!user.getRole().equals(user1.getRole())){
            model.addAttribute("msg","您的身份有误，请重新选择");
            return "login";
        }
        if (user.getRole()==0){
            Student student=userService.selStudentBySid(user.getUid());
            session.setAttribute("student",student);
        }else if (user.getRole()==1){
            Teacher teacher=userService.selTeacherByTid(user.getUid());
            session.setAttribute("teacher",teacher);
        }
        session.setAttribute("user", user1);
        System.out.println(session.getAttribute("user"));
        return "index";

    }

    //忘记密码，重置密码并发送邮箱
    @RequestMapping("/forgetPassword")
    public String forgetPassword(User user,String code,Model model, HttpServletRequest request){
        String sessionCode = (String)request.getSession().getAttribute(Constants.KAPTCHA_SESSION_KEY);
        if (!code.equals(sessionCode)){
            model.addAttribute("msg","验证码错误");
            return "forgetPassword";
        }
        System.out.println(user.getEmail());
        User user1=userService.selUserById(user.getUid());
        System.out.println(user1);
        if (user1==null) {
            model.addAttribute("msg", "您的账号输入有误，请重新输入");
            return "forgetPassword";
        }else if(!user.getEmail().equals(user1.getEmail())){
            model.addAttribute("msg", "您的邮箱输入有误，请重新输入");
            return "forgetPassword";
        }
        String new_password="666666";
        userService.resetPassword(user.getUid(),new_password);
        //发送邮件
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("1224692968@qq.com");
        message.setTo(user.getEmail());
        message.setSubject("主题：密码修改通知");
        message.setText("尊敬的用户：您的密码已重置，新密码为："+new_password+"，请妥善保管！");
        mailSender.send(message);
        model.addAttribute("msg","您的密码已重置重置，新密码已发往邮箱，请前往邮箱查看");
        return "/";
    }
   //跳转网页
    @RequestMapping("tosrc")
    public String tosrc(String src){
        return src;

    }
    //注册用户
    @RequestMapping("registing")
    public String registing(User user,Model model){
        System.out.println(user);
        //设置状态为有效1,初始密码账号后六位
        user.setStatus(1);
        String name=user.getUid();
        String password=name.substring(name.length()-6,name.length());
        user.setPassword(password);
        if (user.getUid()==null&&user.getUid().equals("")){
            model.addAttribute("msg","学号/教工号不能为空");
            return "fail";
        }
        if(userService.selUserById(user.getUid())!=null){
            model.addAttribute("msg","该用户已被注册");
            return "fail";
        }
        if (userService.selUserByEmail(user.getEmail())!=null){
            model.addAttribute("msg","该邮箱已被注册");
            return "fail";
        }
        int num=userService.addUser(user);
        //判断是学生还是教师，往各自表里插入
        int role=user.getRole();
        if (role==0){
            //学生
            Student student=new Student();
            student.setSid(user.getUid());
            student.setRemove_tag(1);//有效标识
            student.setScore(75.0);//初始预警学分
            student.setStudent_name(user.getUsername());
            student.setStudent_email(user.getEmail());
            userService.addStudent(student);
        } else if (role==1){
            Teacher teacher=new Teacher();
            teacher.setTid(user.getUid());
            teacher.setTeacher_name(user.getUsername());
            teacher.setTeacher_email(user.getEmail());
            teacher.setRemove_tag(1);//有效标识
            userService.addTeacher(teacher);
        }
        model.addAttribute("msg","注册成功");
        return "successful";
    }
    //显示所有用户
    @RequestMapping("userinfo")
    public  String userInfo(Model model){
        List<User> userList=userService.findAllUsers();
        model.addAttribute("users",userList);
        return "UserInfoList2";
    }
    //退出登录
    @RequestMapping("logout")
    public String logout(HttpSession session,Model model){
        session.invalidate();
        model.addAttribute("msg","退出成功");
        return "/login";
    }
    //修改密码
    @RequestMapping("updatePassword")
    public String  updatePassword(String password,String new_password ,Model model,HttpSession session){
        User user=(User) session.getAttribute("user");
        System.out.println(password);
        System.out.println(user.getPassword());
        if (!user.getPassword().equals(password)){
            model.addAttribute("msg","原密码输入错误，请重新输入");
            return "/fail";
        }
        userService.resetPassword(user.getUid(),new_password);
        model.addAttribute("msg","修改成功");
        return "successful";
    }
}
