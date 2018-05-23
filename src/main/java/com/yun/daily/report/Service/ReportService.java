package com.yun.daily.report.Service;

import com.yun.daily.report.dao.ReportDao;
import com.yun.daily.report.domain.Report;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReportService {
    @Autowired
    private ReportDao reportDao;
    /**
     * 根据id删除记录
     * @param reportId
     * @return
     */
    int deleteById(Long reportId){
        return reportDao.deleteById(reportId);
    }

    /**
     * 根据id更新记录
     * @param record
     * @return
     */
    public int update(Report record){
        return reportDao.update(record);
    }

    /**
     * 根据id查询记录
     * @param reportId
     * @return
     */
    public Report selectById(Long reportId){
        return reportDao.selectById(reportId);
    }

    /**
     * 模糊查询
     * @param report
     * @return
     */
    public List<Report> selectByCondition(Report report){
        return reportDao.selectByCondition(report);
    }
}
