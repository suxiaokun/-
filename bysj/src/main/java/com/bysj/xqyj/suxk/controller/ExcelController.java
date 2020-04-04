package com.bysj.xqyj.suxk.controller;

import com.bysj.xqyj.suxk.dao.CourseDao;
import com.bysj.xqyj.suxk.pojo.*;
import com.bysj.xqyj.suxk.service.CourseService;
import com.bysj.xqyj.suxk.service.ReviewService;
import com.bysj.xqyj.suxk.service.UserService;
import com.bysj.xqyj.suxk.utils.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class ExcelController {
    @Autowired
    private ReviewService reviewService;
    @Autowired
    private UserService userService;
    @Autowired
    private CourseService courseService;
    //下载模板
    @RequestMapping("downdemo")
    public void downDemo(HttpServletResponse response){
        User user=new User();
        List demoList=new ArrayList();
        demoList.add(user);
        FileUtil.exportExcel(demoList,"学号/教工号，身份：0：学生，1：教师","",User.class,"用户注册模板.xls",response);
    }
    //批量导入课程信息模板
    @RequestMapping("downCourseDemo")
    public void downCourseDemo(HttpServletResponse response){
        CourseDemo courseDemo=new CourseDemo();
        courseDemo.setCourse_major("如：软件工程");
        courseDemo.setCourse_grade("如：2016");
        courseDemo.setCourse_name("如：数据结构");
        courseDemo.setStart_time(new Date());
        List demoList=new ArrayList();
        demoList.add(courseDemo);
        FileUtil.exportExcel(demoList,"模板","",CourseDemo.class,"用户注册模板.xls",response);

    }
    //批量注册
    @RequestMapping("importUser")
    public String importuser(@RequestParam("file") MultipartFile file,Model model){
        List<User> users =FileUtil.importExcel(file, 1,  1, User.class);
        for (int i = 0; i <users.size() ; i++) {
            User user=users.get(i);
            String name=user.getUid();
            String password=name.substring(name.length()-6,name.length());
            user.setPassword(password);
            user.setStatus(1);
            userService.addUser(user);
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
        }
        System.out.println(users);
        model.addAttribute("msg","批量注册成功");
        return "successful";
    }
    //导出所有学生信息
    @RequestMapping("downStudentInfo")
    public void downStudentInfo(HttpServletResponse response,String[] arrUserid){
        List<Student> studentList=new ArrayList<Student>();
        for (String id:arrUserid) {
            Student student=userService.selStudentBySid(id);
            studentList.add(student);
        }
        System.out.println(studentList);
        FileUtil.exportExcel(studentList,"学生信息","",Student.class,"学生信息表.xls",response);
    }
    //导出所有教师信息
    @RequestMapping("downTeacherInfo")
    public void downTeacherInfo(HttpServletResponse response,String[] arrUserid){
        List<Teacher> teacherList=new ArrayList<Teacher>();
        for (String id:arrUserid) {
            Teacher teacher=userService.selTeacherByTid(id);
            teacherList.add(teacher);
        }
        System.out.println(teacherList);
        FileUtil.exportExcel(teacherList,"教师信息","",Teacher.class,"教师信息表.xls",response);
    }

    //导出我的课程信息
    @RequestMapping("downMyCourse")
    public void downMyCourse(HttpServletResponse response,String[] arrUserid){
        List<Course> courseList=new ArrayList<Course>();
        for (String id:arrUserid) {
           Course course=courseService.selCourseByCid(id);
           course.setCourse_grade(course.getCourse_grade()+"级");
           courseList.add(course);
        }
        System.out.println(courseList);
        FileUtil.exportExcel(courseList,"课程信息","",Course.class,"课程信息表.xls",response);

    }
    //批量录入课程
    @RequestMapping("importCourse")
    public String importCourse(@RequestParam("file") MultipartFile file,HttpSession session,Model model){
        User user=(User) session.getAttribute("user");
        List<Course> courseList =FileUtil.importExcel(file, 1,  1, Course.class);
        for (int i = 0; i <courseList.size() ; i++) {
            //补全信息
            Course course=courseList.get(i);
            course.setTid(user.getUid());//教师ID
            course.setCourse_teacher(user.getUsername());//教师姓名
            course.setStop_tag(1);
            courseService.addCourse(course);
        }
        model.addAttribute("msg","录入成功");
        return "successful";
    }

    //批量导出活动信息
    @RequestMapping("/downReviewInfo")
    public void downReviewInfo(HttpServletResponse response,String[] arrUserid){
        List<StudentReview> studentReviewList=new ArrayList<>();
        for (String id:arrUserid
             ) {
            StudentReview studentReview=reviewService.selReviewByRid(id);
            studentReviewList.add(studentReview);
        }
        System.out.println(studentReviewList);
        FileUtil.exportExcel(studentReviewList,"活动信息","",StudentReview.class,"活动信息表.xls",response);

    }
}
