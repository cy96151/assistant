package indi.graduate.assistant.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import indi.graduate.assistant.dao.UserBeanMapper;
import indi.graduate.assistant.dto.AccountDto;
import indi.graduate.assistant.dto.AccountSearchDto;
import indi.graduate.assistant.dto.PasswordDto;
import indi.graduate.assistant.model.UserBean;
import indi.graduate.assistant.service.UserService;
import indi.graduate.assistant.util.MD5;

@Service("UserService")
public class UserServiceImpl implements UserService {
	@Autowired
	private UserBeanMapper userBeanMapper;

	/* 新增账户方法 */
	public Map<String, String> addAccounts(AccountDto accountDto) {
		UserBean accountBean = new UserBean();
		Map<String, String> map = new HashMap<>();
		accountBean.setUserAccount(accountDto.getaddUserAccount());
		accountBean.setUserName(accountDto.getaddUserName());
		accountBean.setUserPhone(accountDto.getaddUserPhone());
		accountBean.setUserTechnology(accountDto.getaddUserTechnology());
		accountBean.setUserProject(accountDto.getaddUserProject());
		accountBean.setUserDepartment(accountDto.getaddUserDepartment());
		accountBean.setUserHobby(accountDto.getaddUserHobby());
		accountBean.setUserPassword(accountDto.getaddUserPassword());
		accountBean.setUserDepartment(accountDto.getaddUserDepartment());
		accountBean.setUserPower(accountDto.getaddUserPower());
		accountBean.setUserGrade(accountDto.getaddUserGrade());
		accountBean.setUserId(MD5.GetMD5Code(accountDto.getaddUserAccount()));
		List<UserBean> userBeansTemp = new ArrayList<>();
		try {
			userBeansTemp = userBeanMapper.selectByUserAccount(accountBean.getUserAccount());

		} catch (Exception e) {
			e.printStackTrace();
			map.put("code", "10");
			map.put("codeDes", "数据库错误！");
			return map;
		}
		// 3代表账户已存在
		if (userBeansTemp != null && userBeansTemp.size() != 0) {
			map.put("code", "11");
			map.put("codeDes", "用户名已存在！");
			return map;
		}

		try {
			userBeanMapper.insert(accountBean);
		} catch (Exception e) {
			e.printStackTrace();
			map.put("code", "10");
			map.put("codeDes", "数据库错误！");
			return map;
		}
		map.put("code", "1");
		return map;
	}

	/* 修改账户方法 */

	public Map<String, String> editAccounts(AccountDto accountDto) {
		UserBean accountBean = new UserBean();
		accountBean.setUserAccount(accountDto.getaddUserAccount());
		accountBean.setUserName(accountDto.getaddUserName());
		accountBean.setUserId(accountDto.getaddUserId());
		accountBean.setUserPhone(accountDto.getaddUserPhone());
		accountBean.setUserTechnology(accountDto.getaddUserTechnology());
		accountBean.setUserProject(accountDto.getaddUserProject());
		accountBean.setUserDepartment(accountDto.getaddUserDepartment());
		accountBean.setUserHobby(accountDto.getaddUserHobby());
		accountBean.setUserDepartment(accountDto.getaddUserDepartment());
		accountBean.setUserGrade(accountDto.getaddUserGrade());
		accountBean.setUserPower(accountDto.getaddUserPower());

		Map<String, String> map = new HashMap<>();
		try {
			userBeanMapper.updateByPrimaryKey(accountBean);
		} catch (Exception e) {
			e.printStackTrace();
			map.put("code", "10");
			map.put("codeDes", "数据库错误！");
			return map;
		}
		map.put("code", "1");
		return map;
	}

	/* 修改时读取数据 */
	public Map<String, Object> editQuery(String userId) {
		UserBean result = null;
		Map<String, Object> map = new HashMap<>();
		try {
			result = userBeanMapper.selectByPrimaryKey(userId);
		} catch (Exception e) {
			e.printStackTrace();
			map.put("code", "2");
			map.put("codeDes", "读取信息失败！");
			return map;
		}
		modifyShow(result);
		map.put("code", "1");
		map.put("userBean", result);
		return map;
	}

	// 自动读取方法
	public Map<String, Object> query(int pageNo, int pageSize, String searchText) {
		Map<String, Object> mm = new HashMap<>();
		mm.put("pageNo", (pageNo - 1) * 10);
		mm.put("pageSize", pageSize);
		mm.put("searchText", searchText);
		List<UserBean> result = null;
		Map<String, Object> resultMap = new HashMap<>();
		int rowCount = 0;
		try {
			result = userBeanMapper.accountsShow(mm);
			rowCount = userBeanMapper.countAccounts();
		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("code", "2");
			return resultMap;
		}
		for (int i = 0; i < result.size(); i++) {
			modifyShow(result.get(i));
		}
		if (result != null && result.size() > 0) {
			resultMap = new HashMap<String, Object>();
			resultMap.put("rows", result);
			resultMap.put("total", rowCount);
		}
		resultMap.put("code", 1);
		return resultMap;
	}

