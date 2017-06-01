package indi.graduate.assistant.action;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import indi.graduate.assistant.dto.LoginDto;
import indi.graduate.assistant.service.LoginService;
import indi.graduate.assistant.util.RegexCheck;

/*
 * 登录控制器，实现登录控制，转发
 */

@Controller
public class LoginAction {
	@Autowired
	private LoginService loginService;

	@RequestMapping(value = "login.htm")
	public String login(@CookieValue(value = "INTERVIEW", required = false) String userID, HttpSession httpSession, HttpServletRequest request) throws Exception {
		Object sessionUserId=httpSession.getAttribute("userId");
		if(sessionUserId!=null && StringUtils.isNotBlank(sessionUserId.toString())){
			return "redirect:/index.htm";
		}
		if (StringUtils.isNotBlank(userID)) {
			if (loginService.validateLogin(userID, httpSession, request)) {
				return "redirect:/index.htm";
			}
		}
		return "login";
	}
	
	@RequestMapping(value = "exit.htm")
	public String exit(HttpSession httpSession, HttpServletResponse response) throws Exception {
		if (loginService.login(httpSession, response)) {
			return "redirect:/login.htm";
		}
		return "index";
	}

	@RequestMapping(value = "login.json")
	@ResponseBody
	public Map<String, Object> login2(HttpServletRequest request, HttpServletResponse response, LoginDto user, HttpSession httpSession) throws Exception {
		Map<String, Object> map = new HashMap<>();
		if (!Validate(user, map)) {
			return map;
		}
		String path = request.getContextPath();
		String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
		map = loginService.getLogin(user, httpSession, response, request);
		map.put("basePath", basePath);
		return map;
	}

	@RequestMapping(value = "index.htm")
	public void index(ModelMap map,HttpSession session) throws Exception {
		String userName = (String) session.getAttribute("userName");
		int userLevel = (int) session.getAttribute("userLevel");
		if(userLevel == 0){
			map.put("userPower", "系统管理员");
		}else{
			map.put("userPower", "面试官");
		}
		map.put("userName", userName);
	}
	
	@RequestMapping(value = "welcome.htm")
	public void welcome(ModelMap map,HttpSession session,HttpServletRequest request) throws Exception {
		map.putAll(loginService.getCount());
		map.put("Scheme", request.getScheme() + "://" + request.getServerName() + "/");
		map.put("LocalAddr", request.getLocalAddr());
		map.put("ServerName", request.getServerName());
		map.put("ServerPort", request.getServerPort());
		map.put("ServerDate", new Date());
	}

	/* 账户信息验证 */
	public boolean Validate(LoginDto user, Map<String, Object> map) {
		if (user.getUserName() == null || user.getUserName().equals("")) {
			map.put("code", "4");
			map.put("codeDes", "账户不能为空! ");
			return false;
		} else if (user.getPassWord() == null || user.getPassWord().equals("")) {
			map.put("code", "5");
			map.put("codeDes", "密码不能为空！");
			return false;
		} else if (!RegexCheck.regexCheck(user.getUserName(), RegexCheck.userCheck)) {
			map.put("code", "6");
			map.put("codeDes", "账号格式错误！");
			return false;
		}
		return true;
	}
}
