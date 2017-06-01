package indi.graduate.assistant.dto;

public class InterviewReportDto {
	private int interviewReportId;
	
	private String questionType;
	private String questionTopic;
	private String idUnderstanding;
	private String idRemark;
	private String idUnderstandingAndEva;
	private String questionQuestion;
	private String questionAnswers;
	public String getQuestionType() {
		return questionType;
	}
	public void setQuestionType(String questionType) {
		this.questionType = questionType;
	}
	public String getQuestionTopic() {
		return questionTopic;
	}
	public void setQuestionTopic(String questionTopic) {
		this.questionTopic = questionTopic;
	}
	public String getIdUnderstanding() {
		return idUnderstanding;
	}
	public void setIdUnderstanding(String idUnderstanding) {
		this.idUnderstanding = idUnderstanding;
	}
	public String getIdRemark() {
		return idRemark;
	}
	public void setIdRemark(String idRemark) {
		this.idRemark = idRemark;
	}
	public int getInterviewReportId() {
		return interviewReportId;
	}
	public void setInterviewReportId(int interviewReportId) {
		this.interviewReportId = interviewReportId;
	}
	public String getIdUnderstandingAndEva() {
		return idUnderstandingAndEva;
	}
	public void setIdUnderstandingAndEva(String idUnderstandingAndEva) {
		this.idUnderstandingAndEva = idUnderstandingAndEva;
	}
	public String getQuestionQuestion() {
		return questionQuestion;
	}
	public void setQuestionQuestion(String questionQuestion) {
		this.questionQuestion = questionQuestion;
	}
	public String getQuestionAnswers() {
		return questionAnswers;
	}
	public void setQuestionAnswers(String questionAnswers) {
		this.questionAnswers = questionAnswers;
	}

}
