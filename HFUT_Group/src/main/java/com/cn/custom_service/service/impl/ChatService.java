package com.cn.custom_service.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.cn.custom_service.dao.ChatMapper;
import com.cn.custom_service.pojo.Chat;
import com.cn.custom_service.pojo.PageBean;


@Service
@Scope("singleton")
public class ChatService {
	@Resource
	private ChatMapper chatmapper ;
	public int getcount(int uid , int cid){
		return chatmapper.getcount(uid, cid);
	}
	
	public PageBean<Chat>  getchat(Integer currentPage, Integer pageSize, Integer recordCount , int uid , int cid){
		try {
			int start=currentPage==1?0:(currentPage-1)*pageSize;
			recordCount=chatmapper.getcount(uid,cid);
			
			List<Chat> list=chatmapper.getchat(start, pageSize, uid, cid);
			PageBean<Chat> pageBean=new PageBean<Chat>(currentPage, pageSize, list, recordCount);
			return pageBean;
		} catch (Exception e) { 
			e.printStackTrace();
			return null;
		}
	}
}
