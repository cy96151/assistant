package indi.graduate.assistant.dto;

/** 添加问题列表 **/
public class AddQuestionDto {
	/** 题号 **/
	private String questionId;
	
	/** 题目主干 **/
	private String questionTopic;
	
	/** 难易程度 **/
	private int questionDifficulty;
	
	/** 题目类型 **/
	private int questionType;
	
	/** 适用场景 **/
	private int faceWay1;
	private int faceWay2;
	private int faceWay3;
	
	/** 题目详情 **/
	private String questionQuestion;
	
	/** 参考答案 **/
	private String questionAnswers;

	public String getQuestionId() {
		return questionId;
	}

	public void setQuestionId(String questionId) {
		this.questionId = questionId;
	}

	public String getQuestionTopic() {
		return questionTopic;
	}

	public void setQuestionTopic(String questionTopic) {
		this.questionTopic = questionTopic;
	}

	public int getQuestionDifficulty() {
		return questionDifficulty;
	}

	public void setQuestionDifficulty(int questionDifficulty) {
		this.questionDifficulty = questionDifficulty;
	}

	public int getQuestionType() {
		return questionType;
	}

	public void setQuestionType(int questionType) {
		this.questionType = questionType;
	}

	public int getFaceWay1() {
		return faceWay1;
	}

	public void setFaceWay1(int faceWay1) {
		this.faceWay1 = faceWay1;
	}

	public int getFaceWay2() {
		return faceWay2;
	}

	public void setFaceWay2(int faceWay2) {
		this.faceWay2 = faceWay2;
	}

	public int getFaceWay3() {
		return faceWay3;
	}

	public void setFaceWay3(int faceWay3) {
		this.faceWay3 = faceWay3;
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
