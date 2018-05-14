package com.cn.custom_service.dao;

import java.util.List;

import com.cn.custom_service.pojo.Chat;
import com.cn.custom_service.pojo.CustomService;

public interface AdminMapper {
	public List<CustomService> showservice();
	public List<Chat> showcustom(int cid);
	public List<CustomService> showservicebynum();
	public List<CustomService> showservicebyjudge();
	public Chat getend(int uid , int cid);
}
