package com.cn.custom_service.controller;

import java.io.IOException;

import javax.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.cn.custom_service.pojo.CustomService;
import com.cn.custom_service.pojo.csStatus;
import com.cn.custom_service.service.impl.CSService;
@Controller
public class CustomServiceController {
	@Resource
	CSService csservice;
	@RequestMapping("/updatejudege.action")
	@ResponseBody
	public String updatejudege(@RequestBody CustomService customservice1){
		CustomService customservice ;
		customservice = csservice.getcustoms(customservice1.getCid());
		System.out.println(customservice1.getCid());
		System.out.println(customservice1.getJudge());
		double num = 0;
		num = customservice.getJudge()*customservice.getSerivcNnum()+customservice1.getJudge()*2;
		num = num/(customservice.getSerivcNnum()+1);
		csservice.updatejudge(num , customservice1.getCid());
		int number = customservice.getSerivcNnum() + 1;
		csservice.updateserivcNnum(number , customservice1.getCid());
		return "修改成功";
	}
	@RequestMapping("/breakConn")//结束会话函数
	@ResponseBody
	public void breakConn(@RequestBody csStatus cs){
		cs.setNumbering(cs.getNumbering());
		csservice.updateNumbering(cs.getCid(),cs.getNumbering());
	}

	
}
