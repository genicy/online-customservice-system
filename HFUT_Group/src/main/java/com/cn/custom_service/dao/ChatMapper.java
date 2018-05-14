package com.cn.custom_service.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.cn.custom_service.pojo.Chat;

public interface ChatMapper {
	public int getcount(int uid , int cid);
	public List<Chat> getchat(@Param("start")int start,@Param("length")int length ,int uid , int cid);
}
