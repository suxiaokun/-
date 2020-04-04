package com.bysj.xqyj.suxk.service.impl;

import com.bysj.xqyj.suxk.dao.NoticeDao;
import com.bysj.xqyj.suxk.pojo.User;
import com.bysj.xqyj.suxk.pojo.WarningNotice;
import com.bysj.xqyj.suxk.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("NoticeService")
public class NoticeServiceImpl implements NoticeService{
    @Autowired
    private NoticeDao noticeDao;

    @Override
    public Integer addNotice(WarningNotice warningNotice) {
        return noticeDao.addNotice(warningNotice);
    }

    @Override
    public List<WarningNotice> selStudentNotice(String notice_sid) {
        return noticeDao.selStudentNotice(notice_sid);
    }

    @Override
    public List<WarningNotice> showMynotice(User user) {
        if (user.getRole()==0){
            return noticeDao.selStudentNotice(user.getUid());
        }else if(user.getRole()==1){
            return noticeDao.selTeacherNotice(user.getUid());
        }else if(user.getRole()==2){
            return noticeDao.selAllNotice();
        }
        return null;
    }

    @Override
    public int noticeWatch(String nid) {
        return noticeDao.noticeWatch(nid);
    }

}
