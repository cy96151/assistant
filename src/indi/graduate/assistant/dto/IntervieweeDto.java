package indi.graduate.assistant.dto;

import java.util.Date;

public class IntervieweeDto {
	/** 序号 **/
	private int intervieweeId;
	/** 面试状态 **/
	private Integer intervieweeStatus;
	private String intervieweeStatus1;
	/** 姓名 **/
	private String intervieweeName;
	/** 姓别 **/
	private String intervieweeSex;
	/** 手机号 **/
	private String intervieweePhone;
	/** 毕业学校 **/
	private String intervieweeSchool;
	/** 毕业时间 **/
	private Date intervieweeGraduationTime;
	/** 岗位 **/
	private String intervieweePost;
	/** 项目组 **/
	private String intervieweeProject;
	/** 面试途径 **/
	private Integer interviewWay;
	private String interviewWay1;
	/** 面试编号 **/
	private int interviewId;
	/** 面试官序号 **/
	private String userId;
	/** 面试官姓名 **/
	private String userName;
	/** 面试次数 **/
	private Integer intervieweeNum;
    /** 最后一次面试时间    */
   private Date intervieweeLastTime;
	/** 专业特长 **/
	private String intervieweePecialties;
	/** 技能等级 **/
	private int interviewSkillLevel;
	/** 面试类型 **/
	private int interviewType;
	/**面试时间**/
	private Date interviewTime;
	
	public Date getInterviewTime() {
		return interviewTime;
	}

	public void setInterviewTime(Date interviewTime) {
		this.interviewTime = interviewTime;
	}

	public int getInterviewType() {
		return interviewType;
	}

	public void setInterviewType(int interviewType) {
		this.interviewType = interviewType;
	}

	public int getIntervieweeId() {
		return intervieweeId;
	}

	public void setIntervieweeId(int intervieweeId) {
		this.intervieweeId = intervieweeId;
	}

	public Integer getIntervieweeStatus() {
		return intervieweeStatus;
	}

	public void setIntervieweeStatus(Integer intervieweeStatus) {
		this.intervieweeStatus = intervieweeStatus;
	}

	public String getIntervieweeStatus1() {
		return intervieweeStatus1;
	}

	public void setIntervieweeStatus1(String intervieweeStatus1) {
		this.intervieweeStatus1 = intervieweeStatus1;
	}

	public String getIntervieweeName() {
		return intervieweeName;
	}

	public void setIntervieweeName(String intervieweeName) {
		this.intervieweeName = intervieweeName;
	}

	public String getIntervieweeSex() {
		return intervieweeSex;
	}

	public void setIntervieweeSex(String intervieweeSex) {
		this.intervieweeSex = intervieweeSex;
	}

	public String getIntervieweePhone() {
		return intervieweePhone;
	}

	public void setIntervieweePhone(String intervieweePhone) {
		this.intervieweePhone = intervieweePhone;
	}

	public String getIntervieweeSchool() {
		return intervieweeSchool;
	}

	public void setIntervieweeSchool(String intervieweeSchool) {
		this.intervieweeSchool = intervieweeSchool;
	}

	public Date getIntervieweeGraduationTime() {
		return intervieweeGraduationTime;
	}

	public void setIntervieweeGraduationTime(Date intervieweeGraduationTime) {
		this.intervieweeGraduationTime = intervieweeGraduationTime;
	}

	public String getIntervieweePost() {
		return intervieweePost;
	}

	public void setIntervieweePost(String intervieweePost) {
		this.intervieweePost = intervieweePost;
	}

	public String getIntervieweeProject() {
		return intervieweeProject;
	}

	public void setIntervieweeProject(String intervieweeProject) {
		this.intervieweeProject = intervieweeProject;
	}

	public Integer getInterviewWay() {
		return interviewWay;
	}

	public void setInterviewWay(Integer interviewWay) {
		this.interviewWay = interviewWay;
	}

	public String getInterviewWay1() {
		return interviewWay1;
	}

	public void setInterviewWay1(String interviewWay1) {
		this.interviewWay1 = interviewWay1;
	}

	public int getInterviewId() {
		return interviewId;
	}

	public void setInterviewId(int interviewId) {
		this.interviewId = interviewId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Integer getIntervieweeNum() {
		return intervieweeNum;
	}

	public void setIntervieweeNum(Integer intervieweeNum) {
		this.intervieweeNum = intervieweeNum;
	}

	public String getIntervieweePecialties() {
		return intervieweePecialties;
	}

	public void setIntervieweePecialties(String intervieweePecialties) {
		this.intervieweePecialties = intervieweePecialties;
	}

	public int getInterviewSkillLevel() {
		return interviewSkillLevel;
	}

	public void setInterviewSkillLevel(int interviewSkillLevel) {
		this.interviewSkillLevel = interviewSkillLevel;
	}

	public Date getIntervieweeLastTime() {
		return intervieweeLastTime;
	}

	public void setIntervieweeLastTime(Date intervieweeLastTime) {
		this.intervieweeLastTime = intervieweeLastTime;
	}
	
}
