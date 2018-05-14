package com.cn.custom_service.dao;

import com.cn.custom_service.pojo.CustomserviceAndCsStatus;

public interface CSAndCsStatusMapper {
	public CustomserviceAndCsStatus selectInforById(int cid);
	
	public void updateTelno(CustomserviceAndCsStatus cacs);
	
	public void updateServiceStatusAndMaxNum(CustomserviceAndCsStatus cacs);
}
