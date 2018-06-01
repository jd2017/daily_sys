package com.yun.daily.report.dao;

import com.yun.daily.report.domain.Report;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface ReportDao {

    /**
     * 根据id更新记录
     * @param record
     * @return
     */
    int insert(Report record);

    /**
     * 根据id删除记录
     * @param reportId
     * @return
     */
    int deleteById(Long reportId);

    /**
     * 根据id更新记录
     * @param record
     * @return
     */
    int update(Report record);

    /**
     * 根据id查询记录
     * @param reportId
     * @return
     */
    Report selectById(Long reportId);

    /**
     * 模糊查询
     * @param ids
     * @return
     */
    List<Report> selectByCondition(List<Long> ids);

    /**
     * 模糊查询
     * @param param
     * @return
     */
    List<Long> selectIdsByCondition(Map<String, Object> param);
}