package indi.graduate.assistant.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import indi.graduate.assistant.dao.InterviewBeanMapper;
import indi.graduate.assistant.dao.IntervieweeBeanMapper;
import indi.graduate.assistant.dao.QuestionBeanMapper;
import indi.graduate.assistant.dao.UserBeanMapper;
import indi.graduate.assistant.dto.LoginDto;
import indi.graduate.assistant.model.UserBean;
import indi.graduate.assistant.service.LoginService;

@Service("LoginService")
public class LoginServiceImpl implements LoginService {

	@Autowired
	private UserBeanMapper userBeanMapper;
	@Autowired
	private QuestionBeanMapper questionBeanMapper;
	@Autowired
	private InterviewBeanMapper interviewBeanMapper;
	@Autowired
	private IntervieweeBeanMapper intervieweeBeanMapper;

	// 登陆验证
	public Map<String, Object> getLogin(LoginDto user, HttpSession httpSession, HttpServletResponse response, HttpServletRequest request) {
		Map<String, Object> map = new HashMap<>();
		List<UserBean> userBeanList = new ArrayList<>();
		try {
			userBeanList = userBeanMapper.selectByUserAccount(user.getUserName());
		} catch (Exception e) {
			e.printStackTrace();
			// 0代表数据库查询异常
			map.put("code", "0");
			map.put("codeDes", "数据库错误！");
			return map;
		}
		// 2代表没有该账号
		if (null == userBeanList || userBeanList.size() == 0) {
			map.put("code", "2");
			map.put("codeDes", "用户不存在！");
			return map;
		}
		for (UserBean userBean : userBeanList) {
			if ((user.getPassWord()).equals(userBean.getUserPassword())) {
				setSession(httpSession, userBean, request);
				if (user.isOnline()) {
					Cookie cookie = new Cookie("INTERVIEW", userBean.getUserId());
					cookie.setMaxAge(60 * 60 * 24 * 7);
					response.addCookie(cookie);
				}
				map.put("userLevel", userBean.getUserPower());
				map.put("code", "1");
				map.put("codeDes", "登陆成功！");
				return map;
			}
		}
		// 3代表密码错误
		map.put("code", "3");
		map.put("codeDes", "密码错误！");
		return map;
	}

	// 退出
	@SuppressWarnings("deprecation")
	public boolean login(HttpSession httpSession, HttpServletResponse response) {
		boolean flag = false;
		try {
			httpSession.removeValue("userId");
			httpSession.removeValue("userName");
			httpSession.removeValue("userLevel");
			Cookie cookie = new Cookie("INTERVIEW", null);
			cookie.setMaxAge(0);
			response.addCookie(cookie);
			flag = true;
		} catch (Exception e) {
			return flag;
		}
		return flag;
	}

	@Override
	public boolean validateLogin(String userID, HttpSession httpSession, HttpServletRequest request) {
		UserBean userBean = userBeanMapper.selectByPrimaryKey(userID);
		if (userBean != null) {
			setSession(httpSession, userBean, request);
			return true;
		}
		return false;
	}

	private void setSession(HttpSession httpSession, UserBean userBean, HttpServletRequest request) {
		httpSession.setMaxInactiveInterval(30 * 60);
		httpSession.setAttribute("lastIp", userBean.getUserLastIp());
		httpSession.setAttribute("lastTime", userBean.getUserLastTime());
		httpSession.setAttribute("loginSum", userBean.getUserLoginSum() + 1);
		httpSession.setAttribute("userId", userBean.getUserId());
		httpSession.setAttribute("userName", userBean.getUserName());
		httpSession.setAttribute("userLevel", userBean.getUserPower());
		String loginIp = getIP(request);
		Map<String, Object> loginMsg = new HashMap<>();
		loginMsg.put("loginIp", loginIp);
		loginMsg.put("userId", userBean.getUserId());
		userBeanMapper.updateLoginMsg(loginMsg);
	}

	private String getIP(HttpServletRequest request) {
		String ip = request.getHeader("X-Forwarded-For");
		if (StringUtils.isNotEmpty(ip) && !"unKnown".equalsIgnoreCase(ip)) {
			// 多次反向代理后会有多个ip值，第一个ip才是真实ip
			int index = ip.indexOf(",");
			if (index != -1) {
				return ip.substring(0, index);
			} else {
				return ip;
			}
		}
		ip = request.getHeader("X-Real-IP");
		if (StringUtils.isNotEmpty(ip) && !"unKnown".equalsIgnoreCase(ip)) {
			return ip;
		}
		return request.getRemoteAddr();
	}

	@Override
	public Map<String, Object> getCount() {
		Map<String, Object> result = new HashMap<>();
		result.put("userCount", userBeanMapper.selectUserCount());
		result.put("questionCount", questionBeanMapper.selectQuestionCount());
		result.put("interviewCount", interviewBeanMapper.selectInterviewCount());
		result.put("intervieweeCount", intervieweeBeanMapper.selectIntervieweeCount());
		return result;
	}
}
