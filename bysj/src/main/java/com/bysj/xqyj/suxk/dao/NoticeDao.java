package com.bysj.xqyj.suxk.dao;

import com.bysj.xqyj.suxk.pojo.WarningNotice;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface NoticeDao {

    Integer addNotice(WarningNotice warningNotice);

    //学生查看我的通知
    @Select("SELECT \t*\tFROM \txqyjxm.warning_notice \tWHERE notice_sid=#{notice_sid}\n")
    List<WarningNotice> selStudentNotice(String notice_sid);
    //教师查看通知
    @Select("SELECT \t*\tFROM \txqyjxm.warning_notice \tWHERE notice_uid=#{notice_uid}\n")
    List<WarningNotice> selTeacherNotice(String notice_uid);
    //管理员
    @Select("SELECT \t*\tFROM \txqyjxm.warning_notice")
    List<WarningNotice> selAllNotice();
    //已读
    @Update("UPDATE xqyjxm.warning_notice \n" +
            "\tSET\n" +
            "\twatching_tag = 1\n" +
            "\tWHERE\n" +
            "\tnid = #{nid}")
    Integer noticeWatch(@Param("nid")String nid);
}
