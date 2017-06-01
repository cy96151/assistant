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

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column interview_detail.id_id
     *
     * @return the value of interview_detail.id_id
     *
     * @mbggenerated Mon Aug 29 21:58:48 CST 2016
     */
    public Integer getIdId() {
        return idId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column interview_detail.id_id
     *
     * @param idId the value for interview_detail.id_id
     *
     * @mbggenerated Mon Aug 29 21:58:48 CST 2016
     */
    public void setIdId(Integer idId) {
        this.idId = idId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column interview_detail.interview_id
     *
     * @return the value of interview_detail.interview_id
     *
     * @mbggenerated Mon Aug 29 21:58:48 CST 2016
     */
    public Integer getInterviewId() {
        return interviewId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column interview_detail.interview_id
     *
     * @param interviewId the value for interview_detail.interview_id
     *
     * @mbggenerated Mon Aug 29 21:58:48 CST 2016
     */
    public void setInterviewId(Integer interviewId) {
        this.interviewId = interviewId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column interview_detail.question_id
     *
     * @return the value of interview_detail.question_id
     *
     * @mbggenerated Mon Aug 29 21:58:48 CST 2016
     */
    public Integer getQuestionId() {
        return questionId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column interview_detail.question_id
     *
     * @param questionId the value for interview_detail.question_id
     *
     * @mbggenerated Mon Aug 29 21:58:48 CST 2016
     */
    public void setQuestionId(Integer questionId) {
        this.questionId = questionId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column interview_detail.id_understanding
     *
     * @return the value of interview_detail.id_understanding
     *
     * @mbggenerated Mon Aug 29 21:58:48 CST 2016
     */
    public Integer getIdUnderstanding() {
        return idUnderstanding;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column interview_detail.id_understanding
     *
     * @param idUnderstanding the value for interview_detail.id_understanding
     *
     * @mbggenerated Mon Aug 29 21:58:48 CST 2016
     */
    public void setIdUnderstanding(Integer idUnderstanding) {
        this.idUnderstanding = idUnderstanding;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column interview_detail.id_remark
     *
     * @return the value of interview_detail.id_remark
     *
     * @mbggenerated Mon Aug 29 21:58:48 CST 2016
     */
    public String getIdRemark() {
        return idRemark;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column interview_detail.id_remark
     *
     * @param idRemark the value for interview_detail.id_remark
     *
     * @mbggenerated Mon Aug 29 21:58:48 CST 2016
     */
    public void setIdRemark(String idRemark) {
        this.idRemark = idRemark;
    }
}