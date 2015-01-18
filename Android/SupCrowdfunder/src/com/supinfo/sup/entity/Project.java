package com.supinfo.sup.entity;

import java.io.Serializable;
import java.util.ArrayList;

public class Project implements Serializable{
	private Long Id;
	private String Title ;
	private String Body;
	private Float Price;
	private Float NowPrice;
	private ArrayList user;
	private Category category;
	public Project(){};
	public Long getId() {
		return Id;
	}
	public void setId(Long id) {
		Id = id;
	}
	
	public ArrayList getUser() {
		return user;
	}
	public void setUser(ArrayList user) {
		this.user = user;
	}
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	public String getBody() {
		return Body;
	}
	public void setBody(String body) {
		Body = body;
	}
	public String getTitle() {
		return Title;
	}
	public void setTitle(String title) {
		Title = title;
	}
	public Float getPrice() {
		return Price;
	}
	public void setPrice(Float price) {
		Price = price;
	}
	public Float getNowPrice() {
		return NowPrice;
	}
	public void setNowPrice(Float nowPrice) {
		NowPrice = nowPrice;
	}

	
}
