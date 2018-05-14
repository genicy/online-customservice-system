package com.cn.custom_service.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.cn.custom_service.pojo.Chat;
import com.cn.custom_service.pojo.CustomerQueue;
import com.cn.custom_service.pojo.Queue;
import com.cn.custom_service.pojo.Var;
import com.cn.custom_service.service.impl.CustomerQueueService;

@Controller
public class CustomerQueueController {
	@Resource
	CustomerQueueService cqs;
	
	Var v =new Var();

	@RequestMapping("/join.action") // 进入排队
	@ResponseBody
	public Map<String, Integer> join(@RequestBody CustomerQueue cq) {
		Map<String, Integer> result = new HashMap<String, Integer>();
		List<Queue> list = cqs.getqueue();
		int[] a = new int[list.size()];
		int[] b = new int[list.size()];
		if(Var.getReturncustomer() == 1){
			// 查看是否回头客
			List<Queue> bool = cqs.selectchat(cq.getUid());
			if (bool.size() > 1) {
				result = join2(cq.getUid(), bool);
				System.out.println("123456798s");
				return result;
			} else if (bool.size() == 1) {
				for (Queue que : bool) {
					Queue que2 = cqs.getqueuebyId(que.getCid());
					cq.setNumber(que2.getNumber() + 1);
					cq.setCid(que.getCid());
					cqs.insert(cq);
					cqs.addnumeber(cq.getCid());
					
					result.put("person", que2.getNumber());
					return result;
				}
			}
		}
		//不是回头客
		int i = 0;
		int min = 0;
		for (Queue queue : list) {
			a[i] = queue.getCid();
			b[i] = queue.getNumber();
			if (b[i] == 0) {
				cq.setNumber(1);
				cq.setCid(a[i]);
				cqs.insert(cq);
				cqs.addnumeber(cq.getCid());
				result.put("person", b[i]);
				return result;
			}
			if (i > 0 && i < list.size() && b[i] < b[min]) {
				System.out.println("123465");
				min = i;
			}
			i++;
		}
		cq.setNumber(b[min] + 1);
		cq.setCid(a[min]);
		cqs.insert(cq);
		cqs.addnumeber(cq.getCid());
		result.put("person", b[min]);
		return result;

	}

	public Map<String, Integer> join2(int uid, List<Queue> list) {
		Map<String, Integer> result = new HashMap<String, Integer>();
		CustomerQueue cq = new CustomerQueue();
		cq.setUid(uid);
		int[] a = new int[list.size()];
		int[] b = new int[list.size()];
		System.out.println(list);
		int i = 0;
		int min = 0;
		for (Queue que : list) {
			Queue queue = cqs.getqueuebyId(que.getCid());
			a[i] = queue.getCid();
			b[i] = queue.getNumber();
			if (b[i] == 0) {
				cq.setNumber(1);
				cq.setCid(a[i]);
				cqs.insert(cq);
				cqs.addnumeber(cq.getCid());
				result.put("person", b[min]);
				return result;
			}
			if (i > 0 && i < list.size() && b[i] < b[min]) {
				min = i;
			}
			i++;
		}
		cq.setNumber(b[min] + 1);
		cq.setCid(a[min]);
		cqs.insert(cq);
		cqs.addnumeber(cq.getCid());
		result.put("person", b[min]);
		return result;

	}
	
	@RequestMapping("/refresh.action/{uid}")
	@ResponseBody
	public Map<String, Integer> refresh(@PathVariable("uid") int uid) {
		Map<String, Integer> result = new HashMap<String, Integer>();
		CustomerQueue cq;
		cq = cqs.getnumber(uid);
		result.put("person", cq.getNumber());
		return result;
	}

	@RequestMapping("/delete.action")
	@ResponseBody
	public void delete(@RequestBody CustomerQueue cq1) {
		CustomerQueue cq;
		cq = cqs.getnumber(cq1.getUid());
		cqs.update(cq1.getCid(), cq.getNumber());
		cqs.delete(cq1.getUid());
		cqs.subnumeber(cq1.getCid());
	}
}
