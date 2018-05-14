package com.cn.custom_service.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cn.custom_service.pojo.CustomserviceAndCsStatus;
import com.cn.custom_service.service.CSAndCsStatusService;
@Controller
public class CSAndCsStatusController {
	@Resource
	private CSAndCsStatusService csAcsSvc;
	
	@RequestMapping(value = "/updateInfor")
	@ResponseBody 
	public CustomserviceAndCsStatus updateInfor(@RequestBody CustomserviceAndCsStatus CSACsStatus){
		int cid = CSACsStatus.getCid();
		String telno = CSACsStatus.getTelno();
		int status = CSACsStatus.getServiceStatus();
		int maxNum = CSACsStatus.getMaxNum();
		CustomserviceAndCsStatus updateCS = new CustomserviceAndCsStatus();
		updateCS.setCid(cid);
		updateCS.setTelno(telno);
		updateCS.setServiceStatus(status);
		updateCS.setMaxNum(maxNum);
		csAcsSvc.updateInfor(updateCS);
		CustomserviceAndCsStatus cs = csAcsSvc.selectInforById(cid);
		return cs;
	}
	@RequestMapping(value = "/selectInfo")
	@ResponseBody 
	public CustomserviceAndCsStatus selectInfo(@RequestBody CustomserviceAndCsStatus CSACsStatus){
		int cid = CSACsStatus.getCid();
		CustomserviceAndCsStatus updateCS = new CustomserviceAndCsStatus();
		updateCS.setCid(cid);
		System.out.println(updateCS);
		CustomserviceAndCsStatus cs = csAcsSvc.selectInforById(cid);
		return cs;
	}
}
