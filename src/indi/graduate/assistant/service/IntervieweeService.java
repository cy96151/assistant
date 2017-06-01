package indi.graduate.assistant.service;

import java.util.Map;

import indi.graduate.assistant.dto.AddIntervieweeListDto;
import indi.graduate.assistant.dto.InterviewSearchDto;

public interface IntervieweeService {
	/** 获取面试人员列表 **/
	public Map<String, Object> getinterviewee(InterviewSearchDto interviewSearchDto) ;
	/** 新增面试人信息 **/
	public Map<String, Object> addInterviewee(AddIntervieweeListDto addIntervieweeList) ;
	/** 修改面试人信息 **/
	public Map<String, Object> editInterviewee(AddIntervieweeListDto addIntervieweeList) ;
	/** 删除面试人信息 **/
	public Map<String, Object> delIntervieweeList(String id) ;
	/** 归档 **/
	public Map<String, Object> stopStatus(String id) ;
	/** 添加面试官 **/
	public Map<String, Object> addInterviewer(AddIntervieweeListDto addIntervieweeList) ;
	/**判断是否输入为空**/
	public Map<String, Object> isEmpty(AddIntervieweeListDto addIntervieweeList);
	/**获取面试人面试列表**/
	public Map<String, Object> intervieeDetailShow(int intervieweeId);
	
}
