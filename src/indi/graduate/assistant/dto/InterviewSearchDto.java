package indi.graduate.assistant.dto;

/** 面试过程搜索面试人列表 **/
public class InterviewSearchDto {
	private int PageNo;
	private int PageSize;
	// 查找
	private String searchName;
	// 面试状态
	private int intervieweeStatus;

	public int getPageNo() {
		return PageNo;
	}

	public void setPageNo(int pageNo) {
		PageNo = pageNo;
	}

	public int getPageSize() {
		return PageSize;
	}

	public void setPageSize(int pageSize) {
		PageSize = pageSize;
	}

	public String getSearchName() {
		return searchName;
	}

	public void setSearchName(String searchName) {
		this.searchName = searchName;
	}

	public int getIntervieweeStatus() {
		return intervieweeStatus;
	}

	public void setIntervieweeStatus(int intervieweeStatus) {
		this.intervieweeStatus = intervieweeStatus;
	}

}
