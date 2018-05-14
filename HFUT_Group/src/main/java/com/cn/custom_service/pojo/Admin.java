package com.cn.custom_service.pojo;

public class Admin {
	int aid;
	String password;
	String mailaddress;
	public Admin() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Admin(int aid, String password, String mailaddress) {
		super();
		this.aid = aid;
		this.password = password;
		this.mailaddress = mailaddress;
	}
	public int getAid() {
		return aid;
	}
	public void setAid(int aid) {
		this.aid = aid;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getMailaddress() {
		return mailaddress;
	}
	public void setMailaddress(String mailaddress) {
		this.mailaddress = mailaddress;
	}
	@Override
	public String toString() {
		return "Admin [aid=" + aid + ", password=" + password + ", mailaddress=" + mailaddress + "]";
	}
	
	
}
