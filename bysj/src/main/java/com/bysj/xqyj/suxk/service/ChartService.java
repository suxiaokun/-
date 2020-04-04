package com.bysj.xqyj.suxk.service;

import com.bysj.xqyj.suxk.pojo.Chart;

import java.util.List;

public interface ChartService {
    List<Chart> levelSum();

    List<Chart> reviewSum(String sid);

    List<Chart> teacher_course_chart();

    List<Chart> teacher_notice();

    List<Chart> teacher_major_chart(String tid);
}
