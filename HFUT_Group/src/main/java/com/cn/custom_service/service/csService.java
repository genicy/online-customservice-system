package com.cn.custom_service.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.cn.custom_service.dao.csServiceMapper;
import com.cn.custom_service.pojo.Chat;
import com.cn.custom_service.pojo.Cs;
import com.cn.custom_service.pojo.csStatus;

//ÑôÐ÷³Ç
@Service
@Scope
public class csService {
	@Resource
	csServiceMapper csSerMapper;
	public Cs getInfo(int i)
	{
		return csSerMapper.getInfo(i);
	}
	public void setImg(String img,int cid)
	{
		csSerMapper.setImg(img, cid);
	}
	public Cs checkPwd(int cid, String password) {
		return csSerMapper.checkPwd(cid,password);
	}
	public void setNewPwd(int cid, String password) {
		csSerMapper.setNewPwd(cid,password);
		
	}

	public List<csStatus> selectCs() {
		return csSerMapper.selectCs();
		
	}
	public void updateNumbering(int cid,int numbering) {
		csSerMapper.updateNumbering(cid,numbering);
		
	}
	public void saveChat(Chat chat) {
		csSerMapper.saveChat(chat);
		
	}
	public List<Chat> getChatlist(int uid, int cid, int number) {
		// TODO Auto-generated method stub
		return csSerMapper.getChatlist(uid,cid,number);
	}
}
