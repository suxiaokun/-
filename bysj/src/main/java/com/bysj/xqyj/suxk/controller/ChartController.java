package com.bysj.xqyj.suxk.controller;

import com.bysj.xqyj.suxk.pojo.Chart;
import com.bysj.xqyj.suxk.pojo.User;
import com.bysj.xqyj.suxk.service.ChartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class ChartController {
    @Autowired
    private ChartService chartService;
    @RequestMapping("levelSum")
    @ResponseBody
    public List<Chart> levelSum(Model model){
        List<Chart> chartList=chartService.levelSum();
        model.addAttribute("charts",chartList);
        System.out.println(chartList);
        return chartList;
    }

    //学生查询自己的各科获得学分表格
    @RequestMapping("reviewSum")
    @ResponseBody
    public List<Chart> reviewSum(Model model, HttpSession session){
        User user=(User)session.getAttribute("user");
        String sid=user.getUid();
        List<Chart> chartList=chartService.reviewSum(sid);
        model.addAttribute("charts",chartList);
        System.out.println(chartList);
        return chartList;
    }
    @RequestMapping("teacher_course_chart")
    @ResponseBody
    public List<Chart> teacher_course_chart(Model model){
        List<Chart> chartList=chartService.teacher_course_chart();
        model.addAttribute("charts",chartList);
        System.out.println(chartList);
        return chartList;
    }
    @RequestMapping("teacher_notice_chart")
    @ResponseBody
    public List<Chart> teacher_notice_chart(Model model){
        List<Chart> chartList=chartService.teacher_notice();
        model.addAttribute("charts",chartList);
        System.out.println(chartList);
        return chartList;
    }

    @RequestMapping("teacher_major_chart")
    @ResponseBody
    public List<Chart> teacher_major_chart(Model model,HttpSession session){
        User user=(User)session.getAttribute("user");
        String tid=user.getUid();
        List<Chart> chartList=chartService.teacher_major_chart(tid);
        model.addAttribute("charts",chartList);
        System.out.println(chartList);
        return chartList;
    }
}
