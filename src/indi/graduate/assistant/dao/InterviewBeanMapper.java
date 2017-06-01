package indi.graduate.assistant.dao;

import java.util.List;
import java.util.Map;

import indi.graduate.assistant.dto.IsStartUpInterviewDto;
import indi.graduate.assistant.model.InterviewBean;

public interface InterviewBeanMapper {

    int insertSelective(InterviewBean record);

    InterviewBean selectByPrimaryKey(Integer interviewId);

    int updateByPrimaryKeySelective(InterviewBean record);

    //更新面试 
    int updateByPrimaryKey(InterviewBean record);
    
    //查询面试信息
    IsStartUpInterviewDto selectIsStartUpInterview(int interviewId);
    //面试人列表
    List<IsStartUpInterviewDto> selectInterviewList(Map<String, Object> mm);
    int countAccounts(Map<String, Object> mm);
    //更新面试状态
    void updateEvaluateByPrimaryKey(InterviewBean record);
    /**面试人面试列表**/
    List<IsStartUpInterviewDto> intervieeDetailList(int intervieweeId);
    List<Integer> selectInterviewCount();
}