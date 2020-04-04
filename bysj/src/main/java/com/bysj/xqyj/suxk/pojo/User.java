package com.bysj.xqyj.suxk.pojo;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;
//user 用data包自动生成get/set方法
@Data
public class User {
    @Excel(name = "账号" ,width = 20)
    private String uid;
    @Excel(name = "姓名")
    private String username;
    private String password;
    @Excel(name = "身份")
    private Integer role;
    private Integer status;
    @Excel(name = "邮箱",width=30)
    private String email;

}
