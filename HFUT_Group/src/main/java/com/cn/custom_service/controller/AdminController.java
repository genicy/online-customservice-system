package com.cn.custom_service.controller;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import com.cn.custom_service.pojo.Chat;
import com.cn.custom_service.pojo.CustomService;
import com.cn.custom_service.pojo.Devide;
import com.cn.custom_service.pojo.PageBean;
import com.cn.custom_service.service.impl.AdminService;
import com.cn.custom_service.service.impl.ChatService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.cn.custom_service.pojo.CsAdminStream;
import com.cn.custom_service.service.impl.CsAdminFunImpl;

@Controller
public class AdminController {
	@Resource
	AdminService adminservice;

	@Resource
	ChatService chatsercvice;
	@Resource
	private CsAdminFunImpl csadmin;

	@RequestMapping("/showservice.action")
	@ResponseBody
	public List<CustomService> showservice() {
		List<CustomService> list = adminservice.showservice();
		for (CustomService obj : list) {
			DecimalFormat df = new DecimalFormat("#.0");
			double judge = obj.getJudge() / 2;
			obj.setJudge(Double.parseDouble(df.format(judge)));
		}
		return list;
	}

	@RequestMapping("/showcustom.action/{cid}")
	@ResponseBody
	public List<Chat> showcustom(@PathVariable("cid") int cid) {
		// System.out.println("123");
		int c = cid;
		List<Chat> list = adminservice.showcustom(c);
		for (Chat obj : list) {
			// System.out.println(obj.getUid() + c);
			Chat temp = adminservice.getend(obj.getUid(), c);
			// System.out.println(adminservice.getend(2015210001, 100001));
			obj.setCtime(temp.getCtime());
		}
		return list;
	}

	@RequestMapping("/showchat.action")
	@ResponseBody
	public PageBean<Chat> JumpChat(@RequestBody Devide devide) {
		int uid = devide.getUid();
		int cid = devide.getCid();
		Integer currentPage = devide.getCurrentPage();
		Integer pageSize = devide.getPageSize();
		Integer recordCount = devide.getRecordCount();
		Integer num = devide.getNum();
		System.out.println(currentPage + "  " + num);
        if (num != null) {
			currentPage = 0;
			currentPage += num;
		}

		if (currentPage == null || currentPage <= 0)
			currentPage = 1;
		if (pageSize == null)
			pageSize = 20;
		if (recordCount == null)
			recordCount = 0;
		System.out.println(uid + "    " + cid);
		return chatsercvice.getchat(currentPage, pageSize, recordCount, uid, cid);

	}

	@RequestMapping("/getNum")
	@ResponseBody
	public CsAdminStream adminGetNum() {
		System.out.println("hello");
		Integer history = csadmin.getHistoryTolNum();
		Integer curServiceNum = csadmin.getCurNum();
		Integer curRankNum = csadmin.getCurRankNum();
		if (curRankNum == null) {
			curRankNum = 0;
		}
		CsAdminStream asStream = new CsAdminStream(history, curServiceNum, curRankNum);
		return asStream;

	}
}
