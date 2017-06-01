package indi.graduate.assistant.dao;

import java.util.List;
import java.util.Map;

import indi.graduate.assistant.dto.IntervieweeDto;
import indi.graduate.assistant.model.IntervieweeBean;

public interface IntervieweeBeanMapper {
	List<Integer> selectIntervieweeCount();
    int deleteByPrimaryKey(Integer intervieweeId);
    int insert(IntervieweeBean record);
    int insertSelective(IntervieweeBean record);
    IntervieweeBean selectByPrimaryKey(Integer intervieweeId);
    int updateByPrimaryKeySelective(IntervieweeBean record);
    /**查找面试人列表**/
    List<IntervieweeDto> selectIntervieweeList(Map<String, Object> mm);
    int countAccounts(Map<String, Object> mm);
    /**查询最新ID**/
    int selectLastId();
    /*更新面试人状态*/
    void updateStatussByPrimaryKey(IntervieweeBean intervieweeBean);
}