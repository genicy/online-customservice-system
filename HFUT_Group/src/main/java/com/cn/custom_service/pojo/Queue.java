package com.cn.custom_service.pojo;

public class Queue {
	private int cid;
	private int number;
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
		return "Queue [cid=" + cid + ", number=" + number + "]";
	}
	public Queue(int cid, int number) {
		super();
		this.cid = cid;
		this.number = number;
	}
	public Queue() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
