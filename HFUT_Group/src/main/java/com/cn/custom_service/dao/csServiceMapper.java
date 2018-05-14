package com.cn.custom_service.dao;

import java.util.List;

import com.cn.custom_service.pojo.Chat;
import com.cn.custom_service.pojo.Cs;
import com.cn.custom_service.pojo.csStatus;


//ÑôÐ÷³Ç
public interface csServiceMapper {
	public Cs getInfo(int i);
	public void setImg(String img,int cid);
	public Cs checkPwd(int cid, String password);
	public void setNewPwd(int cid, String password);
	public List<csStatus> selectCs();
	public void updateNumbering(int cid,int numbering);
	public void saveChat(Chat chat);
	public List<Chat> getChatlist(int uid, int cid, int number);
}
