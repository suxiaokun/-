package com.bysj.xqyj.suxk.service.impl;

import com.bysj.xqyj.suxk.dao.ChartDao;
import com.bysj.xqyj.suxk.pojo.Chart;
import com.bysj.xqyj.suxk.service.ChartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("ChartService")
public class ChartServiceImpl implements ChartService {
    @Autowired
    private ChartDao chartDao;

    @Override
    public List<Chart> levelSum() {
        return chartDao.levelSum();
    }

    @Override
    public List<Chart> reviewSum(String sid) {
        return chartDao.reviewSum(sid);
    }

    @Override
    public List<Chart> teacher_course_chart() {
        return chartDao.teacher_course();
    }

    @Override
    public List<Chart> teacher_notice() {
        return chartDao.teacher_course();
    }

    @Override
    public List<Chart> teacher_major_chart(String tid) {
        return chartDao.teacher_major_course(tid);
    }
}
