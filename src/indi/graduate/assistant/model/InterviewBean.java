package indi.graduate.assistant.model;

import java.util.Date;

public class InterviewBean {
    /**
     * 序号
     */
    private Integer interviewId;

    /**
     *面试人序号
     */
    private Integer intervieweeId;

    /**
     * 面试官序号
     */
    private String userId;
    private String user;
    /**
     *面试时间
     */
    private Date interviewTime;
    private String interviewTimeStr;
    /**
     *面试地点
     */
    private String interviewLocation;

    /**
     * 面试途径
     */
    private Integer interviewWay;

    /**
     *面试类型
     */
    private Integer interviewType;

    /**
     *技能等级
     */
    private Integer interviewSkillLevel;

    /**
     * 成绩
     */
    private Integer interviewScore;

    /**
     * 总评价
     */
    private String interviewEvaluate;
    
    /**
     * 面试是否完成
     */
    private Integer interviewEnd;

	public Integer getInterviewId() {
		return interviewId;
	}

	public void setInterviewId(Integer interviewId) {
		this.interviewId = interviewId;
	}

	public Integer getIntervieweeId() {
		return intervieweeId;
	}

	public void setIntervieweeId(Integer intervieweeId) {
		this.intervieweeId = intervieweeId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Date getInterviewTime() {
		return interviewTime;
	}

	public void setInterviewTime(Date interviewTime) {
		this.interviewTime = interviewTime;
	}

	public String getInterviewLocation() {
		return interviewLocation;
	}

	public void setInterviewLocation(String interviewLocation) {
		this.interviewLocation = interviewLocation;
	}

	public Integer getInterviewWay() {
		return interviewWay;
	}

	public void setInterviewWay(Integer interviewWay) {
		this.interviewWay = interviewWay;
	}

	public Integer getInterviewType() {
		return interviewType;
	}

	public void setInterviewType(Integer interviewType) {
		this.interviewType = interviewType;
	}

	public Integer getInterviewSkillLevel() {
		return interviewSkillLevel;
	}

	public void setInterviewSkillLevel(Integer interviewSkillLevel) {
		this.interviewSkillLevel = interviewSkillLevel;
	}

	public Integer getInterviewScore() {
		return interviewScore;
	}

	public void setInterviewScore(Integer interviewScore) {
		this.interviewScore = interviewScore;
	}

	public String getInterviewEvaluate() {
		return interviewEvaluate;
	}

	public void setInterviewEvaluate(String interviewEvaluate) {
		this.interviewEvaluate = interviewEvaluate;
	}

	public Integer getInterviewEnd() {
		return interviewEnd;
	}

	public void setInterviewEnd(Integer interviewEnd) {
		this.interviewEnd = interviewEnd;
	}

	public String getInterviewTimeStr() {
		return interviewTimeStr;
	}

	public void setInterviewTimeStr(String interviewTimeStr) {
		this.interviewTimeStr = interviewTimeStr;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

   
    
}