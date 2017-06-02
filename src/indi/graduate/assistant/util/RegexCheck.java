package indi.graduate.assistant.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexCheck {
	// 用户名验证
	public final static String userCheck = "^[a-zA-Z]{1}([a-zA-Z0-9]|[._]){4,19}$";
	// 用户密码验证
	public final static String passCheck = "^(\\w){6,20}$";
	// 用户姓名验证
	public final static String userNameCheck = "^[\u4e00-\u9fa5]*$";
	// 手机号码验证
	public final static String phoneCheck = "^1[3|4|5|7|8]\\d{9}$";
	// 日期验证
	public final static String dateCheck = "^((\\d{2}(([02468][048])|([13579][26]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])))))|(\\d{2}(([02468][1235679])|([13579][01345789]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|(1[0-9])|(2[0-8]))))))";
	// 毕业院校进行验证（只输入中文）
	public final static String schoolCheck = "^[\u4E00-\u9FA5]+$";

	/**
	 * 验证账号
	 * 
	 * @param username
	 * @return
	 */
	public static boolean regexCheck(String text, String check) {
		boolean flag = false;
		try {
			Pattern regex = Pattern.compile(check);
			Matcher matcher = regex.matcher(text);
			flag = matcher.matches();
		} catch (Exception e) {
			flag = false;
		}
		return flag;
	}
}
