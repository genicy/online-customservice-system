package com.cn.custom_service.service.impl;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.cn.custom_service.dao.CustomServiceMapper;
import com.cn.custom_service.pojo.CustomService;

@Service
@Scope("singleton")
public class CSService {
	@Resource
	private CustomServiceMapper csm;
	
	
	public CustomService getcustoms(int cid){
		return csm.getcs(cid);
	}
	public void updatejudge(double judge, int cid) {
		csm.updatejudge(judge , cid);
	}
	public void updateserivcNnum(int serivcNnum , int cid){
		csm.updateserivcNnum(serivcNnum , cid);
	}
	public void updateNumbering(int cid, int numbering) {
		csm.updateNumbering(cid,numbering);
	}
	
}
