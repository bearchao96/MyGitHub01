package com.buyManage.hx1;

import java.io.Serializable;

/**
 * 订单类
 * 
 * @author bear
 *
 */
public class Order implements Serializable {

	/**
	 * 订单的属性
	 */
	private static final long serialVersionUID = 1L;
	// 定单编号
	private long num;
	// 商品id
	private int id;
	// 商品名称
	private String name;
	// 商品数量
	private int many;
	// 商品价格
	private double price;
	// 商品总价格
	private double money;
	// 商品支付状态
	private String isbuy;

	public Order() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Order(long num, int id, String name, int many, double price, double money, String isbuy) {
		super();
		this.num = num;
		this.id = id;
		this.name = name;
		this.many = many;
		this.price = price;
		this.money = money;
		this.isbuy = isbuy;
	}

	public long getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getMany() {
		return many;
	}

	public void setMany(int many) {
		this.many = many;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public double getMoney() {
		return money;
	}

	public void setMoney(double money) {
		this.money = money;
	}

	public String getIsbuy() {
		return isbuy;
	}

	public void setIsbuy(String isbuy) {
		this.isbuy = isbuy;
	}

	@Override
	public String toString() {
		return "订单编号：" + num + "  商品id：" + id + "  商品名称：" + name + "  商品数量：" + many + "  商品价格：" + price
				+ "  商品总金额：" + money + "  订单状态：" + isbuy;
	}

}
