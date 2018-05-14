package com.cn.custom_service.pojo;

import java.sql.Date;

public class CustomService {
	private int cid;
	private String password;
	private String images;
	private int gender;
	private int age ;
	private int serivcNnum;
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
	public int getSerivcNnum() {
		return serivcNnum;
	}
	public void setSerivcNnum(int serivcNnum) {
		this.serivcNnum = serivcNnum;
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
	@Override
	public String toString() {
		return "CustomService [cid=" + cid + ", password=" + password + ", images=" + images + ", gender=" + gender
				+ ", age=" + age + ", serivcNnum=" + serivcNnum + ", mailaddress=" + mailaddress + ", telno=" + telno
				+ ", regTime=" + regTime + ", judge=" + judge + ", status=" + status + "]";
	}
	public CustomService(int cid, String password, String images, int gender, int age, int serivcNnum,
			String mailaddress, String telno, Date regTime, double judge, int status) {
		super();
		this.cid = cid;
		this.password = password;
		this.images = images;
		this.gender = gender;
		this.age = age;
		this.serivcNnum = serivcNnum;
		this.mailaddress = mailaddress;
		this.telno = telno;
		this.regTime = regTime;
		this.judge = judge;
		this.status = status;
	}
	public CustomService() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

}