	/* 查找方法 */
	public Map<String, Object> queryByText(AccountSearchDto accountSearchDto) {
		Map<String, Object> mm = new HashMap<>();
		int pageNo = accountSearchDto.getPageNo();
		int pageSize = accountSearchDto.getPageSize();
		mm.put("pageNo", (pageNo - 1) * 10);
		mm.put("pageSize", pageSize);
		String searchText = accountSearchDto.getSearchText();
		mm.put("searchText", searchText);
		List<UserBean> result = null;
		int rowCount = 0;
		boolean flag = StringUtils.isNumeric(searchText);
		try {
			if (null == searchText || searchText.equals("")) {
				result = userBeanMapper.accountsShow(mm);
				rowCount = userBeanMapper.countAccounts();
			} else {
				if (flag) {

					result = userBeanMapper.accountsByPhone(mm);
					rowCount = userBeanMapper.countAccountsByPhone(mm);
				} else {
					result = userBeanMapper.accountsByName(mm);
					rowCount = userBeanMapper.countAccountsByName(mm);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		for (int i = 0; i < result.size(); i++) {
			modifyShow(result.get(i));
		}

		Map<String, Object> resultMap = new HashMap<>();
		if (result != null && result.size() > 0) {
			resultMap = new HashMap<String, Object>();
			resultMap.put("rows", result);
			resultMap.put("total", rowCount);
		}

		return resultMap;
	}

	// 删除账户方法
	public Map<String, String> delAccounts(String userId) {

		Map<String, String> map = new HashMap<String, String>();
		try {
			userBeanMapper.delUsers(userId);

		} catch (Exception e) {
			e.printStackTrace();
			map.put("code", "2");
			map.put("codeDes", "删除失败");
			return map;
		}
		map.put("code", "1");
		map.put("codeDes", "删除成功！");
		return map;
	}

	// 辅助方法，用于更改显示
	public void modifyShow(UserBean result) {
		if (result.getUserPower() == 1) {
			result.setUserType("面试官");
		} else {
			result.setUserType("管理员");
		}
		switch (result.getUserGrade()) {
		case 1:
			result.setUserGradeTemp("E1");
			break;
		case 2:
			result.setUserGradeTemp("E2");
			break;
		case 3:
			result.setUserGradeTemp("E3");
			break;
		case 4:
			result.setUserGradeTemp("E4");
			break;
		case 5:
			result.setUserGradeTemp("E5");
			break;
		default:
			break;
		}
	}

	// 修改密码
	public Map<String, String> editPassword(HttpSession session, PasswordDto passwordDto) {
		Map<String, String> map = new HashMap<>();
		UserBean user = null;
		try {
			user = userBeanMapper.selectByPrimaryKey((String) session.getAttribute("userId"));
		} catch (Exception e) {
			map.put("code", "2");
			map.put("codeDes", "读取信息失败！");
			return map;
		}
		if (user.getUserPassword().equals(passwordDto.getUserPassword())) {
			user.setUserPassword(passwordDto.getUserPassword1());
			try {
				userBeanMapper.updateByPrimaryKey(user);
			} catch (Exception e) {
				map.put("code", "2");
				map.put("codeDes", "密码插入失败！");
				return map;
			}
		} else {
			map.put("code", "2");
			map.put("codeDes", "原密码输入错误！");
			return map;
		}
		map.put("code", "1");
		map.put("codeDes", "密码修改成功！");
		return map;
	}

	public Map<String, Object> addaccountsManger() {
		List<UserBean> result = null;
		Map<String, Object> resultMap = new HashMap<>();
		int rowCount = 0;
		try {
			result = userBeanMapper.addAccountsShow();
			rowCount = userBeanMapper.addCountAccounts();
		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("code", "2");
			return resultMap;
		}
		for (int i = 0; i < result.size(); i++) {
			modifyShow(result.get(i));
		}
		if (result != null && result.size() > 0) {
			resultMap = new HashMap<String, Object>();
			resultMap.put("rows", result);
			resultMap.put("total", rowCount);
		}
		resultMap.put("code", 1);
		return resultMap;

	}

	/* 还原密码 */
	public Map<String, Object> resetPass(String userId) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			userBeanMapper.resetPass(userId);
		} catch (Exception e) {
			e.printStackTrace();
			map.put("code", "2");
			map.put("codeDes", "重置失败");
			return map;
		}
		map.put("code", "1");
		map.put("codeDes", "重置成功！");
		return map;
	}
}
