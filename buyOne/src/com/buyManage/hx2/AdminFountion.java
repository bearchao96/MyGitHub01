package com.buyManage.hx2;

import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.List;
import java.util.Scanner;
import com.buyManage.hx1.Product;
import com.buyManage.hx1.User;
import com.buyManage.hx3.Save;

/**
 * 管理员功能方法
 * 
 * @author bear
 *
 */

public class AdminFountion {
	// 创建键盘输入对象
	public static Scanner scanner = new Scanner(System.in);
	// 欢迎界面

	public static void guanLi(Product product, List<Product> pdtList, List<User> userList, User user) {
		System.out.println("请选择您要完成的操作");
		System.out.println("1：查看所有用户");
		System.out.println("2：删除用户");
		System.out.println("3：查封用户");
		System.out.println("4：添加商品");
		System.out.println("5：删除商品");
		System.out.println("6：查询商品");
		System.out.println("0：退出系统");
		while (true) {
			// 获取管理员录入的键值
			int num = Integer.parseInt(scanner.nextLine());
			// 匹配判断
			if (num == 1) {
				// 查看所有用户
				System.out.println("查询用户如下：");
				List<User> listNew = new ArrayList<User>();
				// 重复的去重
				for (User user1 : userList) {
					if (!listNew.contains(user1)) {
						listNew.add(user1);
					}
				}
				userList = listNew;
				Save.saveUser(userList);
				for (User user2 : userList) {
					user = user2;
					System.out.println("  "+user.getUsername());
				}
			} else if (num == 2) {
				// 删除用户
				// 提示管理员输入要删除的用户姓名
				System.out.println("请输入要删除的用户");
				String username = scanner.nextLine();
				try {
					for (User user1 : userList) {
						user = user1;
						if (username.equals(user.getUsername())) {
							userList.remove(user);
							Save.saveUser(userList);
						}
					}
				} catch (ConcurrentModificationException e) {
					// TODO: handle exception
				}
				System.out.println("删除成功");
				System.out.println("请继续选择操作");

			} else if (num == 3) {
				// 查封用户

				System.out.println("请输入您要查封的用户名");
				String username = scanner.nextLine();
				boolean flag = false;
				try {
					for (User user1 : userList) {
						user = user1;
						// 判断输入的的用户名是否存在
						if (username.equals(user.getUsername())) {
							user.setIsban(true);
							userList.add(user);
							flag = true;
							System.out.println("查封成功");
							System.out.println("请继续选择功能操作");
							break;
						}
					}

					if (!flag) {
						System.out.println("该用户不存在，请重新输入功能 3按钮");
					}
					Save.saveUser(userList);
				} catch (ConcurrentModificationException e) {

				}
			} else if (num == 4) {
				// 添加商品
				System.out.println("请输入您要添加的商品：ID-商品名称-商品价格");
				String str = scanner.nextLine();
				String[] split = str.split("-");
				try {
					boolean flag = false;
					for (Product product1 : pdtList) {
						if (Integer.parseInt(split[0]) == product1.getId() || split[1].equals(product1.getName())) {
							flag = true;
							break;
						}

					}

					if (!flag) {

						// 将商品添加到商品容器中
						product = new Product(Integer.parseInt(split[0]), split[1], Double.parseDouble(split[2]));
						pdtList.add(product);
						Save.saveProduct(pdtList);
						// 提示管理员添加成功
						System.out.println("商品添加成功");
					} else {
						System.out.println("该商品或编号已存在,请重新输入:功能 4 按钮");

					}

				} catch (ConcurrentModificationException e) {
					// TODO: handle exception

				}

			} else if (num == 5) {
				// 删除商品
				System.out.println("请输入您要删除的商品Id");
				boolean flag = false;
				try {
					int id = Integer.parseInt(scanner.nextLine());
					for (Product product1 : pdtList) {
						if (id == product1.getId()) {
							pdtList.remove(product1);
							flag = true;
							// 提示管理员删除成功
							System.out.println("删除商品成功");
							break;
						}
					}
					if (!flag) {
						System.out.println("输入的ID有误，请重新输入:功能5按 ");
					}
					Save.saveProduct(pdtList);
				} catch (ConcurrentModificationException e) {
					// TODO: handle exception
					guanLi(product, pdtList, userList, user);
				}

			} else if (num == 6) {
				// 查看商品
               System.out.println("所有商品如下：");
				for (Product product1 : pdtList) {
					System.out.println(product1);
				}
				
			} else if (num == 0) {
				// 退出系统
				Fountion.exit(user, userList);
			} else {
				// 提示用户输入错误，请重新输入
				System.out.println("输入错误，请重新输入");
			}
		}
	}
}
