package com.bysj.xqyj.suxk.pojo;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/***
 * 学生表
 */
@Data
public class Student {
    @Excel(name = "学号")
    private String sid;
    @Excel(name = "姓名")
    private String student_name;
//    private String student_password;
    @Excel(name = "性别",replace = {"男_1","女_0"})
    private Integer sex;
    @Excel(name = "学院")
    private String student_college;
    @Excel(name = "专业")
    private String student_major;
    @Excel(name = "入学年份",width = 20)
    private String start_year;
    private Integer remove_tag;
    @Excel(name = "当前学分")
    private Double score;
    @Excel(name = "住址",width = 25)
    private String student_address;
    @Excel(name = "邮箱",width = 25)
    private String student_email;
    @Excel(name = "电话",width = 25)
    private String student_phone;

}
