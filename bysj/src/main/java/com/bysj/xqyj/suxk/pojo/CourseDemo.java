package com.bysj.xqyj.suxk.pojo;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

//课程表
@Data
public class CourseDemo {
    @Excel(name = "课程名",width = 25)
    private String course_name;
    @Excel(name = "开课时间",importFormat = "yyyy-MM-dd",exportFormat = "yyyy-MM-dd",width = 20)
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date start_time;
    @Excel(name = "开课专业",width = 25)
    private String course_major;
    @Excel(name = "开课年级",width = 25)
    private String course_grade;

}
