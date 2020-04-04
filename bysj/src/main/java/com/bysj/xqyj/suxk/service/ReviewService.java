package com.bysj.xqyj.suxk.service;

import com.bysj.xqyj.suxk.pojo.StudentReview;
import com.bysj.xqyj.suxk.pojo.Student_Course;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface ReviewService {
    Integer addReview(StudentReview studentReview);

    List<StudentReview> showStudentReview(String sid);
    List<StudentReview> showTeacherReview(String tid);
    StudentReview selReviewByRid(String rid);
    List<StudentReview> showAllReview();

    List<Student_Course> showTeacherReviewList(String tid);
}
