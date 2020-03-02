package com.buyManage.hx1;

import java.io.Serializable;
/**
 * 用户类
 * @author bear
 *
 */
public class User implements Serializable {
	/**
	 * 用户的属性
	 */
	private static final long serialVersionUID = 1L;
	// 用户名
	private String username;
	// 用户密码
	private String password;
	// 用户vip等级
	private String vip;
	// 余额
	private double balance;
	// 退出时间
	private String timestamp;
	// 是否查封
	private boolean isban;

	// 无参构造
	public User() {
		super();
	}

	// 有参构造

	public User(String username, String password, String vip, double balance, String timestamp, boolean isban) {
		super();
		this.username = username;
		this.password = password;
		this.vip = vip;
		this.balance = balance;
		this.timestamp = timestamp;
		this.isban = isban;
	}

	public User(String username, String password, boolean isban) {
		super();
		this.username = username;
		this.password = password;
		this.isban = isban;
	}

	// set get 方法
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getVip() {
		return vip;
	}

	public void setVip(String vip) {
		this.vip = vip;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public boolean isIsban() {
		return isban;
	}

	public void setIsban(boolean isban) {
		this.isban = isban;
	}

	// toString 方法
	@Override
	public String toString() {
		return "User [username=" + username + ", password=" + password + ", vip=" + vip + ", balance=" + balance
				+ ", timestamp=" + timestamp + ", isban=" + isban + "]";
	}

}
