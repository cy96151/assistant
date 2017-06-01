package indi.graduate.assistant.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/*
 * 用户过滤器，实现用户过滤功能，未登录的都转发到登录页
 */

public class UserInterceptor implements HandlerInterceptor {

	@Override
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
			throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean preHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2) throws Exception {
		// TODO Auto-generated method stub
		HttpSession session = arg0.getSession(true);
		// 不拦截登录页
		String path = arg0.getRequestURL().toString();
		if (path.contains("login.htm")) {
			return true;
		}
		Object obj = session.getAttribute("userId");
		// 判断如果没有取到用户信息，就跳转到登陆页面，提示用户进行登陆
		if (null == obj || "".equals(obj.toString())) {
			String basePath = arg0.getScheme() + "://" + arg0.getServerName() + ":" + arg0.getServerPort() + arg0.getContextPath() + "/";
			arg1.sendRedirect(basePath+"login.htm");
			return false;
		} else {
			return true;
		}
	}

}
