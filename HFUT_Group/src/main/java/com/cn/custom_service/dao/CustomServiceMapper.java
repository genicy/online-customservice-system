package com.cn.custom_service.dao;

import com.cn.custom_service.pojo.CustomService;

public interface CustomServiceMapper {
	public CustomService getcs(int cid);
	public void updatejudge(double judge , int cid);
	public void updateserivcNnum(int serivcNnum , int cid);
	public void updateNumbering(int cid, int numbering);
}
