package com.xzg.entity;

public class Order {
	private String id;
	private String number;
	private String mobile;
	private String price;
	private String true_name;
	private String create_id;

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNumber() {
		return this.number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getMobile() {
		return this.mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getPrice() {
		return this.price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getTrue_name() {
		return this.true_name;
	}

	public void setTrue_name(String true_name) {
		this.true_name = true_name;
	}

	public String getCreate_id() {
		return this.create_id;
	}

	public void setCreate_id(String create_id) {
		this.create_id = create_id;
	}
}
