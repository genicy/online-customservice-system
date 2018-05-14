package com.cn.custom_service.dao;

import com.cn.custom_service.pojo.csStatus;

/**
 * @author 刘帅
 * @time 2018.3.31
 * */
public interface csStatusMapper {
	public void updataServiceStatus(csStatus cs);
	
	public csStatus selectStatus(int id);
}
