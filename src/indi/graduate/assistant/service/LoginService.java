package indi.graduate.assistant.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import indi.graduate.assistant.dto.LoginDto;

public interface LoginService {
	// 登陆验证
	public Map<String, Object> getLogin(LoginDto user, HttpSession httpSession, HttpServletResponse response, HttpServletRequest request);

	// 自动登录
	public boolean validateLogin(String userID, HttpSession httpSession, HttpServletRequest request);

	// 退出
	public boolean login(HttpSession httpSession, HttpServletResponse response);
	
	public Map<String, Object> getCount();
}
