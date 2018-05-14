package com.cn.custom_service.dao;

import java.util.List;

import com.cn.custom_service.pojo.CustomerQueue;
import com.cn.custom_service.pojo.Queue;

public interface CustomerQueueMapper {
	public List<Queue> getqueue();
	public Queue getqueuebyId(int cid);
	public void addnumeber(int cid);
	public void subnumeber(int cid);
	public void insert(CustomerQueue customerqueue);
	public void delete(int uid);
	public void update(int cid , int number);
	public CustomerQueue getnumber(int uid);
	public List<Queue> selectchat(int uid);
}
