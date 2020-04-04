package com.bysj.xqyj.suxk.service.impl;

import com.bysj.xqyj.suxk.dao.CourseDao;
import com.bysj.xqyj.suxk.pojo.Course;
import com.bysj.xqyj.suxk.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service("CourseService")
public class CourseServiceImpl implements CourseService{
    @Autowired
    private CourseDao courseDao;
    @Override
    public Integer addCourse(Course course) {
        return courseDao.addCourse(course);
    }

    @Override
    public List<Course> selCourseByMajor(String course_major, String course_grade) {
        return courseDao.selCourseByMajor(course_grade,course_major);
    }

    @Override
    public List<Course> selCourseByTid(String tid) {
        return courseDao.selCourseByTid(tid);
    }

    @Override
    public Integer changeCourseToStop(Integer stop_tag,String cid) {
        return courseDao.changeCourseToStop(stop_tag,cid);
    }

    @Override
    public Course selCourseByCid(String cid) {
        return courseDao.selCourseByCid(cid);
    }

    @Override
    public List<Course> selAllCourse() {
        return courseDao.selAllCourse();
    }

    @Override
    public Integer updateCourseInfo(String course_name, Date start_time, String course_major, String course_grade, String cid) {
        return courseDao.updateCourseInfo(course_name,start_time,course_major,course_grade,cid);
    }
}
