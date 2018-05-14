package com.cn.custom_service.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.cn.custom_service.dao.CustomerQueueMapper;
import com.cn.custom_service.pojo.CustomerQueue;
import com.cn.custom_service.pojo.Queue;

@Service
@Scope("singleton")
public class CustomerQueueService {
	@Resource
	CustomerQueueMapper cqm;
	
	public List<Queue> getqueue(){
		return cqm.getqueue();
	}
	public void addnumeber(int cid){
		cqm.addnumeber(cid);
	}
	public void subnumeber(int cid){
		cqm.subnumeber(cid);
	}
	public void insert(CustomerQueue customerqueue){
		System.out.println(customerqueue);
		cqm.insert(customerqueue);
	}
	public void delete(int uid){
		cqm.delete(uid);
	}
	public CustomerQueue getnumber(int uid){
		return cqm.getnumber(uid);
	}
	public void update(int cid , int number){
		cqm.update(cid, number);
	}
	public List<Queue> selectchat(int uid){
		return cqm.selectchat(uid);
	}
	public Queue getqueuebyId(int cid){
		return cqm.getqueuebyId(cid);
	}
	
}
