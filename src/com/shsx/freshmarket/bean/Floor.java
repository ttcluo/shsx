package com.shsx.freshmarket.bean;

import java.util.List;

public class Floor {

	private int Id;
	private int Type;
	private String Title;
	private String Description;
	private String DisplayOrder;
	private List<ItemFloor> FloorItem;
	public int getId() {
		return Id;
	}
	public void setId(int id) {
		Id = id;
	}
	public int getType() {
		return Type;
	}
	public void setType(int type) {
		Type = type;
	}
	public String getTitle() {
		return Title;
	}
	public void setTitle(String title) {
		Title = title;
	}
	public String getDescription() {
		return Description;
	}
	public void setDescription(String description) {
		Description = description;
	}
	public String getDisplayOrder() {
		return DisplayOrder;
	}
	public void setDisplayOrder(String displayOrder) {
		DisplayOrder = displayOrder;
	}
	public List<ItemFloor> getFloorItem() {
		return FloorItem;
	}
	public void setFloorItem(List<ItemFloor> floorItem) {
		FloorItem = floorItem;
	}
	
}
