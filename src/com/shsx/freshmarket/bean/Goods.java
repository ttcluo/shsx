package com.shsx.freshmarket.bean;

import java.util.List;

public class Goods {

	private List<Banner> banner;
	private Product product;
	public List<Banner> getBanner() {
		return banner;
	}
	public void setBanner(List<Banner> banner) {
		this.banner = banner;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
}
