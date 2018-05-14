package com.cn.custom_service.pojo;

/**
 * 
 * @author 刘帅
 * @time 2018.2.6
 *
 */
public class Robot {
	private int id;
	private int rid;
	private String keyword;
	private String answer;

	private int status;
	private String updateTime;
	private String question;
	public Robot() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}
	public Robot(int id, int rid, String keyword, String answer, int status, String updateTime) {
		super();
		this.id = id;
		this.rid = rid;
		this.keyword = keyword;
		this.answer = answer;
		this.status = status;
		this.updateTime = updateTime;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getRid() {
		return rid;
	}
	public void setRid(int rid) {
		this.rid = rid;
	}
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}
	@Override
	public String toString() {
		return "Robot [id=" + id + ", rid=" + rid + ", keyword=" + keyword + ", answer=" + answer + ", status=" + status
				+ ", updateTime=" + updateTime + "]";
	}
	
	
}
