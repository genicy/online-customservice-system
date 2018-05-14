package com.cn.custom_service.service;

public interface SendMailService {
	public void sendSimpleMail(String subject, String content, String tomail);
	public void sendHtmlMail(String subject, String content, String uri, String telno, String duty, String toMail);
}
