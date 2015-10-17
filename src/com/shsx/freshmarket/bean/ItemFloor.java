package com.shsx.freshmarket.bean;

public class ItemFloor {

	private int ProductId;
	private String ProductName;
	private String Weight;
	private double PRICE;
	private double OLDPRICE;
	private String PRICENAME;
	private int PictureId;
	private String EndTime;
	private String image;
	private int FloorMasterId;
	private String Url;
	private int LinkType;
	private int Id;
	private String LinkContent;
	private String LinkContentName;
	private int ProductVariantId;
	
	public int getFloorMasterId() {
		return FloorMasterId;
	}
	public void setFloorMasterId(int floorMasterId) {
		FloorMasterId = floorMasterId;
	}
	public String getUrl() {
		return Url;
	}
	public void setUrl(String url) {
		Url = url;
	}
	public int getLinkType() {
		return LinkType;
	}
	public void setLinkType(int linkType) {
		LinkType = linkType;
	}
	public int getId() {
		return Id;
	}
	public void setId(int id) {
		Id = id;
	}
	public String getLinkContent() {
		return LinkContent;
	}
	public void setLinkContent(String linkContent) {
		LinkContent = linkContent;
	}
	public String getLinkContentName() {
		return LinkContentName;
	}
	public void setLinkContentName(String linkContentName) {
		LinkContentName = linkContentName;
	}
	public int getProductVariantId() {
		return ProductVariantId;
	}
	public void setProductVariantId(int productVariantId) {
		ProductVariantId = productVariantId;
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
	public double getPRICE() {
		return PRICE;
	}
	public void setPRICE(double pRICE) {
		PRICE = pRICE;
	}
	public double getOLDPRICE() {
		return OLDPRICE;
	}
	public void setOLDPRICE(double oLDPRICE) {
		OLDPRICE = oLDPRICE;
	}
	public String getPRICENAME() {
		return PRICENAME;
	}
	public void setPRICENAME(String pRICENAME) {
		PRICENAME = pRICENAME;
	}
	public int getPictureId() {
		return PictureId;
	}
	public void setPictureId(int pictureId) {
		PictureId = pictureId;
	}
	public String getEndTime() {
		return EndTime;
	}
	public void setEndTime(String endTime) {
		EndTime = endTime;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
}
