package com.buyManage.hx3;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.List;

import com.buyManage.hx1.Order;
import com.buyManage.hx1.Product;
import com.buyManage.hx1.User;

/**
 * 集合对象储存类
 * @author bear
 *
 */
public class Save {

	/**
	 * 保存List<user> 的方法
	 * 
	 * @throws IOException
	 * @throws FileNotFoundException
	 */

	public static void saveUser(List<User> userList) {
		// 创建对象输出流
		ObjectOutputStream oos = null;
		try {
			oos = new ObjectOutputStream(new FileOutputStream("user.txt"));
			// 将集合通过对象流 存入到文件
			oos.writeObject(userList);
			// 关闭流
			oos.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 保存List<Product>的方法
	 * 
	 * @param product
	 */
	public static void saveProduct(List<Product> product) {
		// 创建对象输出流
		ObjectOutputStream oos = null;
		try {
			oos = new ObjectOutputStream(new FileOutputStream("product.txt"));
			// 将商品集合通过对象流放入文件中
			oos.writeObject(product);
			// 关闭对象流
			oos.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 将订单存入到List<Order> 集合中
	 */

	public static void saveOrder(List<Order> orderList) {
		// 创建对象输出流
		ObjectOutputStream oos = null;
		try {
			oos = new ObjectOutputStream(new FileOutputStream("order.txt"));
			// 将商品集合通过对象流放入文件中
			oos.writeObject(orderList);
			// 关闭对象流
			oos.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
