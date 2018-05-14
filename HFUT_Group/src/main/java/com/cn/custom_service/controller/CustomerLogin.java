package com.cn.custom_service.controller;

import java.util.regex.Pattern;
import javax.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.cn.custom_service.pojo.Admin;
import com.cn.custom_service.pojo.Customer;
import com.cn.custom_service.service.impl.CustomerServiceImpl;


@Controller

public class CustomerLogin {
	Pattern pattern = Pattern.compile("[\\w[.-]]+@[\\w[.-]]+\\.[\\w]+");//閭鏍峰紡瑙勫畾
	@Resource
	private CustomerServiceImpl Icustomer;
	@RequestMapping("/CustomerLogin")
	@ResponseBody
	public Customer customerLogin(@RequestBody Customer customer){
		Customer customer1 = new Customer();
		Admin admin = null;
		String mailaddress = customer.getMailaddress();
		String password = customer.getPassword();
		String role = customer.getRole();
        if(!pattern.matcher(mailaddress).matches()){
		customer1.setStatus1(3);
		customer1.setErrorMessage("邮箱地址有误");
		return customer1;
	}
	try {
		if(role.equals("1")){
			customer1 = Icustomer.CustomerLogin(mailaddress, password);
			if(customer1.getStatus() == 0){
				customer1.setStatus1(0);
				Icustomer.updateStatus(customer1.getStatus());
			}else {
				customer1.setStatus1(2);
				customer1.setErrorMessage("该账户尚未激活或已被禁用");
			}
			customer1.setRole("1");
			return customer1;
		}else{
			admin = Icustomer.getAdminPasswordByMail(mailaddress,password);
			System.out.println(admin);
			customer1.setCid(admin.getAid());
			customer1.setStatus1(0);
			customer1.setRole("2");
			return customer1;
		}
		
	} catch (Exception e) {
			customer1 = new Customer();
			customer1.setStatus1(4);
			customer1.setErrorMessage("邮箱或密码错误");
			System.out.println(e.getMessage());
			return customer1;
	}	
}
	
}