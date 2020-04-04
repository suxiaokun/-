package com.bysj.xqyj.suxk.controller;

import com.bysj.xqyj.suxk.pojo.Student;
import com.bysj.xqyj.suxk.pojo.StudentReview;
import com.bysj.xqyj.suxk.pojo.Student_Course;
import com.bysj.xqyj.suxk.pojo.User;
import com.bysj.xqyj.suxk.service.ReviewService;
import com.bysj.xqyj.suxk.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

@Controller
public class RewiewController {
    @Autowired
    private ReviewService reviewService;
    @Autowired
    private UserService userService;
    @RequestMapping("addReview")
    public String addReview(StudentReview studentReview, HttpSession session,Model model){
        User user=(User)session.getAttribute("user");
        String sid=studentReview.getSid();
        String student_name=userService.selStudentBySid(sid).getStudent_name();
        studentReview.setStudent_name(student_name);
        studentReview.setReview_tid(user.getUid());
        studentReview.setReview_teacher(user.getUsername());
        studentReview.setReview_time(new Date());
        Integer num=reviewService.addReview(studentReview);
        System.out.println(studentReview);
        model.addAttribute("msg","审核成功");
        return "successful";
    }

    @RequestMapping("showMyReview")
    public String showStudentReview(HttpSession session,Model model){
        User user=(User)session.getAttribute("user");
        if (user.getRole()==0){
            //学生
            String sid=user.getUid();
            Student student=userService.selStudentBySid(sid);
            List<StudentReview> studentReviewList=reviewService.showStudentReview(sid);
            model.addAttribute("reviews",studentReviewList);
            model.addAttribute("student",student);
            return "review-list";
        }
        if(user.getRole()==1){
            //教师
            List<StudentReview> teacherReviewList=reviewService.showTeacherReview(user.getUid());
            model.addAttribute("reviews",teacherReviewList);
            return "review-list";
        }
        if (user.getRole()==2){
            //管理员
            List<StudentReview> reviewList=reviewService.showAllReview();
            model.addAttribute("reviews",reviewList);
            return "review-list";
        }
        return null;

    }

    //展示教师可以审核的学生列表
    @RequestMapping("showTeacherReview")
    public String showTeacherReviewList(HttpSession session,Model model){
        User user=(User)session.getAttribute("user");
        String tid=user.getUid();
        List<Student_Course> courseList=reviewService.showTeacherReviewList(tid);
        model.addAttribute("courses",courseList);
        return "teacher-review-list";
    }
    //跳转审核列表
    @RequestMapping("toaddreview")
    public String toaddreview(String sid,String course_name,Model model){
        System.out.println(sid+course_name);
        model.addAttribute("sid",sid);
        model.addAttribute("course_name",course_name);
        return "add-review";

    }
    //教师欢迎页面
    @RequestMapping("teacherWelcome")
    public String teacherWelcome(HttpSession session,Model model){
        User user=(User)session.getAttribute("user");
        String tid=user.getUid();
        List<Student_Course> courseList=reviewService.showTeacherReviewList(tid);
        model.addAttribute("courses",courseList);
        return "teacherindex";
    }
}
