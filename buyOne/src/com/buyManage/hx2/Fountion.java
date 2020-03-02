package com.buyManage.hx2;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import com.buyManage.hx1.Order;
import com.buyManage.hx1.Product;
import com.buyManage.hx1.User;
import com.buyManage.hx3.Save;
import com.buyManage.hx3.Utils;

public class Fountion {
	// 创建用户登录的次数
	public static Scanner scanner = new Scanner(System.in);
	// 记录用户登录次数
	public static int count = 3;

	/**
	 * 登录的方法
	 * 
	 */

	public static void login(User user, List<User> userList, Product product, List<Product> pdtList,
			List<Order> orderList) {

		// 提示用户登录格式用户名-密码
		System.out.println("请输入用户名-密码");
		String string = scanner.nextLine();
		if(string.contains("-")) {
			String[] split = string.split("-");
			// 进行权限验证
			if (split[0].equals("root") && split[1].equals("root")) {
				System.out.println("欢迎管理员回来！！！");
				// 进入管理员权限
				AdminFountion.guanLi(product, pdtList, userList, user);
			} else {
				// 进入用户权限
				dengLu(user, userList, product, pdtList, orderList, split);
				// 提示用户该账号不存在，请退出系统，重新登录
				System.out.println("该账号不存在，请退出系统，重新登录");
				Fountion.exit(user, userList);

			}
		}else {
			//提示用户名输入格式错误
			System.out.println("输入有误，请输入正确格式：用户名-密码");
			login(user, userList, product, pdtList, orderList);
		}
		
		
		
		
	}

	/**
	 * 登录的方法
	 * 
	 * @param user
	 * @param userList
	 * @param product
	 * @param pdtList
	 * @param orderList
	 * @param split
	 * @return
	 */
	private static void dengLu(User user, List<User> userList, Product product, List<Product> pdtList,
			List<Order> orderList, String[] split) {
		// 进入用户权限登录
		for (User user1 : userList) {
			if (split[0].equals(user1.getUsername())) {
				if (split[1].equals(user1.getPassword())) {
					user = user1;
					// 判断用户是否被查封
					if (user.isIsban() == false) {
						System.out.println("欢迎" + user.getUsername() + "回来，您上次的登录时间为" + user.getTimestamp());
						System.out.println("如果不是本人操作请及时修改密码。(如果用户已被封则不允许登录并给用户对应的提示)");
						System.out.println("请选择功能： 1 、继续登录  2 、修改密码");
						while (true) {
							int num = Integer.parseInt(scanner.nextLine());
							if (num == 1) {

								// 继续登录
								UserFountion.yongHu(user, userList, product, pdtList, orderList);
								break;
							} else if (num == 2) {

								// 修改密码
								UserFountion.xiuGai(user, userList);
								break;
							} else if (num == 0) {
								// 退出系统
								exit(user, userList);
							} else {
								// 操作错误，请重新输入
								System.out.println("输入错误，请重新输入： 1 、继续登录  2 、修改密码");
							}
						}
					} else {
						// 该账户已被查封
						System.out.println("该账户已被查封!!!!!!");
						Fountion.exit(user, userList);
					}
				} else {
					if (count > 0) {
						// 输入有误,请重新输入
						System.out.println("输入错误，您还有" + (--count) + "次机会");
						// 判断输入剩余的次数
						System.out.println("密码输错三次，将退出系统");
						Welcome.welcome();
					}
					break;
				}
			}

		}
		
	}

	/**
	 * 注册的方法
	 * 
	 * @throws IOException
	 */
	public static void regist(User user, List<User> userList, Product product, List<Product> pdtList,
			List<Order> orderList) {
		// 定义用户是否被封
		boolean isban = false;
		// 提示用户输入用户名和密码
		System.out.println("请输入用户名和密码:用户名-密码");
		String str = scanner.nextLine();
		if(str.contains("-")) {
			
			// 拿到用户名
			String[] split = str.split("-");
			boolean flag1 = false;
			for (User user1 : userList) {
				user = user1;
				if(split[0].equals(user.getUsername())) {
					System.out.println("该用户已存在,请重新注册");
					System.out.println();
					flag1 = true ;
					Welcome.welcome();
					break;
				}
				
			}
			if(!flag1) {
				
				boolean flag = Utils.checkPwd(split[1]);
				if (flag) {
					user = new User(split[0], split[1], isban);
					userList.add(user);
					// 将集合存入文件中
					Save.saveUser(userList);
					// 提示用户注册成功
					System.out.println("注册成功");
					System.out.println("请登录");
					login(user, userList, product, pdtList, orderList);
				} else {
					// 提示用户注册失败
					System.out.println("---密码格式错误，请重新注册---");
					regist(user, userList, product, pdtList, orderList);
				}
			}
		}else {
			//提示用户输入的格式错误
			System.out.println("---输入格式错误，请重新输入正确格式：用户名-密码");
			regist(user, userList, product, pdtList, orderList);
		}
	}

	/**
	 * 退出的方法
	 */
	public static void exit(User user, List<User> userList) {
		// 获取系统当前时间戳
		long timenow = System.currentTimeMillis();
		// 创建时间对象，获取系统时间（没有格式化的）
		Date date = new Date(timenow);
		// 创建格式对象（格式化时间）
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		// 将时间戳格式化
		String format = dateFormat.format(date);
		user.setTimestamp(format);
		userList.add(user);
		Save.saveUser(userList);
		System.out.println("感谢您的使用，欢迎下次再来");
		System.out.println();
		Welcome.welcome();
	}
}
