package com.bysj.xqyj.suxk.service.impl;

import com.bysj.xqyj.suxk.dao.UserDao;
import com.bysj.xqyj.suxk.pojo.Student;
import com.bysj.xqyj.suxk.pojo.Teacher;
import com.bysj.xqyj.suxk.pojo.User;
import com.bysj.xqyj.suxk.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service("UserService")
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;
    public User selUserById(String uid){
        return userDao.selUserById(uid);
    }

    @Override
    public Integer resetPassword(String uid, String password) {
        return userDao.resetPassword(uid,password);
    }

    @Override
    public User selUserByEmail(String email) {
        return userDao.selUserByEmail(email);
    }

    @Override
    public Integer addUser(User user) {
        return userDao.addUser(user);
    }

    @Override
    public List<User> findAllUsers() {
        return userDao.findAddUsers();
    }

    @Override
    public Integer addStudent(Student student) {
        return userDao.addStudent(student);
    }

    @Override
    public Integer addTeacher(Teacher teacher) {
        return userDao.addTeacher(teacher);
    }

    @Override
    public Student selStudentBySid(String sid) {
        return userDao.selStudentBySid(sid);
    }

    @Override
    public Integer updateStudentInfo(Integer sex, String student_college, String student_major, String start_year, String student_address, String student_email, String student_phone, String sid) {
        return userDao.updateUserInfo(sex, student_college, student_major, start_year,student_address,student_email,student_phone, sid);
    }

    @Override
    public Teacher selTeacherByTid(String tid) {
        return userDao.selTeacherByTid(tid);
    }
    @Override
    public Integer updateTeacherInfo(String teacher_name, Integer teacher_sex, String teacher_college, Date teacher_time, String teacher_address, String teacher_email, String teacher_phone, String tid) {
        return userDao.updateTeacherInfo(teacher_name,teacher_sex,teacher_college,teacher_time,teacher_address,teacher_email,teacher_phone,tid);
    }

    @Override
    public List selAllStudent() {
        return userDao.selAllStudent();
    }

    @Override
    public List selAllTeacher() {
        return userDao.selAllTeacher();
    }

    @Override
    public Integer delStudent(Integer remove_tag, String sid) {
        userDao.delUserByUid(sid);
        return userDao.delStudent(remove_tag,sid);
    }

    @Override
    public Integer delTeacher(int remove_tag, String tid) {
        userDao.delUserByUid(tid);
        return userDao.delTeacher(remove_tag,tid);
    }


}
