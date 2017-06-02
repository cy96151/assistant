package indi.graduate.assistant.service;

import java.util.Map;

import javax.servlet.http.HttpSession;

import indi.graduate.assistant.dto.AccountDto;
import indi.graduate.assistant.dto.AccountSearchDto;
import indi.graduate.assistant.dto.PasswordDto;

public interface UserService {
	/* 新增账户方法 */
	public Map<String, String> addAccounts(AccountDto accountDto);

	/* 修改账户方法 */
	public Map<String, String> editAccounts(AccountDto accountDto);

	/* 读取单条数据 */
	public Map<String, Object> editQuery(String userId);

	// 自动载入grid数据方法
	public Map<String, Object> query(int pageNo, int pageSize, String searchText);

	/* 查找方法 */
	public Map<String, Object> queryByText(AccountSearchDto accountSearchDto);

	// 删除账户方法
	public Map<String, String> delAccounts(String userId);

	// 修改密码
	public Map<String, String> editPassword(HttpSession session, PasswordDto passwordDto);

	// 获取面试官
	public Map<String, Object> addaccountsManger();

	/* 还原密码 */
	public Map<String, Object> resetPass(String userId);
}
