package com.yuccheng.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "SortNumber")
public class SortNumber {

	@Id
	private String user_name;

	private int user_number;

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public int getUser_number() {
		return user_number;
	}

	public void setUser_number(int user_number) {
		this.user_number = user_number;
	}

	@Override
	public String toString() {
		return "SortNumber [user_name=" + user_name + ", user_number=" + user_number + "]";
	}

}
