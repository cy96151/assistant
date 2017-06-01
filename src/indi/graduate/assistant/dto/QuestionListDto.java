package indi.graduate.assistant.dto;

public class QuestionListDto {

	 /**
     * 题目
     */
    private String questionTopic;
    /**
     * 完整题目
     */
    private String questionQuestion;
    /**
     *答案
     */
    private String questionAnswers;
    /**
     * 序号
     */
    private Integer questionId;

    /**
     *题目类型
     */
    private Integer questionType;
    private String questionType1;

    /**
     * 难易程度
     */
    private Integer questionDifficulty;
    private String questionDifficultyStr;
    
    /**
     * 使用场景 111代表现场，电话，视频权限',
     */
    private Integer questionFaceWay;
    private String questionFaceWayStr;

    public String getQuestionDifficultyStr() {
		return questionDifficultyStr;
	}

	public void setQuestionDifficultyStr(String questionDifficultyStr) {
		this.questionDifficultyStr = questionDifficultyStr;
	}

	public String getQuestionFaceWayStr() {
		return questionFaceWayStr;
	}

	public void setQuestionFaceWayStr(String questionFaceWayStr) {
		this.questionFaceWayStr = questionFaceWayStr;
	}

	public String getQuestionTopic() {
		return questionTopic;
	}

	public void setQuestionTopic(String questionTopic) {
		this.questionTopic = questionTopic;
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

	public String getQuestionType1() {
		return questionType1;
	}

	public void setQuestionType1(String questionType1) {
		this.questionType1 = questionType1;
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
