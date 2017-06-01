package indi.graduate.assistant.dao;

import java.util.List;
import java.util.Map;

import indi.graduate.assistant.dto.ScheduleDto;
import indi.graduate.assistant.model.InterviewDetailBean;

public interface InterviewDetailBeanMapper {

    int insertSelective(InterviewDetailBean record);
    
    /**获取详情面试**/
    List<ScheduleDto> schedule(String interviewId);
    int scheduleNum(String interviewId);
    
    /**查看是否答过此题**/
    InterviewDetailBean selectUnique(Map<String,Object> map);
    
    /**生成面试报告**/
    List<InterviewDetailBean> getReport(Integer interviewId);
}