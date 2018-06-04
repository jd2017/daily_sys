package com.yun.daily.report.Service;

import com.yun.common.Page;
import com.yun.daily.report.dao.ReportDao;
import com.yun.daily.report.domain.Report;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ReportService {
    @Autowired
    private ReportDao reportDao;

    /**
     * 根据id删除记录
     * @param record
     * @return
     */
    public int insert(Report record){
        return reportDao.insert(record);
    }

    /**
     * 根据id删除记录
     * @param reportId
     * @return
     */
    public int deleteById(Long reportId){
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
     * @param typeValue
     * @return
     */
    public Report selectByTypeValue(String typeValue,String account){
        Map map =new HashMap();
        map.put("typeValue",typeValue);
        map.put("account",account);
        return reportDao.selectByTypeValue(map);
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
    public Page selectByCondition(Long pageNumber,Long pageSize,Report report){
        /*所需参数*/
        Map<String, Object> param=new HashMap<String, Object>();
        Long first=(pageNumber-1)*pageSize;
        param.put("report", report);
        Long total= Long.valueOf(reportDao.selectIdsByCondition(param).size());
        param.put("first", first);
        if((pageNumber*pageSize)>total){
            param.put("pageSize", total-first);
        }else {
            param.put("pageSize", pageSize);
        }
        List<Long> ids = reportDao.selectIdsByCondition(param);
        Page page = new Page();
        List<Report> rows=reportDao.selectByCondition(ids);//应该加上分页参数
        page.setTotal(total);
        page.setRows(rows);
        return page;
    }
}
