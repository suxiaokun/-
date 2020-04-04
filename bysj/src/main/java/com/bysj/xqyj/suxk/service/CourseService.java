package com.bysj.xqyj.suxk.service;

import com.bysj.xqyj.suxk.pojo.Course;

import java.util.Date;
import java.util.List;

public interface CourseService {
    Integer addCourse(Course course);

    List<Course> selCourseByMajor(String course_grade,String course_major);

    List<Course> selCourseByTid(String tid);

    Integer changeCourseToStop(Integer stop_tag,String cid);

    Course selCourseByCid(String cid);
    List<Course> selAllCourse();
    Integer updateCourseInfo(String course_name, Date start_time, String course_major, String course_grade, String cid);
}
