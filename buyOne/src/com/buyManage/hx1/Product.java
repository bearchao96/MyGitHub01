package com.buyManage.hx1;

import java.io.Serializable;
/**
 * 商品类
 * @author bear
 *
 */
public class Product implements Serializable {
	/**
	 * 商品属性
	 */
	private static final long serialVersionUID = 1L;
	// 商品id
	private int id;
	// 商品名字
	private String name;
	// 商品价格
	private double price;

	// 商品无参构造
	public Product() {
		super();
		// TODO Auto-generated constructor stub
	}

	// 商品有参构造
	public Product(int id, String name, double price) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
	}

	// set get 方法
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

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	// toString方法
	@Override
	public String toString() {
		return "商品ID： " + id + "  商品名称： " + name + "  商品价格： " + price;
	}

}
