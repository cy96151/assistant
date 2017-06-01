package indi.graduate.assistant.dto;

import java.util.Date;

import indi.graduate.assistant.util.DateUtil;

public class IsStartUpInterviewDto {
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

    /**
     *面试时间
     */
    private Date interviewTime;

    /**
     *面试地点
     */
    private String interviewLocation;

    /**
     * 面试途径
     */
    private Integer interviewWay;
    private String interviewWay1;

    /**
     *面试类型
     */
    private Integer interviewType;
    private String interviewType1;

    /**
     *技能等级
     */
    private Integer interviewSkillLevel;
    private String interviewSkillLevel1;

    /**
     * 成绩
     */
    private Integer interviewScore;

    /**
     * 总评价
     */
    private String interviewEvaluate;

    /**
     * 姓名
     */
    private String intervieweeName;

    /**
     *性别
     */
    private Integer intervieweeSex;
    private String intervieweeSex1;

    /**
     * 联系方式
     */
    private String intervieweePhone;

    /**
     * 面试状态
     */
    private Integer intervieweeStatus;
    private String intervieweeStatus1;
    /**
     * 面试次数
     */
    private Integer IntervieweeNum;
    /**
     *毕业学校
     */
    private String intervieweeSchool;

    /**
     *毕业时间
     */
    private Date intervieweeGraduationTime;

    /**
     *应聘岗位
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
    
    /**
     * 面试官姓名
     */
    private String userName;
    
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

	public String getIntervieweeSchool() {
		return intervieweeSchool;
	}

	public void setIntervieweeSchool(String intervieweeSchool) {
		this.intervieweeSchool = intervieweeSchool;
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

	public Date getIntervieweeGraduationTime() {
		return intervieweeGraduationTime;
	}

	public void setIntervieweeGraduationTime(Date intervieweeGraduationTime) {
		this.intervieweeGraduationTime = intervieweeGraduationTime;
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

	public Integer getIntervieweeStatus() {
		return intervieweeStatus;
	}

	public void setIntervieweeStatus(Integer intervieweeStatus) {
		this.intervieweeStatus = intervieweeStatus;
	}

	public String getIntervieweePecialties() {
		return intervieweePecialties;
	}

	public void setIntervieweePecialties(String intervieweePecialties) {
		this.intervieweePecialties = intervieweePecialties;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Integer getIntervieweeNum() {
		return IntervieweeNum;
	}

	public void setIntervieweeNum(Integer intervieweeNum) {
		IntervieweeNum = intervieweeNum;
	}
	public String parsedIntervieweeGraduationTime(){
		return DateUtil.stringDate(intervieweeGraduationTime);
	}

	public String getInterviewWay1() {
		return interviewWay1;
	}

	public void setInterviewWay1(String interviewWay1) {
		this.interviewWay1 = interviewWay1;
	}

	public String getInterviewType1() {
		return interviewType1;
	}

	public void setInterviewType1(String interviewType1) {
		this.interviewType1 = interviewType1;
	}

	public String getInterviewSkillLevel1() {
		return interviewSkillLevel1;
	}

	public void setInterviewSkillLevel1(String interviewSkillLevel1) {
		this.interviewSkillLevel1 = interviewSkillLevel1;
	}

	public String getIntervieweeSex1() {
		return intervieweeSex1;
	}

	public void setIntervieweeSex1(String intervieweeSex1) {
		this.intervieweeSex1 = intervieweeSex1;
	}

	public String getIntervieweeStatus1() {
		return intervieweeStatus1;
	}

	public void setIntervieweeStatus1(String intervieweeStatus1) {
		this.intervieweeStatus1 = intervieweeStatus1;
	}
	
	
}
