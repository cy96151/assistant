package indi.graduate.assistant.dto;

/** 搜索问题列表 **/
public class QuestionSerchDto {

	private int PageNo;
	private int PageSize;
	// 题目类型
	private int questionType ;
	public int getPageNo() {
		return PageNo;
	}
	public void setPageNo(int pageNo) {
		PageNo = pageNo;
	}
	public int getPageSize() {
		return PageSize;
	}
	public void setPageSize(int pageSize) {
		PageSize = pageSize;
	}
	public int getQuestionType() {
		return questionType;
	}
	public void setQuestionType(int questionType) {
		this.questionType = questionType;
	}
	

}
