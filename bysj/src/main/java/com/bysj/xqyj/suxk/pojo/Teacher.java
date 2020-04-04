package com.bysj.xqyj.suxk.pojo;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class Teacher {
    @Excel(name = "教工号")
    private String tid;
    @Excel(name = "姓名")
    private String teacher_name;
//    private String teacher_password;
    @Excel(name = "性别",replace = {"男_1","女_0"})
    private Integer teacher_sex;
    @Excel(name = "学院")
    private String teacher_college;
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @Excel(name = "任教年份",importFormat = "yyyy-MM-dd",exportFormat = "yyyy-MM-dd",width = 20)
    private Date teacher_time;
    private Integer remove_tag;
    @Excel(name = "住址")
    private String teacher_address;
    @Excel(name = "邮箱",width = 25)
    private String teacher_email;
    @Excel(name = "电话",width = 25)
    private String teacher_phone;


}
