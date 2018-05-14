package com.cn.custom_service.dao;

import com.cn.custom_service.pojo.Admin;

public interface CsAdminDao {
	public Integer getHistoryTolNum();
	public Integer getCurNum();
	public Integer getCurRankNum();
	public Admin customerAdminLogin(String mail,String password);
}
