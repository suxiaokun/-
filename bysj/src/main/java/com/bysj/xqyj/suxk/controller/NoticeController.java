package com.bysj.xqyj.suxk.controller;

import com.bysj.xqyj.suxk.pojo.Student;
import com.bysj.xqyj.suxk.pojo.User;
import com.bysj.xqyj.suxk.pojo.WarningNotice;
import com.bysj.xqyj.suxk.service.NoticeService;
import com.bysj.xqyj.suxk.service.UserService;
import com.mysql.cj.protocol.x.Notice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class NoticeController {
    @Autowired
    private JavaMailSender mailSender;
    @Autowired
    private NoticeService noticeService;
    @Autowired
    private UserService userService;
    @RequestMapping("sendNotice")
    public String sendNotice(String sid,Model model){
        Student student=userService.selStudentBySid(sid);
        model.addAttribute("student",student);
        return "add-notice";
    }
    @RequestMapping("sendNoticeing")
    public String sendNoticeing(WarningNotice warningNotice, Model model, HttpSession session){
        User user=(User)session.getAttribute("user");
        warningNotice.setNotice_date(new Date());
        warningNotice.setNotice_uid(user.getUid());
        warningNotice.setWatching_tag(0);
        warningNotice.setNotice_user(user.getUsername());
        Integer num=noticeService.addNotice(warningNotice);
        //发邮件
        SimpleMailMessage message = new SimpleMailMessage();
        String student_email=userService.selStudentBySid(warningNotice.getNotice_sid()).getStudent_email();
        String teacher_email=user.getEmail();
        message.setFrom("1224692968@qq.com");
        message.setTo("1224692968@qq.com");
        message.setSubject("主题：预警邮件通知");
        message.setText(warningNotice.getNotice_content());
        mailSender.send(message);
        model.addAttribute("msg","已成功发送该同学邮箱并提醒");
        return "successful";
    }

    //查看通知列表
    @RequestMapping("showMyNotice")
    public String showMyNotice(Model model,HttpSession session){
        User user=(User)session.getAttribute("user");
        List<WarningNotice> warningNoticeList=noticeService.showMynotice(user);
        if (user.getRole()==0){
            Student student=userService.selStudentBySid(user.getUid());
            model.addAttribute("student",student);
        }
        model.addAttribute("notices",warningNoticeList);
        return "notice-list";
    }
    @RequestMapping("noticeWatch")
    @ResponseBody
    public Map noticeWatch(String nid){
        Map map=new HashMap();
        int num=noticeService.noticeWatch(nid);
        map.put("result","已读");
        return map;

    }
}
