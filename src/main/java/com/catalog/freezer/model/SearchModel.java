package com.catalog.freezer.model;

import java.util.Date;

import org.springframework.data.domain.Pageable;

public class SearchModel {
	
	 String name;
	 String foodTypeName;
	 Date createdAtStart;
	 Date createdAtEnd;
	 Pageable pageable;
	 
	public SearchModel(String name, String foodTypeName, Date createdAtStart, 
			Date createdAtEnd, Pageable pageable) {
		super();
		this.name = name;
		this.foodTypeName = foodTypeName;
		this.createdAtStart = createdAtStart;
		this.createdAtEnd = createdAtEnd;
		this.pageable = pageable;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getFoodTypeName() {
		return foodTypeName;
	}
	public void setFoodTypeName(String foodTypeName) {
		this.foodTypeName = foodTypeName;
	}
	public Date getCreatedAtStart() {
		return createdAtStart;
	}
	public void setCreatedAtStart(Date createdAtStart) {
		this.createdAtStart = createdAtStart;
	}
	public Date getCreatedAtEnd() {
		return createdAtEnd;
	}
	public void setCreatedAtEnd(Date createdAtEnd) {
		this.createdAtEnd = createdAtEnd;
	}
	public Pageable getPageable() {
		return pageable;
	}
	public void setPageable(Pageable pageable) {
		this.pageable = pageable;
	}
}
