package indi.graduate.assistant.dto;

public class AccountDto {
	
	private String addUserId;
	private String addUserAccount;
	private String addUserPassword;
	private String addUserName;
	private String addUserPhone;
	private String addUserTechnology;
	private String addUserDepartment;
	private String addUserProject;
	private int addUserGrade;
	private String addUserHobby;
	private int addUserPower;
	private String userType;
	private String addUserGradeTemp;
	
	public String getaddUserId() {
		return addUserId;
	}
	public void setaddUserId(String addUserId) {
		this.addUserId = addUserId;
	}
	public String getaddUserAccount() {
		return addUserAccount;
	}
	public void setaddUserAccount(String addUserAccount) {
		this.addUserAccount = addUserAccount;
	}
	public String getaddUserPassword() {
		return addUserPassword;
	}
	public void setaddUserPassword(String addUserPassword) {
		this.addUserPassword = addUserPassword;
	}
	public String getaddUserName() {
		return addUserName;
	}
	public void setaddUserName(String addUserName) {
		this.addUserName = addUserName;
	}
	public String getaddUserPhone() {
		return addUserPhone;
	}
	public void setaddUserPhone(String addUserPhone) {
		this.addUserPhone = addUserPhone;
	}
	public String getaddUserTechnology() {
		return addUserTechnology;
	}
	public void setaddUserTechnology(String addUserTechnology) {
		this.addUserTechnology = addUserTechnology;
	}
	public String getaddUserDepartment() {
		return addUserDepartment;
	}
	public void setaddUserDepartment(String addUserDepartment) {
		this.addUserDepartment = addUserDepartment;
	}
	public String getaddUserProject() {
		return addUserProject;
	}
	public void setaddUserProject(String addUserProject) {
		this.addUserProject = addUserProject;
	}
	public int getaddUserGrade() {
		return addUserGrade;
	}
	public void setaddUserGrade(int addUserGrade) {
		this.addUserGrade = addUserGrade;
	}
	public String getaddUserHobby() {
		return addUserHobby;
	}
	public void setaddUserHobby(String addUserHobby) {
		this.addUserHobby = addUserHobby;
	}
	public int getaddUserPower() {
		return addUserPower;
	}
	public void setaddUserPower(int addUserPower) {
		this.addUserPower = addUserPower;
	}
	//增加toString方法
	public String toString(){
		return addUserName+addUserPassword;
	}
	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}
	public String getAddUserGradeTemp() {
		return addUserGradeTemp;
	}
	public void setAddUserGradeTemp(String addUserGradeTemp) {
		this.addUserGradeTemp = addUserGradeTemp;
	}
	
}
