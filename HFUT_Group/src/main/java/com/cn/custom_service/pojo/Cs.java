package com.cn.custom_service.pojo;

import java.sql.Date;
//ÑôÐ÷³Ç ¿Í·þ
public class Cs {
	private int cid;
	private String password;
	private String images;
	private int gender;
    private int age;
	private int serviceNnum;
	private String mailaddress;
	private String telno;
	private Date regTime;
	private double judge;
	private int status;
	public int getCid() {
		return cid;
	}
	public void setCid(int cid) {
		this.cid = cid;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getImages() {
		return images;
	}
	public void setImages(String images) {
		this.images = images;
	}
	public int getGender() {
		return gender;
	}
	public void setGender(int gender) {
		this.gender = gender;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public int getServiceNnum() {
		return serviceNnum;
	}
	public void setServiceNnum(int serviceNnum) {
		this.serviceNnum = serviceNnum;
	}
	public String getMailaddress() {
		return mailaddress;
	}
	public void setMailaddress(String mailaddress) {
		this.mailaddress = mailaddress;
	}
	public String getTelno() {
		return telno;
	}
	public void setTelno(String telno) {
		this.telno = telno;
	}
	public Date getRegTime() {
		return regTime;
	}
	public void setRegTime(Date regTime) {
		this.regTime = regTime;
	}
	public double getJudge() {
		return judge;
	}
	public void setJudge(double judge) {
		this.judge = judge;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public Cs(int cid, String password, String images, int gender, int age, int serviceNnum,
			String mailaddress, String telno, Date regTime, double judge, int status) {
		super();
		this.cid = cid;
		this.password = password;
		this.images = images;
		this.gender = gender;
		this.age = age;
		this.serviceNnum = serviceNnum;
		this.mailaddress = mailaddress;
		this.telno = telno;
		this.regTime = regTime;
		this.judge = judge;
		this.status = status;
	}
	public Cs() {
		super();
		// TODO Auto-generated constructor stub
	}
}
