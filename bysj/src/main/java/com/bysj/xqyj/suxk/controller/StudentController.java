package com.bysj.xqyj.suxk.controller;

import com.bysj.xqyj.suxk.pojo.Student;
import com.bysj.xqyj.suxk.pojo.User;
import com.bysj.xqyj.suxk.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class StudentController {
    @Autowired
    private UserService userService;
    //修改学生信息
    @RequestMapping("updateStudent")
    public String updateStudent(HttpSession session, Model model){
        User user=(User) session.getAttribute("user");
        String sid=user.getUid();
        Student student=userService.selStudentBySid(sid);
        System.out.println(student);
        model.addAttribute("student",student);
        return "update-student-info";
    }
    @RequestMapping("updateStudenting")
    public String updateStudenting(Student student,Model model){
        System.out.println(student);
        int num=userService.updateStudentInfo(student.getSex(),student.getStudent_college(),student.getStudent_major(),student.getStart_year(),student.getStudent_address(),
                student.getStudent_email(),student.getStudent_phone(),student.getSid());
        model.addAttribute("msg","保存成功");
        return "successful";
    }
    @RequestMapping("showAllStudent")
    public String showAllStudent(Model model){
        List<Student> studentLists=userService.selAllStudent();
        model.addAttribute("students",studentLists);
        return "student-list";
    }
    @RequestMapping("delStudent")
    @ResponseBody
    public Map delStudent(String sid, Model model){
        Map map=new HashMap();
        System.out.println(sid);
        Integer num=userService.delStudent(0,sid);
        model.addAttribute("msg","删除成功");
        map.put("msg","shanchuchenggong");
        return map;
//        return "successful";
    }
}
