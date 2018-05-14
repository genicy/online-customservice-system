package com.cn.custom_service.pojo;

import java.text.SimpleDateFormat;
import java.util.Date;


public class Chat {
	private int id;
	private int uid;
	private int cid;
	private int dir;
	private String cchat;
	private  Date ctime;
	private int uip;
	private int cip;
	private String formattime;
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
	public int getCid() {
		return cid;
	}
	public void setCid(int cid) {
		this.cid = cid;
	}
	public int getDir() {
		return dir;
	}
	public void setDir(int dir) {
		this.dir = dir;
	}
	public String getCchat() {
		return cchat;
	}
	public void setCchat(String cchat) {
		this.cchat = cchat;
	}
	public Date getCtime() {
		this.formattime = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(ctime);
		return ctime;
	}
	public String getFormattime() {
		return formattime;
	}
	public void setFormattime(String formattime) {
		this.formattime = formattime;
	}
	public void setCtime(Date ctime) {
		this.ctime = ctime;
	}
	public int getUip() {
		return uip;
	}
	public void setUip(int uip) {
		this.uip = uip;
	}
	public int getCip() {
		return cip;
	}
	public void setCip(int cip) {
		this.cip = cip;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Chat(int id, int uid, int cid, int dir, String cchat, Date ctime, int uip, int cip) {
		super();
		this.id = id;
		this.uid = uid;
		this.cid = cid;
		this.dir = dir;
		this.cchat = cchat;
		this.ctime = ctime;
		this.uip = uip;
		this.cip = cip;
	}
	@Override
	public String toString() {
		return "Chat [id=" + id + ", uid=" + uid + ", cid=" + cid + ", dir=" + dir + ", cchat=" + cchat + ", ctime="
				+ ctime + ", uip=" + uip + ", cip=" + cip + ", formattime=" + formattime + "]";
	}
	public Chat() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
