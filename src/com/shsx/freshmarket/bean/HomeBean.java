package com.shsx.freshmarket.bean;

import java.util.List;

public class HomeBean {

	private List<Floor> data;
	private String success;

	public List<Floor> getData() {
		return data;
	}

	public void setData(List<Floor> data) {
		this.data = data;
	}

	public String getSuccess() {
		return success;
	}

	public void setSuccess(String success) {
		this.success = success;
	}
}
