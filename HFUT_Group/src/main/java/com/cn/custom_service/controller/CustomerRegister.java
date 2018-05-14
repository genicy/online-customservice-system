package com.cn.custom_service.controller;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.regex.Pattern;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.cn.custom_service.pojo.AddCustomer;
import com.cn.custom_service.pojo.Customer;
import com.cn.custom_service.service.impl.CustomerServiceImpl;
import com.cn.custom_service.service.impl.SendMailServiceImpl;
import com.cn.custom_service.tool.AESOperator;

/**
 * 
 * @author锛氬闇�
 * 
 * @time : 2018.4.20
 *
 **/

@Controller
public class CustomerRegister {
	@Resource
	CustomerServiceImpl customerImpl;
	@Resource
	SendMailServiceImpl sendmailImpl;
	Pattern mail= Pattern.compile("[\\w[.-]]+@[\\w[.-]]+\\.[\\w]+");// 楠岃瘉閭鏍煎紡
	Pattern tel = Pattern.compile("^1[3|4|5|7|8][0-9]\\d{4,8}$");
	@RequestMapping("/register.action")
	@ResponseBody
	public String customerRegister(@RequestBody AddCustomer acustomer) {
		System.out.println(acustomer.getMailaddress());
		if(!mail.matcher(acustomer.getMailaddress()).matches() 
				|| acustomer.getMailaddress() == null ) {
			return "邮件为空或格式错误";
		}
		if(!tel.matcher(acustomer.getTelno()).matches()
				|| acustomer.getTelno() == null) {
			return "电话号码为空或格式错误";
		}
		if(acustomer.getDuty() == null) {
			return "负责人为空";
		}
		if(customerImpl.getCSByMailaddress(acustomer.getMailaddress()) != null){
			return "该用户已存在";
		}else {
			String subject = "激活邮件";
			String content = "点击此链接激活账号";
			String uri = "http://localhost:8080/HFUT_Group1/active/"+ AESOperator.getInstance().encrypt(acustomer.getMailaddress());
			System.out.println(uri);
			sendmailImpl.sendHtmlMail(subject, content, uri, acustomer.getTelno(), acustomer.getDuty(), acustomer.getMailaddress());
			Customer customer = new Customer();
			customer.setPassword("123456");
			customer.setImages("");
			customer.setGender(0);
			customer.setAge(20);
			customer.setMailaddress(acustomer.getMailaddress());
			customer.setTelno("");
			customerImpl.CustomerRegister(customer);
			Integer cid = customerImpl.getCSByMailaddress(acustomer.getMailaddress());
			customerImpl.customerStatus(cid, 0);
			return "ok";
		}
		
			
	}
	@RequestMapping("/active/{mailaddress}")
	public void activeaccount(@PathVariable("mailaddress") String mailaddress, HttpServletResponse response) throws IOException {
		System.out.println(mailaddress);
		String trueMail = AESOperator.getInstance().decrypt(mailaddress);
		System.out.println(trueMail);
		response.setContentType("text/html;charset=UTF8");
		PrintWriter out = response.getWriter();
		if(customerImpl.getCSByMailaddress(trueMail) != null) {
			if(customerImpl.getStatusByMailaddress(trueMail) == 0){
				out.println("<html><body><h2>账号已激活，若无法登录请联系负责人</h2></body></html>");
			}else {
				customerImpl.activeStatus(trueMail);
				out.println("<html>"+
				"<head>"+
				"</head>"+
				"<body><h2>激活成功<span id='time'></span>秒后将进入登录页面</h2><h2>请自行修改密码与个人信息，初始密码为123456</h2></body>"+
				"<script type='text/javascript'>"+
				"var id = document.getElementById('time')\n"+
				"var time = 5\n"+
				"setInterval(()=>{"+
				"if(time == 0){window.location='http://localhost:8081'}\n"+
				"else{id.innerHTML = time\n"+
				"time--"+ 
				"}"+
				"},1000)"+
				"</script>"+
				"</html>");
			}
		}else {
			out.println("<html><body><h2>婵�娲诲け璐ワ紝璇疯仈绯昏礋璐ｄ汉杩涜璇㈤棶</h2></body></html>");
		}
	}
}
