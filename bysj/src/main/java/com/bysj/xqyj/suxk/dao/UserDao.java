package com.bysj.xqyj.suxk.dao;

import com.bysj.xqyj.suxk.pojo.Student;
import com.bysj.xqyj.suxk.pojo.Teacher;
import com.bysj.xqyj.suxk.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import javax.xml.crypto.Data;
import java.util.Date;
import java.util.List;

@Mapper
public interface UserDao {
    @Select("SELECT * FROM xqyjxm.user WHERE user.uid=#{uid} and status='1'")
    User selUserById(String uid);
    @Update("UPDATE xqyjxm.user SET PASSWORD = #{1}  WHERE uid = #{0}")
    Integer resetPassword(String uid,String password);
    @Select("SELECT * FROM xqyjxm.user WHERE user.email=#{email} and status='1'")
    User selUserByEmail(String email);
    Integer addUser(User user);
    @Select("select * from  xqyjxm.user WHERE user.status='1'")
    List<User> findAddUsers();

    Integer addStudent(Student student);

    Integer addTeacher(Teacher teacher);
    @Select("SELECT * FROM xqyjxm.student WHERE sid=#{sid}")
    Student selStudentBySid(String sid);
    //修改学生信息
    @Update("UPDATE xqyjxm.student \n" +
            "\tSET\n" +
            "\tsex = #{sex} , \n" +
            "\tstudent_college = #{student_college} , \n" +
            "\tstudent_major = #{student_major} , \n" +
            "\tstart_year = #{start_year} , \n" +
            "\tstudent_address = #{student_address} , \n" +
            "\tstudent_email = #{student_email} , \n" +
            "\tstudent_phone = #{student_phone}\n" +
            "\tWHERE\n" +
            "\tsid = #{sid};")
    Integer updateUserInfo(@Param("sex")Integer sex, @Param("student_college")String student_college, @Param("student_major")String student_major,
                           @Param("start_year")String start_year, @Param("student_address")String student_address, @Param("student_email")String student_email, @Param("student_phone")String student_phone
                           , @Param("sid")String sid);
    @Select("SELECT * FROM xqyjxm.teacher WHERE tid=#{tid}")
    Teacher selTeacherByTid(String tid);

    //修改教师信息
    @Update("UPDATE xqyjxm.teacher \n" +
            "\tSET\n" +
            "\tteacher_name = #{teacher_name} , \n" +
            "\tteacher_sex = #{teacher_sex} , \n" +
            "\tteacher_college = #{teacher_college} , \n" +
            "\tteacher_time = #{teacher_time} , \n" +
            "\tteacher_address = #{teacher_address} , \n" +
            "\tteacher_email = #{teacher_email} , \n" +
            "\tteacher_phone = #{teacher_phone}\n" +
            "\t\n" +
            "\tWHERE\n" +
            "\ttid = #{tid} ;\n")
    Integer updateTeacherInfo(@Param("teacher_name")String teacher_name,@Param("teacher_sex")Integer teacher_sex,@Param("teacher_college")String teacher_college,@Param("teacher_time")Date teacher_time,
                              @Param("teacher_address")String teacher_address,@Param("teacher_email")String teacher_email,@Param("teacher_phone")String teacher_phone,@Param("tid")String tid);

    //删除用户，置为失效
    @Update("UPDATE xqyjxm.user SET\tSTATUS = '0' WHERE uid = #{uid} ")
    Integer delUserByUid(String uid);

    @Select("SELECT \t* FROM xqyjxm.student WHERE remove_tag='1' ORDER BY score DESC ")
    List<Student> selAllStudent();

    @Select("SELECT \t* FROM xqyjxm.teacher where remove_tag=1 ")
    List<Teacher> selAllTeacher();
    @Select("UPDATE xqyjxm.student \tSET\tstudent.remove_tag = #{remove_tag} \tWHERE\tsid = #{sid}")
    Integer delStudent(@Param("remove_tag")Integer remove_tag,@Param("sid")String sid);

    @Select("UPDATE xqyjxm.teacher \tSET\tteacher.remove_tag = #{remove_tag} \tWHERE\ttid = #{tid}")
    Integer delTeacher(@Param("remove_tag")int remove_tag, @Param("tid")String tid);
}
