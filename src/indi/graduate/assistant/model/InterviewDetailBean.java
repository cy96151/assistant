package indi.graduate.assistant.model;

public class InterviewDetailBean {
    /**
     *序号
     */
    private Integer idId;

    /**
     *面试序号（外键）
     */
    private Integer interviewId;

    /**
     * 题号
     */
    private Integer questionId;

    /**
     * 了解程度
     */
    private Integer idUnderstanding;

    /**
     *备注
     */
    private String idRemark;

    public Integer getIdId() {
        return idId;
    }

    public void setIdId(Integer idId) {
        this.idId = idId;
    }

    public Integer getInterviewId() {
        return interviewId;
    }

    public void setInterviewId(Integer interviewId) {
        this.interviewId = interviewId;
    }

    public Integer getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Integer questionId) {
        this.questionId = questionId;
    }

    public Integer getIdUnderstanding() {
        return idUnderstanding;
    }

    public void setIdUnderstanding(Integer idUnderstanding) {
        this.idUnderstanding = idUnderstanding;
    }

    public String getIdRemark() {
        return idRemark;
    }

    public void setIdRemark(String idRemark) {
        this.idRemark = idRemark;
    }
}