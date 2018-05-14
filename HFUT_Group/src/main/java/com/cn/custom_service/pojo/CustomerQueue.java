package com.cn.custom_service.pojo;

public  class CustomerQueue {
	
	private int uid;
	private int cid;
	private int number;
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
	public int getCid() {
		return cid;
	}
	public void setCid(int cid) {
		this.cid = cid;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	@Override
	public String toString() {
		return "CustomerQueue [uid=" + uid + ", cid=" + cid + ", number=" + number + "]";
	}
	public CustomerQueue() {
		super();
		// TODO Auto-generated constructor stub
	}
	public CustomerQueue(int uid, int cid, int number) {
		super();
		this.uid = uid;
		this.cid = cid;
		this.number = number;
	}
	
	
}
