package com.bysj.xqyj.suxk.dao;

import com.bysj.xqyj.suxk.pojo.StudentReview;
import com.bysj.xqyj.suxk.pojo.Student_Course;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Service;

import java.util.List;

@Mapper
public interface ReviewDao {
    Integer addReview(StudentReview studentReview);

    //给学生增减预警学分
    @Update("UPDATE xqyjxm.student \tSET\tscore = #{score}\tWHERE\tsid = #{sid}")
    Integer changeStudentScore(@Param("score")double score, @Param("sid")String sid);

    //查询学生的活动过程记录
    @Select("SELECT \t*\tFROM \txqyjxm.student_review \tWHERE sid=#{sid}")
    List<StudentReview> showStudentReview(String sid);

    //教师审核记录过程
    @Select("SELECT \t* \tFROM \txqyjxm.student_review \tWHERE review_tid=#{tid}")
    List<StudentReview> showTeacherReview(String tid);
    //根据rid查询
    @Select("SELECT \t* FROM \txqyjxm.student_review \tWHERE rid=#{rid}")
    StudentReview selReviewByRid(String rid);
    //查询所有活动审核过程
    @Select("SELECT \t* FROM \txqyjxm.student_review")
    List<StudentReview> showAllReview();
    @Select("SELECT student.sid,\n" +
            "student.student_name,\n" +
            "student.student_major,\n" +
            "course.course_grade,\n" +
            "course.course_name \n" +
            "FROM student,course \n" +
            "WHERE course.tid=#{tid} AND course.course_major=student.student_major AND course.course_grade=student.start_year ")
    List<Student_Course> showTeacherReviewList(String tid);
}
