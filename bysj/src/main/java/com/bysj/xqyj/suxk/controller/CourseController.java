package com.bysj.xqyj.suxk.controller;

import com.bysj.xqyj.suxk.pojo.Course;
import com.bysj.xqyj.suxk.pojo.Student;
import com.bysj.xqyj.suxk.pojo.Teacher;
import com.bysj.xqyj.suxk.pojo.User;
import com.bysj.xqyj.suxk.service.CourseService;
import com.bysj.xqyj.suxk.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class CourseController {
    @Autowired
    private UserService userService;
    @Autowired
    private CourseService courseService;
    //教师录入课程
    @RequestMapping("addCourse")
    public String addCourseing(Course course, Model model, HttpSession session){
        User user=(User) session.getAttribute("user");
        //补全信息
        course.setTid(user.getUid());//教师ID
        course.setCourse_teacher(user.getUsername());//教师姓名
        course.setStop_tag(1);
        System.out.println(course);
        int num=courseService.addCourse(course);
        model.addAttribute("msg","录入成功");
        return  "successful";
    }
    @RequestMapping("selMyCourse")
    public String selMyCourse(Model model, HttpSession session){
        User user=(User) session.getAttribute("user");
        if (user.getRole()==0){
            //学生
            Student student=userService.selStudentBySid(user.getUid());
            String course_major=student.getStudent_major();
            String  student_year=student.getStart_year();
           // String course_grade=student_year.substring(student_year.length()-4,student_year.length());//获取后四位，即年级如2016
            List<Course> courseList=courseService.selCourseByMajor(course_major,student_year);
            model.addAttribute("courses",courseList);
        }else if (user.getRole()==1){
            //教师,tid
            List<Course> courseList=courseService.selCourseByTid(user.getUid());
            model.addAttribute("courses",courseList);
        }else if(user.getRole()==2){
            List<Course> courseList=courseService.selAllCourse();
            model.addAttribute("courses",courseList);
        }
         return "course-list";
    }
    @RequestMapping("changeCourseToStop")
    @ResponseBody
    public Map changeCourseToStop(Integer stop_tag,String cid,Model model){
        Map map=new HashMap();
        Integer num=courseService.changeCourseToStop(stop_tag,cid);
        map.put("msg","设置成功");
        return map;
    }
    @RequestMapping("/checktest")
    @ResponseBody
    public String  checktest(@RequestParam(value="arrUserid[]") String[] arrUserid){
        for (String id:arrUserid
             ) {
            System.out.println(id);

        }
        return "success";
    }
    //修改课程信息
    @RequestMapping("updateCourseInfo")
    public String updateCourseInfo(String cid,Model model){
        Course course=courseService.selCourseByCid(cid);
        model.addAttribute("course",course);
        return "update-course";
    }
    @RequestMapping("updateCourseing")
    public String updateCourseing(Course course,Model model){
        int num=courseService.updateCourseInfo(course.getCourse_name(),course.getStart_time(),course.getCourse_major(),course.getCourse_grade(),course.getCid());
        model.addAttribute("msg","修改成功");
        return "successful";
    }
}
