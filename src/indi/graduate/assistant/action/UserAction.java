package indi.graduate.assistant.action;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import indi.graduate.assistant.dto.AccountDto;
import indi.graduate.assistant.dto.PasswordDto;
import indi.graduate.assistant.service.UserService;
import indi.graduate.assistant.util.RegexCheck;

@Controller
@RequestMapping("/interview/user")
public class UserAction {
	@Autowired
	private UserService userService;

	/*
	 * 显示系统管理人员界面
	 */
	@RequestMapping("/userManagement.htm")
	public void accountsManger(ModelMap modelMap, int pageNo, int pageSize, String searchText) {
		if (pageNo == 0) {
			pageNo = 1;
		}
		if (pageSize == 0) {
			pageSize = 100;
		}
		Map<String, Object> resultMap = userService.query(pageNo, pageSize, searchText);
		modelMap.putAll(resultMap);
		modelMap.put("searchText", searchText);
	}

	@RequestMapping(value = { "userManagement-edit.htm" })
	public void questionManagement_edit(ModelMap map, String userIdEdit) {
		map.put("userIdEdit", userIdEdit);
	}

	/*
	 * 进入新增界面
	 */
	@RequestMapping("/addAccounts.json")
	@ResponseBody
	public Map<String, String> addAccounts(HttpServletRequest request, AccountDto accountDto) {
		Map<String, String> map = new HashMap<>();
		if (!validate(accountDto, map)) {
			return map;
		}
		try {
			map = userService.addAccounts(accountDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}

	/*
	 * 删除的方法
	 * 
	 */
	@RequestMapping("/delAccounts.json")
	@ResponseBody
	public Map<String, String> delAccounts(HttpServletRequest request, String userId, HttpSession session) {

		Map<String, String> map = new HashMap<>();
		if (session.getAttribute("userId").equals(userId)) {
			map.put("code", "2");
			map.put("codeDes", "无法删除自己的账号！");
			return map;
		}
		map = userService.delAccounts(userId);
		return map;
	}

	/** 重置密码 **/
	@RequestMapping("/resetPass.json")
	@ResponseBody
	public Map<String, Object> resetPass(HttpServletRequest request, String userId) {
		Map<String, Object> map = new HashMap<>();
		map = userService.resetPass(userId);
		return map;
	}

	/*
	 * 修改的方法
	 * 
	 */
	@RequestMapping("/editAccounts.json")
	@ResponseBody
	public Map<String, String> editAccounts(HttpServletRequest request, AccountDto accountDto) {
		Map<String, String> map = new HashMap<>();
		if (!validate(accountDto, map)) {
			return map;
		}
		try {
			map = userService.editAccounts(accountDto);
		} catch (Exception e) {
			e.printStackTrace();
			map.put("code", "2");
			map.put("codeDes", "数据库错误！");
			return map;
		}

		return map;
	}

	/** 修改密码 **/
	@RequestMapping("/editPassword.json")
	@ResponseBody
	public Map<String, String> editPassword(HttpSession session, PasswordDto passwordDto) {
		return userService.editPassword(session, passwordDto);

	}

	/* 数据校验辅助方法 */
	public boolean validate(AccountDto accountDto, Map<String, String> map) {
		// 数据校验
		if (accountDto.getaddUserName() == null || accountDto.getaddUserName().equals("")) {
			map.put("code", "4");
			map.put("codeDes", "用户姓名不能为空！");
			return false;
		} else if (accountDto.getaddUserPhone() == null || accountDto.getaddUserPhone().equals("")) {
			map.put("code", "5");
			map.put("codeDes", "联系方式不能为空！");
			return false;
		}

		if (!RegexCheck.regexCheck(accountDto.getaddUserAccount(), RegexCheck.userCheck)) {
			map.put("code", "6");
			map.put("codeDes", "账号格式错误！");
			return false;
		} else if (!RegexCheck.regexCheck(accountDto.getaddUserName(), RegexCheck.userNameCheck)) {
			map.put("code", "8");
			map.put("codeDes", "用户姓名只能为中文！");
			return false;
		} else if (!RegexCheck.regexCheck(accountDto.getaddUserPhone(), RegexCheck.phoneCheck)) {
			map.put("code", "9");
			map.put("codeDes", "手机格式错误！");
			return false;
		}
		return true;
	}

	/** 获取面试官列表 **/
	@RequestMapping("/addaccountsMangerList.htm")
	public void addaccountsManger(ModelMap modelMap, String intervieweeId, String interviewId) {
		Map<String, Object> resultMap = userService.addaccountsManger();
		modelMap.putAll(resultMap);
		modelMap.put("intervieweeId", intervieweeId);
		modelMap.put("interviewId", interviewId);
	}
}
