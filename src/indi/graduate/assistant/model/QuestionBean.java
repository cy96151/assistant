package indi.graduate.assistant.model;

public class QuestionBean {
	/**
	 * 序号
	 */
	private Integer questionId;

	/**
	 * 题目类型
	 */
	private Integer questionType;

	/**
	 * 难易程度
	 */
	private Integer questionDifficulty;

	/**
	 * 使用场景 111代表现场，电话，视频权限',
	 */
	private Integer questionFaceWay;

	public Integer getQuestionId() {
		return questionId;
	}

	public void setQuestionId(Integer questionId) {
		this.questionId = questionId;
	}

	public Integer getQuestionType() {
		return questionType;
	}

	public void setQuestionType(Integer questionType) {
		this.questionType = questionType;
	}

	public Integer getQuestionDifficulty() {
		return questionDifficulty;
	}

	public void setQuestionDifficulty(Integer questionDifficulty) {
		this.questionDifficulty = questionDifficulty;
	}

	public Integer getQuestionFaceWay() {
		return questionFaceWay;
	}

	public void setQuestionFaceWay(Integer questionFaceWay) {
		this.questionFaceWay = questionFaceWay;
	}
}