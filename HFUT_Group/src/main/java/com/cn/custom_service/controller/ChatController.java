package com.cn.custom_service.controller;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.cn.custom_service.pojo.Chat;
import com.cn.custom_service.pojo.TempChat;
import com.cn.custom_service.service.csService;

@Controller
public class ChatController {
	@Resource
	csService csServ;
	
	@RequestMapping("/saveChat")
	@ResponseBody
	public Chat saveChat(@RequestBody TempChat tempchat){
		System.out.println(tempchat);
		Chat chat = new Chat();
		if(tempchat.getContent()==null||tempchat.getContent()==""){
			chat = new Chat(-1,0,0,0,tempchat.getImgUrl(),new Date(System.currentTimeMillis()),0,0);
		}else{chat = new Chat(-1,0,0,0,tempchat.getContent(),new Date(System.currentTimeMillis()),0,0);}
		
		int id1 = Integer.parseInt(tempchat.getFrom());
		int id2 = Integer.parseInt(tempchat.getTo());
		if(id1<id2){
			chat.setCid(id1);
			chat.setUid(id2);
			chat.setDir(0);//0代表从用户发送到客服
		}
		else{
			chat.setCid(id2);
			chat.setUid(id1);
			chat.setDir(1);
		}
		System.out.println(chat);
		csServ.saveChat(chat);
		return chat;
	}
	@RequestMapping("/showHistoryMes")
	@ResponseBody
	public List<Chat> showHistoryMes(@RequestBody Chat chat){
		int uid = chat.getUid();
		int cid = chat.getCid();
		int number = chat.getId()+10; //与id并无关系，传过来的是显示消息条数
		List<Chat> chatlist = new ArrayList<Chat>();
		chatlist = csServ.getChatlist(uid,cid,number);
		return chatlist;
	}
}
