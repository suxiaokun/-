package com.bysj.xqyj.suxk.service;

import com.bysj.xqyj.suxk.pojo.User;
import com.bysj.xqyj.suxk.pojo.WarningNotice;

import java.util.List;

public interface NoticeService {
    Integer addNotice(WarningNotice warningNotice);

    List<WarningNotice> selStudentNotice(String notice_sid);

    List<WarningNotice> showMynotice(User user);

    int noticeWatch(String nid);
}
