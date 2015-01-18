package com.supinfo.sup.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Category implements Serializable{

	private Long ID;
	private String Name;
	private ArrayList project;
	public Category(){}
	public Long getID() {
		return ID;
	}
	public void setID(Long iD) {
		ID = iD;
	}

	public ArrayList getProject() {
		return project;
	}
	public void setProject(ArrayList project) {
		this.project = project;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}

}
