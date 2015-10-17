package com.shsx.freshmarket.bean;

public class FlashSale {

	private int Batch;
	private int status;
	private int time;
	private String ProductVariantPriceName;
	private String CreateTime;
	private String EndTime;
	private String Discount;
	private String Unit;
	private boolean Standard;
	private double PeriodMoney;
	private double DefaultMoney;
	private int ProductId;
	private String ProductName;
	private String Weight;
	private int PictureId;
	private String servertime;
	private String image;
	public int getBatch() {
		return Batch;
	}
	public void setBatch(int batch) {
		Batch = batch;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public int getTime() {
		return time;
	}
	public void setTime(int time) {
		this.time = time;
	}
	public String getProductVariantPriceName() {
		return ProductVariantPriceName;
	}
	public void setProductVariantPriceName(String productVariantPriceName) {
		ProductVariantPriceName = productVariantPriceName;
	}
	public String getCreateTime() {
		return CreateTime;
	}
	public void setCreateTime(String createTime) {
		CreateTime = createTime;
	}
	public String getEndTime() {
		return EndTime;
	}
	public void setEndTime(String endTime) {
		EndTime = endTime;
	}
	public String getDiscount() {
		return Discount;
	}
	public void setDiscount(String discount) {
		Discount = discount;
	}
	public String getUnit() {
		return Unit;
	}
	public void setUnit(String unit) {
		Unit = unit;
	}
	public boolean isStandard() {
		return Standard;
	}
	public void setStandard(boolean standard) {
		Standard = standard;
	}
	public double getPeriodMoney() {
		return PeriodMoney;
	}
	public void setPeriodMoney(double periodMoney) {
		PeriodMoney = periodMoney;
	}
	public double getDefaultMoney() {
		return DefaultMoney;
	}
	public void setDefaultMoney(double defaultMoney) {
		DefaultMoney = defaultMoney;
	}
	public int getProductId() {
		return ProductId;
	}
	public void setProductId(int productId) {
		ProductId = productId;
	}
	public String getProductName() {
		return ProductName;
	}
	public void setProductName(String productName) {
		ProductName = productName;
	}
	public String getWeight() {
		return Weight;
	}
	public void setWeight(String weight) {
		Weight = weight;
	}
	public int getPictureId() {
		return PictureId;
	}
	public void setPictureId(int pictureId) {
		PictureId = pictureId;
	}
	public String getServertime() {
		return servertime;
	}
	public void setServertime(String servertime) {
		this.servertime = servertime;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
}
