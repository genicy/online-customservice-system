package com.cn.custom_service.service;

import com.cn.custom_service.pojo.Admin;
import com.cn.custom_service.pojo.Customer;

public interface CustomerService {
	public Customer CustomerLogin(String mailaddress, String password);
	public void CustomerRegister(Customer customer);
	public Integer getCSByMailaddress(String mailaddress);
	public Admin getAdminPasswordByMail(String mail,String password);
	public void customerStatus(Integer cid, int servicestatus);
	public void updateStatus(Integer cid);
	public void activeStatus(String mailaddress);
	public Integer getStatusByMailaddress(String mailaddress);
}

/**

 *  @author閿涙艾顦撮棁锟�
 *  @time : 2018.4.14
 *
 **/