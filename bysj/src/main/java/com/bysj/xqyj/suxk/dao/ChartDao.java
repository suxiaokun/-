package com.bysj.xqyj.suxk.dao;

import com.bysj.xqyj.suxk.pojo.Chart;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Service;

import javax.validation.constraints.Max;
import java.util.List;

@Mapper
public interface ChartDao {
    @Select("select title as name,count(sid) as num  from warning_level as w,student as s \n" +
            "where s.score between w.warning_down and w.warning_up\n" +
            "GROUP BY title\n")
    List<Chart> levelSum();
    //查询学生各课程获得学分情况
    @Select("SELECT review_title AS name,SUM(get_score)AS num FROM student_review WHERE sid=#{sid} GROUP BY review_title")
    List<Chart> reviewSum(String sid);
    //查询各个老师开课情况
    @Select("SELECT course_teacher AS NAME,COUNT(course_name) AS num FROM course GROUP BY course_teacher")
    List<Chart> teacher_course();
    //各个老师下发通知的数量情况
    @Select("SELECT notice_user AS NAME,COUNT(*) AS num FROM warning_notice GROUP BY notice_user")
    List<Chart> teacher_notice();


    //查询教师各个班级的开课数量
    @Select("SELECT course_major AS NAME,COUNT(course_name)AS num FROM course WHERE tid=#{tid} GROUP BY course_major")
    List<Chart> teacher_major_course(String tid);

}
