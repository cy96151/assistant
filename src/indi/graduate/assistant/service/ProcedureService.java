package indi.graduate.assistant.service;

import java.util.List;
import java.util.Map;

import indi.graduate.assistant.dto.InterviewSearchDto;
import indi.graduate.assistant.dto.IsStartUpInterviewDto;
import indi.graduate.assistant.dto.QuestionDto;
import indi.graduate.assistant.model.IntervieweeBean;

public interface ProcedureService {
	/** 获取面试人列表 **/
	public Map<String, Object> startIntervieweeList(InterviewSearchDto interviewSearchDto);

	/** 启动面试过程 **/
	public IsStartUpInterviewDto startprocedure(int interviewId, int interviewWay, int interviewType);

	/** 获取试题 **/
	public Map<String, Object> getQuestion(int questionFaceWay, int questionType, int questionId, int interviewId);

	/** 下一题 **/
	public Map<String, Object> nextQuestion(QuestionDto questionDto);

	/** 面试详情 **/
	public Map<String, Object> getSchedule(String interviewId);

	/** 获取面试题目数量 **/
	public Map<String, Object> getScheduleNum(String interviewId);

	/* 生成面试报告 */
	public Map<String, Object> createReport(int interviewId);

	/* 返回问题类型分类 */
	public Map<Integer, Integer> questionClassify(List<Integer> list);

	/* 根据面试id返回面试人信息 */
	public IntervieweeBean getIntervieweeInfo(int interviewId);

	/* 更改面试状态 */
	public boolean modifyInterview(int interviewId, String businRemark);

	/** 获取面试题目类型 **/
	public Map<String, Object> interviewCategory();

	/** 添加面试类型设置 **/
	public Map<String, Object> addinterviewCategory(String interviewCategoryType, int[] interviewCategoryQuestionType);

	/** 隐藏掌握情况 **/
	public Map<String, Object> hideUnderstanding(int dictionaryId, int oldQuestionType, int interviewId);
}
