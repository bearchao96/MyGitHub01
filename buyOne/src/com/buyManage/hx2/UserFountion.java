package com.buyManage.hx2;

import java.util.ConcurrentModificationException;
import java.util.List;
import java.util.Scanner;
import com.buyManage.hx1.Order;
import com.buyManage.hx1.Product;
import com.buyManage.hx1.User;
import com.buyManage.hx3.Save;
/**
 * 用户功能类
 * @author bear
 *
 */
public class UserFountion {
	// 创建键盘输入
	public static Scanner scanner = new Scanner(System.in);
	// 创建订单类对象
	public static Order order = new Order();
	public static double vipSum = 0 ;
	
	/**
	 * 普通用户权限功能
	 */
	public static void yongHu(User user, List<User> userList, Product product, List<Product> pdtList,
			List<Order> orderList) {
		// 提示用户输入功能
		System.out.println("请输入您要选择的功能");
		System.out.println("1：查看余额");
		System.out.println("2：充值");
		System.out.println("3：购买商品");
		System.out.println("4：查看VIP等级");
		System.out.println("5：查看所有订单信息");
		System.out.println("6：结算");
		System.out.println("0：退出");

		//定义订单消费总金额
		double sum = 0;
		//
		double balance;
		
		try {
			while (true) {
				String string = scanner.nextLine();
				int num = Integer.parseInt(string);
				if (num == 1) {
					// 查看余额
					balance = user.getBalance();
					System.out.println(balance + "元");

				} else if (num == 2) {
					// 充值
					System.out.println("---请输入充值金额");
					double balance2 = user.getBalance();
					double double2 = Double.parseDouble(scanner.nextLine());
					//最多充值100000元
					if(balance2+double2 >=0 && balance2+double2 <= 100000) {
						
						if(double2>0 && double2 <=100000) {
							balance = user.getBalance() + double2;
							user.setBalance(balance);
							userList.add(user);
							Save.saveUser(userList);
							// 提示用户充值完成
							System.out.println("---您已充值完成,当前卡里可用余额为： " + balance + "元");
							System.out.println("---选择功能键2,继续充值---");
						}else {
							//如果充值金额不满足，就要重新充值
							System.out.println("---对不起，无法充值，请重新输入正确金额！！！---");
							System.out.println("请输入功能按钮2,重新充值!!!");
						}
					}else {
						System.out.println("充值金额已达上限，为了资金安全请先消费");
						System.out.println("请选择购买按钮3！！！");
					}

				} else if (num == 3) {
					// 购买商品
					for (Product product2 : pdtList) {
						System.out.println(product2);
					}
					
					boolean flag = false ;
					System.out.println();
					System.out.println("---请输入您需要购买的商品ID以及商品数量");
					// 拿到商品id
					System.out.println("---请输入商品id");
					int id = Integer.parseInt(scanner.nextLine());
					//设置id只能输入正确的数字
					if(id>0) {
						for (Product product1 : pdtList) {
							if(id == product1.getId()) {
								flag = true ;
								break ;
							}
						}
						
						if(flag) {
							
							// 拿到商品数量
							System.out.println("---请输入商品数量");
							int many = Integer.parseInt(scanner.nextLine());
							//设置商品单件的购买数量
							if(many > 0 && many <= 50) {
								
								for (int i = 0; i < pdtList.size(); i++) {
									if (id == pdtList.get(i).getId()) {
										// 拿到商品id
										int id2 = pdtList.get(i).getId();
										// 拿到商品名称
										String name = pdtList.get(i).getName();
										// 拿到商品单价
										double price = pdtList.get(i).getPrice();
										// 拿到订单编号
										long num1 = pdtList.get(i).getId() + System.currentTimeMillis();
										// 拿到总价格
										double money = many * price;
										// 添加订单
										order = new Order(num1, id2, name, many, price, money,"未支付");
										// 将订单对象加入到集合
										orderList.add(order);
										// 将集合保存到文件
										Save.saveOrder(orderList);
										// 提示用户订单添加状态
										System.out.println("---订单添加成功---");
									}
									continue;
								}
							}else {
								System.out.println("---请输入正确购买数量，按功能3按键继续购买---");
								System.out.println("---为了防止倒买倒卖商品，单件商品最多买50件---");
								System.out.println("---谢谢配合！！！---");
								yongHu(user, userList, product, pdtList, orderList);
							}
						}else {
							System.out.println("---输入商品ID不存在，请重新输入功能3按钮---");
						}
					}else {
						System.out.println("---id输入有误，请重新输入：如：1,2,3,4.....等---");
						System.out.println("---请输入正确商品ID，按功能3按键继续购买---");
						yongHu(user, userList, product, pdtList, orderList);
					}

				} else if (num == 4) {
					// 查看VIP等级
					// 拿到用户消费的金额
					System.out.println("---VIP等级查询如下：");
					//拿到用户VIP等级折扣后的金额
					double suan1 = jieSuan1(orderList, sum);
					// 等级确定
					if (suan1 >= 1 && suan1 < 100) {
						System.out.println("该用户为VIP1");
						user.setVip("vip1");
					} else if (suan1 >= 100 && suan1 < 1000) {
						System.out.println("该用户为VIP2");
						user.setVip("vip2");
					} else if (suan1 >= 1000 && suan1 < 10000) {
						System.out.println("该用户为VIP3");
						user.setVip("vip3");
					} else if (suan1 >= 10000 && suan1 < 100000) {
						System.out.println("该用户为VIP4");
						user.setVip("vip4");
					} else if (suan1 >= 100000) {
						System.out.println("该用户为VIP5");
						user.setVip("vip5");
					}
					// 存入集合
					userList.add(user);
					Save.saveUser(userList);

				} else if (num == 5) {
					// 查看所有订单信息
					System.out.println("---商品订单信息如下：");
					for (Order order : orderList) {
						System.out.println(order);
					}
					
					
					
				} else if (num == 6) {
					// 购买结算
					double vipSum = jieSuan(orderList, sum);
					//拿到用户余额
					double balance2 = user.getBalance();
					// 购买金额和余额比较  判断能否购买成功
					if (vipSum > balance2) {
						System.out.println("您的余额不足,请充值后，在进行购买");
					} else {
						System.out.println("购买成功");
						// 结余 后将剩余金额存入到用户对象
						balance = balance2 - vipSum;
						user.setBalance(balance);
						userList.add(user);
						Save.saveUser(userList);
					}

				} else if (num == 0) {
					// 退出系统
					Fountion.exit(user, userList);
					
				} else {
					// 提示用户输入错误，请重新输入
					System.out.println("您输入的信息有误，请重新输入");

				}

			}

		} catch (NumberFormatException e) {
			// TODO: handle exception
		}
	}

