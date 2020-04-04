package com.bysj.xqyj.suxk.pojo;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/***
 * 预警下发通知
 */
@Data
public class WarningNotice {
    private Integer nid;
    private String notice_user;
    private String notice_uid;
    private String notice_student;
    private String notice_sid;
    private String notice_content;
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @Excel(name = "下发时间",importFormat = "yyyy-MM-dd",exportFormat = "yyyy-MM-dd",width = 20)
    private Date notice_date;
    private Integer Watching_tag;

}
