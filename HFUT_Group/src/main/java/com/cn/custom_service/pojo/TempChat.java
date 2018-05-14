package com.cn.custom_service.pojo;

import java.sql.Date;

public class TempChat {
	private String content;
    private String from;
    private String to;
    private String imgUrl;
    private String time;
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getFrom() {
		return from;
	}
	public void setFrom(String from) {
		this.from = from;
	}
	public String getTo() {
		return to;
	}
	public void setTo(String to) {
		this.to = to;
	}
	public String getImgUrl() {
		return imgUrl;
	}
	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}


	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public TempChat() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "TempChat [content=" + content + ", from=" + from + ", to=" + to + ", imgUrl=" + imgUrl + ", time="
				+ time + "]";
	}
	public TempChat(String content, String from, String to, String imgUrl, String time) {
		super();
		this.content = content;
		this.from = from;
		this.to = to;
		this.imgUrl = imgUrl;
		this.time = time;
	}

    
}
