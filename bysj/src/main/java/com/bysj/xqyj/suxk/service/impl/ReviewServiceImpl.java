package com.bysj.xqyj.suxk.service.impl;

import com.bysj.xqyj.suxk.dao.ReviewDao;
import com.bysj.xqyj.suxk.dao.UserDao;
import com.bysj.xqyj.suxk.pojo.Student;
import com.bysj.xqyj.suxk.pojo.StudentReview;
import com.bysj.xqyj.suxk.pojo.Student_Course;
import com.bysj.xqyj.suxk.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("ReviewService")
public class ReviewServiceImpl implements ReviewService {
    @Autowired
    private ReviewDao reviewDao;
    @Autowired
    private UserDao userDao;

    public Integer addReview(StudentReview studentReview) {
        String sid=studentReview.getSid();
        Student student=userDao.selStudentBySid(sid);
        double score = student.getScore();
        double get_score=studentReview.getGet_score();
        score+=get_score;
        if (score>=100.0){
            score=100.0;
        }
        reviewDao.changeStudentScore(score,sid);
        return reviewDao.addReview(studentReview);
    }

    @Override
    public List<StudentReview> showStudentReview(String sid) {
        return reviewDao.showStudentReview(sid);
    }

    @Override
    public List<StudentReview> showTeacherReview(String tid) {
        return reviewDao.showTeacherReview(tid);
    }

    @Override
    public StudentReview selReviewByRid(String rid) {
        return reviewDao.selReviewByRid(rid);
    }

    @Override
    public List<StudentReview> showAllReview() {
        return reviewDao.showAllReview();
    }

    @Override
    public List<Student_Course> showTeacherReviewList(String tid) {
        return reviewDao.showTeacherReviewList(tid);
    }


}
