package com.cn.custom_service.service;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.cn.custom_service.dao.csStatusMapper;
import com.cn.custom_service.pojo.csStatus;

/**
 * @author 刘帅
 * @time 2018.3.31
 * */
@Service
@Scope
public class csStatusService {
	@Resource
	private csStatusMapper csstatusmapper;
	
	public void updataServiceStatus(csStatus cs){
		csstatusmapper.updataServiceStatus(cs);
	}
	public csStatus selectStatus(int id){
		return csstatusmapper.selectStatus(id);
	}
}	
