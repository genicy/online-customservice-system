package com.cn.custom_service.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cn.custom_service.dao.csStatusMapper;
import com.cn.custom_service.pojo.csStatus;
/**
 * @author 刘帅
 * @time 2018.3.31
 * */
@Controller
public class csStatusController {
	@Resource
	private csStatusMapper csStatusSvc;
	@RequestMapping(value = "selectStatus")
	public String selectStatus(HttpServletRequest req,HttpSession session){
		csStatus cs = csStatusSvc.selectStatus(100001);
		session.setAttribute("cs", cs);
		return "test";
	}
	@RequestMapping(value = "updataStatus")
	public String updataStatus(HttpServletRequest req,HttpSession session){
		csStatus cs = csStatusSvc.selectStatus(100001);
		cs.setServicestatus(2);
		csStatusSvc.updataServiceStatus(cs);
		return "index";
	}
	@RequestMapping(value = "updataStatus1")
	public String updataStatus1(HttpServletRequest req,HttpSession session){
		csStatus cs = csStatusSvc.selectStatus(100001);
		cs.setServicestatus(111);
		csStatusSvc.updataServiceStatus(cs);
		return "index";
	}
	
}
