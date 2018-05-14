package com.cn.custom_service.pojo;

public class CsAdminStream {
	Integer totalNum;
	Integer currNum;
	Integer curRankNum;
	public CsAdminStream() {
		super();
		// TODO Auto-generated constructor stub
	}
	public CsAdminStream(Integer totalNum, Integer currNum, Integer curRankNum) {
		super();
		this.totalNum = totalNum;
		this.currNum = currNum;
		this.curRankNum = curRankNum;
	}
	public Integer getTotalNum() {
		return totalNum;
	}
	public void setTotalNum(Integer totalNum) {
		this.totalNum = totalNum;
	}
	public Integer getCurrNum() {
		return currNum;
	}
	public void setCurrNum(Integer currNum) {
		this.currNum = currNum;
	}
	public Integer getCurRankNum() {
		return curRankNum;
	}
	public void setCurRankNum(Integer curRankNum) {
		this.curRankNum = curRankNum;
	}
	@Override
	public String toString() {
		return "CsAdminStream [totalNum=" + totalNum + ", currNum=" + currNum + ", curRankNum=" + curRankNum + "]";
	}

	
	
}
