package com.cn.custom_service.pojo;

/**
 * @author 刘帅
 * @time 2018.3.31
 * */
public class csStatus {
	private int cid;
	private int servicestatus;
	private int maxNum;
	private int numbering;
	public csStatus(int cid, int servicestatus, int maxNum, int numbering) {
		super();
		this.cid = cid;
		this.servicestatus = servicestatus;
		this.maxNum = maxNum;
		this.numbering = numbering;
	}
	public csStatus() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getCid() {
		return cid;
	}
	public void setCid(int cid) {
		this.cid = cid;
	}
	public int getServicestatus() {
		return servicestatus;
	}
	public void setServicestatus(int servicestatus) {
		this.servicestatus = servicestatus;
	}
	public int getMaxNum() {
		return maxNum;
	}
	public void setMaxNum(int maxNum) {
		this.maxNum = maxNum;
	}
	public int getNumbering() {
		return numbering;
	}
	public void setNumbering(int numbering) {
		this.numbering = numbering;
	}
	@Override
	public String toString() {
		return "csStatus [cid=" + cid + ", servicestatus=" + servicestatus + ", maxNum=" + maxNum + ", numbering="
				+ numbering + "]";
	}
	
}
