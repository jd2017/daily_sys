package com.yun.daily.report.controller;

import com.yun.common.Page;
import com.yun.daily.personUser.controller.PersonUserController;
import com.yun.daily.report.Service.ReportService;
import com.yun.daily.report.domain.Report;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/api/v1/report",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class ReportController {
    @Autowired
    private ReportService reportService;
    private Logger log = LoggerFactory.getLogger(PersonUserController.class);
    private DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    @RequestMapping("/reports")
    public  String reports(){
        return "report/reports";
    }

    @RequestMapping("/save")
    @ResponseBody
    public  String save(Report report){
        report.setAccount("暂没设置");
        report.setAuthorName("暂没设置");
        String nowStr = df.format(LocalDateTime.now());
        report.setCreateTime(nowStr);
        report.setUpdateTime(nowStr);
        int result = reportService.insert(report);
        return "reports";
    }

    @RequestMapping("/deleteByID")
    @ResponseBody
    public  int deleteByID(Long reportId){
        int result = reportService.deleteById(reportId);
        return result;
    }

    @RequestMapping("/update")
    @ResponseBody
    public  int update(Report report){
        int result = reportService.update(report);
        return result;
    }

    @RequestMapping("/selectById")
    public  String selectById(Map<String,Object> model, Long reportId){
        model.put("user",reportService.selectById(reportId));
        return "reportDetail";
    }

    @RequestMapping("/selectByCondition")
    @ResponseBody
    public Page selectByCondition(int pageNumber, int pageSize, Report report){
        return reportService.selectByCondition(pageNumber,pageSize,report);
    }

}
