package com.bysj.xqyj.suxk.dao;

import com.bysj.xqyj.suxk.pojo.Course;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Mapper
public interface CourseDao {
    Integer addCourse(Course course);

    //根据入学年份与专业确定学生的课程
    @Select("SELECT \t* FROM \txqyjxm.course \tWHERE course.course_grade=#{course_grade} AND course.course_major=#{course_major}\n")
    List<Course> selCourseByMajor(@Param("course_grade")String course_grade, @Param("course_major")String course_major);
    //教师根据tid查询自己的课程
    @Select("SELECT * \tFROM \txqyjxm.course WHERE tid=#{tid}")
    List<Course> selCourseByTid(String tid);
    //结课设置
    @Update("UPDATE xqyjxm.course \tSET\tstop_tag = #{stop_tag}  \tWHERE\tcid = #{cid} ")
    Integer changeCourseToStop(@Param("stop_tag")Integer stop_tag,@Param("cid")String cid);

    //根据课程号查询课程
    @Select("SELECT  *\tFROM \txqyjxm.course  WHERE cid=#{cid}")
    Course selCourseByCid(String cid);
    //查询所有课程
    @Select("SELECT * \tFROM \txqyjxm.course")
    List<Course> selAllCourse();



    //修改课程信息
    @Update("UPDATE xqyjxm.course \n" +
            "\tSET\n" +
            "\tcourse_name = #{course_name} , \n" +
            "\tstart_time = #{start_time}, \n" +
            "\tcourse_major = #{course_major} , \n" +
            "\tcourse_grade = #{course_grade}\n" +
            "\tWHERE\n" +
            "\tcid = #{cid}")
    Integer updateCourseInfo(@Param("course_name")String course_name,@Param("start_time")Date start_time,
                             @Param("course_major")String course_major,@Param("course_grade")String course_grade,@Param("cid")String cid);
}
