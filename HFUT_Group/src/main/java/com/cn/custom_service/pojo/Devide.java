package com.cn.custom_service.pojo;

public class Devide {
	private int uid;
	private int cid;
	private Integer currentPage;
	private Integer pageSize;
	private Integer recordCount;
	private Integer num;
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
	public Integer getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	public Integer getRecordCount() {
		return recordCount;
	}
	public void setRecordCount(Integer recordCount) {
		this.recordCount = recordCount;
	}
	public Integer getNum() {
		return num;
	}
	public void setNum(Integer num) {
		this.num = num;
	}
	@Override
	public String toString() {
		return "Devide [uid=" + uid + ", cid=" + cid + ", currentPage=" + currentPage + ", pageSize=" + pageSize
				+ ", recordCount=" + recordCount + ", num=" + num + "]";
	}
	public Devide(int uid, int cid, Integer currentPage, Integer pageSize, Integer recordCount, Integer num) {
		super();
		this.uid = uid;
		this.cid = cid;
		this.currentPage = currentPage;
		this.pageSize = pageSize;
		this.recordCount = recordCount;
		this.num = num;
	}
	public Devide() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
