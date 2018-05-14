package com.cn.custom_service.service.impl;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.cn.custom_service.dao.CsAdminDao;
import com.cn.custom_service.service.CsAdminFunService;
@Service
@Scope
public class CsAdminFunImpl implements CsAdminFunService {
	@Resource
	private CsAdminDao csadmindao;

	@Override
	public Integer getHistoryTolNum() {
		// TODO Auto-generated method stub
		return csadmindao.getHistoryTolNum();
	}

	@Override
	public Integer getCurNum() {
		// TODO Auto-generated method stub
		return csadmindao.getCurNum();
	}

	@Override
	public Integer getCurRankNum() {
		// TODO Auto-generated method stub
		return csadmindao.getCurRankNum();
	}

	
	}

	
