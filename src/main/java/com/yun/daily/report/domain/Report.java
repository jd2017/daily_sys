package com.yun.daily.report.domain;

import lombok.Data;

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

    private Date createTime;

    private Date updateTime;


    private String authorName;

}