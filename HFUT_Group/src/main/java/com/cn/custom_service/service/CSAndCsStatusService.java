package com.cn.custom_service.service;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.cn.custom_service.dao.CSAndCsStatusMapper;
import com.cn.custom_service.pojo.CustomserviceAndCsStatus;
/**
 * 
 * @author 刘帅
 *@time 2018.4.9
 */
@Service
@Scope
public class CSAndCsStatusService {
	@Resource
	private CSAndCsStatusMapper csandcsstatusmapper;
	
	public CustomserviceAndCsStatus selectInforById(int cid){
		return csandcsstatusmapper.selectInforById(cid);
	}
	
	//修改电话、在线状态、接待人数
	public void updateInfor(CustomserviceAndCsStatus cacs){
		csandcsstatusmapper.updateTelno(cacs);
		csandcsstatusmapper.updateServiceStatusAndMaxNum(cacs);
	}
}
