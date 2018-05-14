package com.cn.custom_service.pojo;

public class User {
private Integer id;
private String userName;
private String password;
public User(Integer id, String userName, String password) {
	super();
	this.id = id;
	this.userName = userName;
	this.password = password;
}
public User() {
	super();
	// TODO Auto-generated constructor stub
}
public Integer getId() {
	return id;
}
public void setId(Integer id) {
	this.id = id;
}
public String getUserName() {
	return userName;
}
public void setUserName(String userName) {
	this.userName = userName;
}
public String getPassword() {
	return password;
}
public void setPassword(String password) {
	this.password = password;
}

}
