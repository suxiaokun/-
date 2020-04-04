package com.bysj.xqyj.suxk.service;

import com.bysj.xqyj.suxk.pojo.Student;
import com.bysj.xqyj.suxk.pojo.Teacher;
import com.bysj.xqyj.suxk.pojo.User;

import java.util.Date;
import java.util.List;

public interface UserService {
    User selUserById(String uid);
    Integer resetPassword(String uid,String password);
    User selUserByEmail(String email);
    Integer addUser(User user);
    List<User> findAllUsers();

    Integer addStudent(Student student);

    Integer addTeacher(Teacher teacher);

    Student selStudentBySid(String sid);

    Integer updateStudentInfo(Integer sex, String student_college, String student_major, String start_year, String student_address, String student_email, String student_phone, String sid);

    Teacher selTeacherByTid(String tid);

    Integer updateTeacherInfo(String teacher_name, Integer teacher_sex, String teacher_college, Date teacher_time, String teacher_address, String teacher_email, String teacher_phone, String tid);

    List<Student> selAllStudent();
    List<Teacher> selAllTeacher();

    Integer delStudent(Integer remove_tag,String sid);

    Integer delTeacher(int remove_tag, String tid);
}
