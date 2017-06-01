package indi.graduate.assistant.model;

public class UserBean {
	
	private String userId;
	private String userAccount;
	private String userPassword;
	private String userName;
	private String userPhone;
	private String userTechnology;
	private String userDepartment;
	private String userProject;
	private int userGrade;
	private String userHobby;
	private int userPower;
	private String userLastTime;
	private String userLastIp;
	private int userLoginSum;
	//userType,字典未实现时的使用字段
	private String userType;
	private String userGradeTemp;
	
	
	public String getUserLastTime() {
		return userLastTime;
	}
	public void setUserLastTime(String userLastTime) {
		this.userLastTime = userLastTime;
	}
	public String getUserLastIp() {
		return userLastIp;
	}
	public void setUserLastIp(String userLastIp) {
		this.userLastIp = userLastIp;
	}
	public int getUserLoginSum() {
		return userLoginSum;
	}
	public void setUserLoginSum(int userLoginSum) {
		this.userLoginSum = userLoginSum;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserAccount() {
		return userAccount;
	}
	public void setUserAccount(String userAccount) {
		this.userAccount = userAccount;
	}
	public String getUserPassword() {
		return userPassword;
	}
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserPhone() {
		return userPhone;
	}
	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}
	public String getUserTechnology() {
		return userTechnology;
	}
	public void setUserTechnology(String userTechnology) {
		this.userTechnology = userTechnology;
	}
	public String getUserDepartment() {
		return userDepartment;
	}
	public void setUserDepartment(String userDepartment) {
		this.userDepartment = userDepartment;
	}
	public String getUserProject() {
		return userProject;
	}
	public void setUserProject(String userProject) {
		this.userProject = userProject;
	}
	public int getUserGrade() {
		return userGrade;
	}
	public void setUserGrade(int userGrade) {
		this.userGrade = userGrade;
	}
	public String getUserHobby() {
		return userHobby;
	}
	public void setUserHobby(String userHobby) {
		this.userHobby = userHobby;
	}
	public int getUserPower() {
		return userPower;
	}
	public void setUserPower(int userPower) {
		this.userPower = userPower;
	}
	//增加toString方法
	public String toString(){
		return userName+userPassword;
	}
	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}
	
	public String getUserGradeTemp() {
		return userGradeTemp;
	}
	public void setUserGradeTemp(String userGradeTemp) {
		this.userGradeTemp = userGradeTemp;
	}
	
}
