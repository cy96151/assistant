package indi.graduate.assistant.model;

import java.util.Date;

public class IntervieweeBean {
	/**
	 * 序号
	 */
	private Integer intervieweeId;

	/**
	 * 姓名
	 */
	private String intervieweeName;

	/**
	 * 性别
	 */
	private Integer intervieweeSex;
	private String intervieweeSexStr;
	/**
	 * 联系方式
	 */
	private String intervieweePhone;

	/**
	 * 面试状态
	 */
	private Integer intervieweeStatus;
	/**
	 * 面试次数
	 */
	private Integer intervieweeNum;

	/**
	 * 最后一次面试时间
	 */
	private Date intervieweeLastTime;
	/**
	 * 毕业学校
	 */
	private String intervieweeSchool;

	/**
	 * 毕业时间
	 */
	private Date intervieweeGraduationTime;
	private String graduationTime;
	/**
	 * 应聘岗位
	 */
	private String intervieweePost;

	/**
	 * 应聘项目
	 */
	private String intervieweeProject;

	/**
	 * 专业特长
	 */
	private String intervieweePecialties;

	public Integer getIntervieweeId() {
		return intervieweeId;
	}

	public void setIntervieweeId(Integer intervieweeId) {
		this.intervieweeId = intervieweeId;
	}

	public String getIntervieweeName() {
		return intervieweeName;
	}

	public void setIntervieweeName(String intervieweeName) {
		this.intervieweeName = intervieweeName;
	}

	public Integer getIntervieweeSex() {
		return intervieweeSex;
	}

	public void setIntervieweeSex(Integer intervieweeSex) {
		this.intervieweeSex = intervieweeSex;
	}

	public String getIntervieweePhone() {
		return intervieweePhone;
	}

	public void setIntervieweePhone(String intervieweePhone) {
		this.intervieweePhone = intervieweePhone;
	}

	public Integer getIntervieweeStatus() {
		return intervieweeStatus;
	}

	public void setIntervieweeStatus(Integer intervieweeStatus) {
		this.intervieweeStatus = intervieweeStatus;
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

	public String getIntervieweePecialties() {
		return intervieweePecialties;
	}

	public void setIntervieweePecialties(String intervieweePecialties) {
		this.intervieweePecialties = intervieweePecialties;
	}

	public Integer getIntervieweeNum() {
		return intervieweeNum;
	}

	public void setIntervieweeNum(Integer intervieweeNum) {
		this.intervieweeNum = intervieweeNum;
	}

	public Date getIntervieweeLastTime() {
		return intervieweeLastTime;
	}

	public void setIntervieweeLastTime(Date intervieweeLastTime) {
		this.intervieweeLastTime = intervieweeLastTime;
	}

	public String getGraduationTime() {
		return graduationTime;
	}

	public void setGraduationTime(String graduationTime) {
		this.graduationTime = graduationTime;
	}

	public String getIntervieweeSexStr() {
		return intervieweeSexStr;
	}

	public void setIntervieweeSexStr(String intervieweeSexStr) {
		this.intervieweeSexStr = intervieweeSexStr;
	}

}