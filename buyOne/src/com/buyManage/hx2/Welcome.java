package com.buyManage.hx2;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

import com.buyManage.hx1.Order;
import com.buyManage.hx1.Product;
import com.buyManage.hx1.User;
import com.buyManage.hx3.Begin;

public class Welcome {
	// 创建用户输入的对象
	public static Scanner scanner = new Scanner(System.in);
	// 创建用户的对象
	public static User user = new User();
	// 创建商品对象
	public static Product product = new Product();
	// List<User>集合初始化
	public static List<User> userList = Begin.UserBegin();
	// List<Product> 集合初始化
	public static List<Product> pdtList = Begin.PdtBegin();
	// List<Order> 集合初始化
	public static List<Order> orderList = Begin.OrderBegin();
	//创建订单类对象
	public static Order order = new Order();
    
	/**
	 * 程序入口
	 * @param args
	 */
	public static void main(String[] args) {
		// 欢迎页
		welcome();
	}

	/**
	 * 欢迎页显示的方法
	 * 
	 * @throws IOException
	 */
	public static void welcome() {
		System.out.println("-----------欢迎使用良心购物系统----------");
		System.out.println("本系统为良心商城,绝无假冒伪劣产品。价格公道，欢迎选购！");
		System.out.println("请选择需要使用的功能：");
		System.out.println("1：登录");
		System.out.println("2：注册");
		System.out.println("0：退出(任意界面中输入0，即可退出系统)");
		// 接收用户的输入
		try {
			while (true) {
				int flag = Integer.parseInt(scanner.nextLine());
				if (flag == 1) {
					// 用户登录
					Fountion.login(user, userList, product, pdtList, orderList);
				} else if (flag == 2) {
					// 注册
					Fountion.regist(user, userList, product, pdtList, orderList);
				} else if (flag == 0) {
					// 退出
					Fountion.exit(user, userList);
				} else {
					// 提示用户输入操作有误，请重新输入
					System.out.println("您选择的功能有误，请重新输入");
				}
				break;
			}
		} catch (NumberFormatException e) {
			// 提示用户输入的类型有误，请重新输入
			System.out.println("您选择的功能有误，请重新输入");
			welcome();
		}
	}
}
