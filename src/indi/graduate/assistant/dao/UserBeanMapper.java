package indi.graduate.assistant.dao;

import java.util.List;
import java.util.Map;

import indi.graduate.assistant.model.UserBean;

public interface UserBeanMapper {
   

    int insert(UserBean record);
    int updateLoginMsg(Map<String, Object> loginMsg);
    List<Integer> selectUserCount();
    UserBean selectByPrimaryKey(String userId);
    /*
     * 根据用户名选择user
     */
    List<UserBean> selectByUserAccount(String userAccount);
    int updateByPrimaryKey(UserBean record);
  /*
   * 系统管理界面接口   
   */
	List<UserBean> accountsShow(Map<String, Object> mm);
	int countAccounts();
	int countAccountsByName(Map<String, Object> accountNum);
	int countAccountsByPhone(Map<String, Object> accountNum);
	int insertAccounts(UserBean account);
	void delUsers(String userId);
	List<UserBean> accountsByName(Map<String, Object> accountNum);
    List<UserBean> accountsByPhone(Map<String, Object> mm);
    /**添加面试官列表**/
    List<UserBean> addAccountsShow();
	int addCountAccounts();
	/*重置密码*/
	void resetPass(String userId);
}