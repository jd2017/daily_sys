package com.yun.daily.report.controller;

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

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/api/v1/report",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class ReportController {
    @Autowired
    private ReportService reportService;
    private Logger log = LoggerFactory.getLogger(PersonUserController.class);

    @RequestMapping("/reports")
    public  String reports(){
        return "report/reports";
    }

    @RequestMapping("/save")
    @ResponseBody
    public  String save(Map<String,Object> model, Report report){
        int result = reportService.insert(report);
        return "reports";
    }

    @RequestMapping("/deleteByID")
    @ResponseBody
    public  String deleteByID(Map<String,Object> model, Long reportId){
        int result = reportService.deleteById(reportId);
        return "reports";
    }

    @RequestMapping("/update")
    @ResponseBody
    public  String update(Map<String,Object> model, Report report){
        int result = reportService.update(report);
        return "";
    }

    @RequestMapping("/selectById")
    public  String selectById(Map<String,Object> model, Long reportId){
        model.put("user",reportService.selectById(reportId));
        return "reportDetail";
    }

    @RequestMapping("/selectByCondition")
    @ResponseBody
    public  List<Report> selectByCondition(Report report){
        List<Report> reports = reportService.selectByCondition(report);
        return reports;
    }

}
