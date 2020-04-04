package com.bysj.xqyj.suxk.controller;

import com.bysj.xqyj.suxk.pojo.Student;
import com.bysj.xqyj.suxk.pojo.Teacher;
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
public class TeacherController {
    @Autowired
    private UserService userService;
    //修改教师信息
    @RequestMapping("updateTeacher")
    public String updateTeacher(HttpSession session, Model model){
        User user=(User) session.getAttribute("user");
        String tid=user.getUid();
        Teacher teacher=userService.selTeacherByTid(tid);
        model.addAttribute("teacher",teacher);
        return "update-teacher-info";

    }
    @RequestMapping("updateTeachering")
    public String updateTeachering(Teacher teacher,Model model){
        int num=userService.updateTeacherInfo(teacher.getTeacher_name(),teacher.getTeacher_sex(),teacher.getTeacher_college(),teacher.getTeacher_time(),teacher.getTeacher_address(),teacher.getTeacher_email(),teacher.getTeacher_phone(),teacher.getTid());
        model.addAttribute("msg","保存成功");
        return "successful";
    }
    @RequestMapping("showAllTeacher")
    public String showAllTeacher(Model model){
        List<Teacher> teacherLists=userService.selAllTeacher();
        System.out.println(teacherLists);
        model.addAttribute("teachers",teacherLists);
        return "teacher-list";
    }
    @RequestMapping("delTeacher")
    @ResponseBody
    public Map delTeacher(String tid, Model model){
        Map map=new HashMap();
        System.out.println(tid);
        Integer num=userService.delTeacher(0,tid);
        model.addAttribute("msg","删除成功");
        map.put("msg","删除成功");
        return map;
//        return "successful";
    }
}
