package com.bysj.xqyj.suxk.pojo;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class StudentReview {
    @Excel(name = "审核主题",width = 20)
    private String review_title;//审核主题
    private String sid;
    @Excel(name = "审核对象")
    private String student_name;
    @Excel(name = "审核内容",width = 30)
    private String review_content;//具体内容
    @Excel(name = "表现情况",width = 30)
    private String student_performance;//表现情况
    @Excel(name = "活动所获学分")
    private Double get_score;//获得学分
    private Integer rid;
    private String review_tid;
    @Excel(name = "评审老师")
    private String review_teacher;
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @Excel(name = "评审时间",importFormat = "yyyy-MM-dd",exportFormat = "yyyy-MM-dd",width = 20)
    private Date review_time;
}
