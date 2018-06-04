package com.yun.daily.report.controller;

import com.yun.common.Page;
import com.yun.daily.personUser.controller.PersonUserController;
import com.yun.daily.personUser.domain.PersonUser;
import com.yun.daily.personUser.service.PersonUserService;
import com.yun.daily.report.Service.ReportService;
import com.yun.daily.report.domain.Report;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
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
    private PersonUserService personUserService;
    @Autowired
    private ReportService reportService;
    private Logger log = LoggerFactory.getLogger(PersonUserController.class);
    private DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    @RequestMapping("/reports")
    public  String reports(){
        return "report/reports";
    }

    @RequestMapping("/reports-day")
    public  String reportsDay(Map<String,Object> model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails)authentication.getPrincipal();
        PersonUser personUser = personUserService.queryByAccount(userDetails.getUsername());
        model.put("person",personUser);
        return "report/reports-day";
    }

    @RequestMapping("/save")
    @ResponseBody
    public  int save(Map map,Report report){
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Report report_ = reportService.selectByTypeValue(report.getTypeValue(),userDetails.getUsername());
        int result =0;
        if(report_==null){
            report.setAccount(userDetails.getUsername());
            String nowStr = df.format(LocalDateTime.now());
            report.setCreateTime(nowStr);
            report.setUpdateTime(nowStr);
            result = reportService.insert(report);
        }
        return result;
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
        String nowStr = df.format(LocalDateTime.now());
        report.setUpdateTime(nowStr);
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
    public Page selectByCondition(Long pageNumber, Long pageSize, Report report){
        return reportService.selectByCondition(pageNumber,pageSize,report);
    }

}
