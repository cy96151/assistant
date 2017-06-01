package indi.graduate.assistant.dto;

/** 获取试题 **/
public class QuestionDto {
	/** 面试编号 **/
	private int interviewId;
	/** 题号 **/
	private int questionId;
	/** 题目类型 **/
	private int questionType;
	/** 掌握情况 **/
	private int idUnderstanding;
	/** 备注 **/
	private String idRemark;
	/**适用场景**/
	private int questionFaceWay;
	private int oldQuestionType;
	/**
	 * 判断是否将questionId置为0，默认为false
	 * @return
	 */
	private boolean isNewQuestionType;
	

	public boolean getIsNewQuestionType() {
		return isNewQuestionType;
	}

	public int getOldQuestionType() {
		return oldQuestionType;
	}

	public void setOldQuestionType(int oldQuestionType) {
		this.oldQuestionType = oldQuestionType;
	}

	public void setIsNewQuestionType(boolean isNewQuestionType) {
		this.isNewQuestionType = isNewQuestionType;
	}

	public int getQuestionFaceWay() {
		return questionFaceWay;
	}

	public void setQuestionFaceWay(int questionFaceWay) {
		this.questionFaceWay = questionFaceWay;
	}

	public int getInterviewId() {
		return interviewId;
	}

	public void setInterviewId(int interviewId) {
		this.interviewId = interviewId;
	}

	public int getQuestionId() {
		return questionId;
	}

	public void setQuestionId(int questionId) {
		this.questionId = questionId;
	}

	public int getQuestionType() {
		return questionType;
	}

	public void setQuestionType(int questionType) {
		this.questionType = questionType;
	}

	public int getIdUnderstanding() {
		return idUnderstanding;
	}

	public void setIdUnderstanding(int idUnderstanding) {
		this.idUnderstanding = idUnderstanding;
	}

	public String getIdRemark() {
		return idRemark;
	}

	public void setIdRemark(String idRemark) {
		this.idRemark = idRemark;
	}

}
