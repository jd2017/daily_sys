package com.yun.daily.report.domain;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;
@Data
public class Report {
    private Long reportId;

    private String account;

    private String title;

    private String content;

    private String type;

    private String typeValue;

    private String summary;

    private String plan;

    private String createTime;

    private String  updateTime;


    //查询辅助
    private String authorName;
    private String beginTime;
    private String endTime;

}