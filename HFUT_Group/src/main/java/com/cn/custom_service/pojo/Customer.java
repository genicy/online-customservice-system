package com.cn.custom_service.pojo;
/**

 *  @author：夏震
 *  @time : 2018.3.31 
 *
 **/
public class Customer {
	private int cid;
	private String password;
	private String images;
	private int age;
	private int gender;
	private int serivcNnum;
	private String mailaddress;
	private String telno;
	private String regTime;
	private double judge;
	private String role;
	private int status;
	private int status1;
	private String errorMessage;
	public Customer() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Customer(int cid, String password, String images, int age, int gender, int serivcNnum, String mailaddress,
			String telno, String regTime, double judge, String role, int status, int status1, String errorMessage) {
		super();
		this.cid = cid;
		this.password = password;
		this.images = images;
		this.age = age;
		this.gender = gender;
		this.serivcNnum = serivcNnum;
		this.mailaddress = mailaddress;
		this.telno = telno;
		this.regTime = regTime;
		this.judge = judge;
		this.role = role;
		this.status = status;
		this.status1 = status1;
		this.errorMessage = errorMessage;
	}
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
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public int getGender() {
		return gender;
	}
	public void setGender(int gender) {
		this.gender = gender;
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
	public String getRegTime() {
		return regTime;
	}
	public void setRegTime(String regTime) {
		this.regTime = regTime;
	}
	public double getJudge() {
		return judge;
	}
	public void setJudge(double judge) {
		this.judge = judge;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public int getStatus1() {
		return status1;
	}
	public void setStatus1(int status1) {
		this.status1 = status1;
	}
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	@Override
	public String toString() {
		return "Customer [cid=" + cid + ", password=" + password + ", images=" + images + ", age=" + age + ", gender="
				+ gender + ", serivcNnum=" + serivcNnum + ", mailaddress=" + mailaddress + ", telno=" + telno
				+ ", regTime=" + regTime + ", judge=" + judge + ", role=" + role + ", status=" + status + ", status1="
				+ status1 + ", errorMessage=" + errorMessage + "]";
	}
	
	
}
