package com.coding.sushi.model;

public class CustomResponseObject {

	private SushiOrder order;
	private Integer code;
	private String msg;

	public SushiOrder getOrder() {
		return order;
	}

	public void setOrder(SushiOrder order) {
		this.order = order;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

}
