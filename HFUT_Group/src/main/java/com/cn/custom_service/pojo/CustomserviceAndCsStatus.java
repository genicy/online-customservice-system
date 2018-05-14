package com.cn.custom_service.pojo;

public class CustomserviceAndCsStatus {
	private int cid;
	private String telno;
	private int serviceStatus;
	private int maxNum;
	public CustomserviceAndCsStatus() {
		super();
		// TODO Auto-generated constructor stub
	}
	public CustomserviceAndCsStatus(int cid, String telno, int serviceStatus, int maxNum) {
		super();
		this.cid = cid;
		this.telno = telno;
		this.serviceStatus = serviceStatus;
		this.maxNum = maxNum;
	}
	public int getCid() {
		return cid;
	}
	public void setCid(int cid) {
		this.cid = cid;
	}
	public String getTelno() {
		return telno;
	}
	public void setTelno(String telno) {
		this.telno = telno;
	}
	public int getServiceStatus() {
		return serviceStatus;
	}
	public void setServiceStatus(int serviceStatus) {
		this.serviceStatus = serviceStatus;
	}
	public int getMaxNum() {
		return maxNum;
	}
	public void setMaxNum(int maxNum) {
		this.maxNum = maxNum;
	}
	@Override
	public String toString() {
		return "CustomserviceAndCsStatus [cid=" + cid + ", telno=" + telno + ", serviceStatus=" + serviceStatus
				+ ", maxNum=" + maxNum + "]";
	}
	
	
}
