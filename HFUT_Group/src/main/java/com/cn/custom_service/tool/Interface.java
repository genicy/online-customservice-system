package com.cn.custom_service.tool;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.cn.custom_service.pojo.CsAdminStream;
import com.cn.custom_service.service.impl.CsAdminFunImpl;
@Service
public class Interface {
	@Resource
	CsAdminFunImpl csadmin;
	public String getStream(){
		Integer history = csadmin.getHistoryTolNum();
		Integer curServiceNum = csadmin.getCurNum();
		Integer curRankNum = csadmin.getCurRankNum();
		if(curRankNum == null) {
			curRankNum = 0;
		}
		CsAdminStream asStream =new CsAdminStream(history,curServiceNum,curRankNum);
		String asStreamjson = JSONObject.toJSONString(asStream);
		return asStreamjson;
	}
	

}
