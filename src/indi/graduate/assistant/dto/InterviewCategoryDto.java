package indi.graduate.assistant.dto;

public class InterviewCategoryDto {

	/**序号**/
	private Integer interviewCategoryId;
	
	/**面试类型**/
	private String interviewCategoryType;
	
	/**面试问题类型**/
	private String interviewCategoryQuestionType;
	private String interviewCategoryQuestionType1;
	public Integer getInterviewCategoryId() {
		return interviewCategoryId;
	}
	public void setInterviewCategoryId(Integer interviewCategoryId) {
		this.interviewCategoryId = interviewCategoryId;
	}
	public String getInterviewCategoryType() {
		return interviewCategoryType;
	}
	public void setInterviewCategoryType(String interviewCategoryType) {
		this.interviewCategoryType = interviewCategoryType;
	}
	public String getInterviewCategoryQuestionType() {
		return interviewCategoryQuestionType;
	}
	public void setInterviewCategoryQuestionType(String interviewCategoryQuestionType) {
		this.interviewCategoryQuestionType = interviewCategoryQuestionType;
	}
	public String getInterviewCategoryQuestionType1() {
		return interviewCategoryQuestionType1;
	}
	public void setInterviewCategoryQuestionType1(String interviewCategoryQuestionType1) {
		this.interviewCategoryQuestionType1 = interviewCategoryQuestionType1;
	}
	
	
}
