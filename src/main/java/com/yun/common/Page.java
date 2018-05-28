package com.yun.common;


import lombok.Data;

import java.util.List;

@Data
public class Page {
    private List<?> rows;
    private int pageSize;  //页面显示的内容条数
    private int total;		//总条数
    private int pageNumber;   //
    private int currentPage;   //当前页
}
