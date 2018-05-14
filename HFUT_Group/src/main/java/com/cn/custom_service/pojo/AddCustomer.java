package com.cn.custom_service.pojo;

public class AddCustomer {
	private String mailaddress;
	private String telno;
	private String duty;
	public AddCustomer() {
		super();
		// TODO Auto-generated constructor stub
	}
	public AddCustomer(String mailaddress, String telno, String duty) {
		super();
		this.mailaddress = mailaddress;
		this.telno = telno;
		this.duty = duty;
	}
	@Override
	public String toString() {
		return "AddCustomer [mailaddress=" + mailaddress + ", telno=" + telno + ", duty=" + duty + "]";
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
	public String getDuty() {
		return duty;
	}
	public void setDuty(String duty) {
		this.duty = duty;
	}
	
	
}
