package indi.graduate.assistant.dao;

import java.util.List;
import java.util.Map;

import indi.graduate.assistant.model.InterviewCategoryBean;

public interface InterviewCategoryBeanMapper {

	/**添加**/
	int insert(InterviewCategoryBean interviewCategoryBean);
	/**查询列表**/
	List<InterviewCategoryBean> selectIntervieweeList(Map<String, Object> mm);
	int countAccounts(Map<String, Object> mm);
	
	/**修改问题类型**/
	int update(InterviewCategoryBean interviewCategoryBean);
	
	/**删除问题类型**/
	int deleteByPrimaryKey(Integer id);
	
	/**查找面试类型**/
	List<InterviewCategoryBean> select();
	
	InterviewCategoryBean selectById(Integer id);
	
	int selectNum(Map<String, Object> mm);
}
