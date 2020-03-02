package com.buyManage.hx3;

public class Utils {
	// 用户名正则
	public static boolean checkUsername(String username) {
		if (username == null) {
			return false;
		}
		return username.matches("^[a-zA-Z]\\w&{2,19}&");
	}

	// 用户密码正则
	public static boolean checkPwd(String password) {
		if (password == null) {
			return false;
		}
		return password.matches("^(?!(?:\\d+|[a-zA-Z]+)$)[\\da-zA-Z]{6,}$");
	}
}
