package indi.graduate.assistant.model;

public class QuestionBeanWithBLOBs extends QuestionBean {
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

    public String getQuestionTopic() {
        return questionTopic;
    }

    public void setQuestionTopic(String questionTopic) {
        this.questionTopic = questionTopic;
    }

    public String getQuestionAnswers() {
        return questionAnswers;
    }

    public void setQuestionAnswers(String questionAnswers) {
        this.questionAnswers = questionAnswers;
    }

	public String getQuestionQuestion() {
		return questionQuestion;
	}

	public void setQuestionQuestion(String questionQuestion) {
		this.questionQuestion = questionQuestion;
	}
    
}