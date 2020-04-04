package com.bysj.xqyj.suxk.pojo;

import lombok.Data;

/***
 * 预警等级
 */

@Data
public class WarningLevel {
    private Integer level;
    private String describe;
    private String describe_detail;
    private double warning_range;
    private String tips;
}