	/**
	 * 购买结算的方法
	 * 
	 * @param orderList
	 * @param sum
	 * @return
	 */
	
	private static double jieSuan(List<Order> orderList, double sum) {
		// 账单结算
		
		//设置开关
		boolean flag = false ;
		
		try{
			for (Order order1 : orderList) {
				//遍历所有订单
			if(order1.getIsbuy().equals("未支付")) {
				//拿到未支付订单对象
				order = order1;
				//拿到商品总价格
				double money = order.getMoney();
				//拿到订单消费的总金额
				sum = sum + money;
				//拿到Vip折扣后的总价格
				vipSum = Vip(sum);
				//将订单的状态改为已支付并写入到本地文件中保存
				order.setIsbuy("已支付");
				flag = true ;
			}
			if(flag) {
				//将修改后的值写入到文件
				Save.saveOrder(orderList);
			}
		}
		}catch (ConcurrentModificationException e) {
			// TODO: handle exception
		}
		//提示用户消费的总金额
		System.out.println("欢迎光临京东商城，祝您购物愉快");
		System.out.println("您本次消费的总金额为" + vipSum + "元");
		return vipSum;
	}
	/**
	 *  返回vip优惠金额的 方法
	 * @param orderList
	 * @param sum
	 * @return
	 */
	private static double jieSuan1(List<Order> orderList, double sum) {
		
		for (Order order1 : orderList) {
			order = order1;
			double money = order.getMoney();
			sum = sum + money;
			vipSum = Vip(sum);
		}
		return vipSum;
	}

	/**
	 * 用户修改密码的方法
	 */
	public static void xiuGai(User user, List<User> userList) {
		// 提示用户输入原密码
		System.out.println("请您输入原密码");
		String password1 = scanner.nextLine();
		System.out.println("请您输入新密码");
		String password2 = scanner.nextLine();
		System.out.println("请确认你的新密码");
		String password3 = scanner.nextLine();
		// 对两次密码进行判断
		if (password2.equals(password3)) {
			user.setPassword(password3);
			userList.add(user);
			Save.saveUser(userList);
			// 提示用户修改成功
			System.out.println("修改成功");
			System.out.println("请重新登录");
			Welcome.welcome();
		}

	}
	
	/**
	 * VIP 折扣方法
	 */
    public static double Vip(double sum) {
    	
    	if(sum >=1 && sum <100) {
    		sum = sum ;
    	}else if(sum >=100 && sum < 1000){
    		sum = sum * 0.95 ;
    	}else if(sum >=1000 && sum < 10000){
    		sum = sum * 0.9 ;
    	}else if(sum >=10000 && sum < 100000){
    		sum = sum * 0.8 ;
    	}else if(sum >=100000 ){
    		sum = sum * 0.7 ;
    	}
    	vipSum = sum ;
		return vipSum;
    }
}
