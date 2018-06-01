package com.yun.common;


import lombok.Data;

import java.util.List;

@Data
public class Page {
    private List<?> rows;
    private Long pageSize;  //页面显示的内容条数
    private Long total;		//总条数
    private Long pageNumber;   //
    private Long currentPage;   //当前页
}
