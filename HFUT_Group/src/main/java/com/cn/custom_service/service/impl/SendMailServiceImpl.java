package com.cn.custom_service.service.impl;
import javax.annotation.Resource;  
import javax.mail.MessagingException;  
import javax.mail.internet.MimeMessage;  
import org.slf4j.Logger;  
import org.slf4j.LoggerFactory;  
import org.springframework.mail.SimpleMailMessage;  
import org.springframework.mail.javamail.JavaMailSender;  
import org.springframework.mail.javamail.MimeMessageHelper;  
import org.springframework.stereotype.Service;

import com.cn.custom_service.service.SendMailService;  
  
/** 
 * @Description:鍙戦偖浠� 
 * @author: fengjk 
 * @time:2017骞�4鏈�20鏃� 涓嬪崍7:42:35 
 */  
  
@Service  
public class SendMailServiceImpl implements SendMailService{  
  
    private static final Logger logger = LoggerFactory.getLogger(SendMailServiceImpl.class);  
  
    @Resource  
    JavaMailSender mailSender;  
    @Resource  
    SimpleMailMessage simpleMailMessage;  
  

    @Override  
    public void sendSimpleMail(String subject,String content,String toMail) {  
        simpleMailMessage.setSubject(subject);  
        simpleMailMessage.setText(content);  
        simpleMailMessage.setTo(toMail);  
        mailSender.send(simpleMailMessage);  
        logger.info("发送成功");   
    }  
      
    public void sendHtmlMail(String subject, String content, String uri, String telno, String duty, String toMail) {  
        MimeMessage mailMessage = mailSender.createMimeMessage();   
        MimeMessageHelper messageHelper = new MimeMessageHelper(mailMessage);   
        try {  
            messageHelper.setTo(toMail);  
            messageHelper.setSubject(subject);   
            messageHelper.setFrom("18755117863@163.com");
            messageHelper.setText("<html><head></head><body><h1>"+content+"</h1>"
            		+"<a href='"+ uri +"'target='_blank'>"+ uri +"</a></br>"+ "<h3>负责人"+ duty +"</h3>"+ "<h3>联系电话"+ telno +"</h3></body></html>",true);  
            mailSender.send(mailMessage);   
            logger.info("发送成功");   
        } catch (MessagingException e) {  
            e.printStackTrace();  
        }   
    }  
      
   
}  