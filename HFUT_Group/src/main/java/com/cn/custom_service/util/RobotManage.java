package com.cn.custom_service.util;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * 
 * @author 刘帅
 * @time 2018.4.7
<<<<<<< HEAD
 * @note  用户关键词搜索管�?
=======
 * @note  用户关键词搜索管理
>>>>>>> e4e7cfac1ae5c0b618740c84384434ef17c4b17e
 */
public class RobotManage {
	private static Map<String,String> keyMap = new HashMap();
	private static List<String> keyWord = new LinkedList();
	public RobotManage() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public static Map<String, String> getKeyMap() {
		return keyMap;
	}
	public static void setKeyMap(HashMap<String, String> keyMap) {
		RobotManage.keyMap = keyMap;
	}
	public static List<String> getKeyWord() {
		return keyWord;
	}
	public static void setKeyWord(List<String> keyWord) {
		RobotManage.keyWord = keyWord;
	}
	
	
	/**
	 * 
	 * @param sentence
	 * @return 如果包含关键词，返回关键词，如果没有返回"0"
	 */
	public String lookupKey(String sentence){
		for(String s : keyWord){
			if(sentence.contains(s)){
				return s;
			}
		}
		return "0";
	}
	/**
	 * 
	 * @param key
<<<<<<< HEAD
	 * @return   返回查找关键�?
=======
	 * @return   返回查找关键词
>>>>>>> e4e7cfac1ae5c0b618740c84384434ef17c4b17e
	 */
	public String lookupKeyMap(String key){
		if(!key.isEmpty()){
			return keyMap.get(key);
		}
		else{
			return "很抱歉，没懂您的意思，你能再说详细一点吗？QAQ";
		}
	}
}
