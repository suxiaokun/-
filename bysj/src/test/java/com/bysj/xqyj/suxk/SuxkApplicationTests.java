package com.bysj.xqyj.suxk;

import com.bysj.xqyj.suxk.dao.ChartDao;
import com.bysj.xqyj.suxk.dao.ReviewDao;
import com.bysj.xqyj.suxk.dao.UserDao;
import com.bysj.xqyj.suxk.pojo.*;
import com.bysj.xqyj.suxk.service.CourseService;
import org.apache.ibatis.annotations.Mapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@SpringBootTest
class SuxkApplicationTests {
	@Autowired
	private ChartDao chartDao;
    @Autowired
	private UserDao userDao;
    @Autowired
	ReviewDao reviewDao;
    @Autowired
    CourseService courseService;
	@Test
	void contextLoads() {
		System.out.println("hello");
	}

	@Test
	void test(){
	    //获取入学年份
		Student s=userDao.selStudentBySid("787");
		String x=s.getStart_year().toString();
        System.out.println(x);
        System.out.println(x.substring(x.length()-4,x.length()));
	}
	@Test
	void teset2(){
		StudentReview studentReview=new StudentReview();
		studentReview.setReview_teacher("陈老师");
		studentReview.setReview_tid("009");
		studentReview.setGet_score(1.0);
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
		studentReview.setReview_time(new Date());
		studentReview.setReview_content("内容");
		studentReview.setReview_title("主题");
		studentReview.setSid("787");
        System.out.println(studentReview);
		reviewDao.addReview(studentReview);

	}
	@Test
	void teset3(){
        List<Course> courseList=courseService.selCourseByTid("*");
        System.out.println(courseList);
	}
	@Test
	void teste4(){
		List<Chart> charts=chartDao.levelSum();
		System.out.println(charts);
	}
	@Test
	void teste5(){
		List<Chart> charts=chartDao.reviewSum("787");
		System.out.println(charts);
	}
    @Test
    void teste6(){
        List<Student_Course> charts=reviewDao.showTeacherReviewList("009");
        System.out.println(charts);
    }

    @Test
    void test7(){
	    List<Student> studentList=userDao.selAllStudent();
        for (int i = 0; i < studentList.size(); i++) {
            System.out.println(studentList.get(i).getScore());
        }
    }
}
