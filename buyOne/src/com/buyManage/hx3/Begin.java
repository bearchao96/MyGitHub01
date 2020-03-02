package com.buyManage.hx3;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;

import com.buyManage.hx1.Order;
import com.buyManage.hx1.Product;
import com.buyManage.hx1.User;
/**
 * 
 * 集合初始化类
 * @author bear
 *
 */
public class Begin {

	/**
	 * 
	 * List<User>集合初始化
	 * 
	 */
	@SuppressWarnings("unchecked")
	public static List<User> UserBegin() {

		File file = new File("user.txt");
		ObjectInputStream ois = null;
		List<User> userList = null;
		try {
			ois = new ObjectInputStream(new FileInputStream(file));
			userList = (List<User>) ois.readObject(); // 对象向下转型
		} catch (Exception e) {
			userList = new ArrayList<>();
		} finally {
			if (ois != null) {
				try {
					ois.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return userList;

	}

	/**
	 * :List<Product>集合初始化
	 */

	public static List<Product> PdtBegin() {
		// 创建文件
		File file = new File("product.txt");
		// 创建商品集合
		List<Product> pdtList = null;
		// 创建对象输入流
		ObjectInputStream ois = null;
		try {
			ois = new ObjectInputStream(new FileInputStream(file));
			// 读入
			pdtList = (List<Product>) ois.readObject();
		} catch (Exception e) {
			pdtList = new ArrayList<Product>();
		} finally {
			if (ois != null) {
				try {
					ois.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return pdtList;
	}

	/**
	 * 订单集合初始化
	 */
	public static List<Order> OrderBegin() {
		// 创建文件
		File file = new File("order.txt");
		// 创建商品集合
		List<Order> orderList = null;
		// 创建对象输入流
		ObjectInputStream ois = null;
		try {
			ois = new ObjectInputStream(new FileInputStream(file));
			// 读入
			orderList = (List<Order>) ois.readObject();
		} catch (Exception e) {
			orderList = new ArrayList<Order>();
		} finally {
			if (ois != null) {
				try {
					ois.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return orderList;
	}

}
