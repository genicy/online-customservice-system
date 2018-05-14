package com.cn.custom_service.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.cn.custom_service.dao.AdminMapper;
import com.cn.custom_service.pojo.Chat;
import com.cn.custom_service.pojo.CustomService;

@Service
@Scope("singleton")
public class AdminService {
	@Resource
	AdminMapper adminmapper;
	
	public List<CustomService> showservice(){
		return adminmapper.showservice();
	}
	
	public List<Chat> showcustom(int cid){
		return adminmapper.showcustom(cid);
	}

	public List<CustomService> showservicebynum() {
		return adminmapper.showservicebynum();
	}

	public List<CustomService> showservicebyjudge() {
		return adminmapper.showservicebyjudge();
	}
	public Chat getend(int uid , int cid){
		return adminmapper.getend(uid, cid);
	}
}
