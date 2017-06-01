package indi.graduate.assistant.service;

import java.util.Map;

import indi.graduate.assistant.dto.InterviewSearchDto;

public interface InterviewCategoryService {
	
	/**显示面试类型列表**/
	public Map<String,Object> interviewCategory(InterviewSearchDto interviewSearchDto);

	/**修改面试类型**/
	public Map<String, Object> editInterviewCategory(int interviewCategoryId,String interviewCategoryType,int[] interviewCategoryQuestionType);
	
	/**删除问题类别**/
	public Map<String,Object> delInterviewCategory(int id);
	
	/**获取面试类型**/
	public Map<String, Object> getInterviewCategory();
	
	/**获取面试问题类型**/
	public Map<String, Object> getQuestionCategory(int interviewType,int interviewId);
}
