package com.cn.custom_service.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cn.custom_service.dao.CsAdminDao;
import com.cn.custom_service.dao.CustomerDao;
import com.cn.custom_service.pojo.Admin;
import com.cn.custom_service.pojo.Customer;
import com.cn.custom_service.service.CustomerService;

/**

 *  @author：夏震
 *  @time : 2018.4.13 
 *
 **/

@Service("customerService")
public class CustomerServiceImpl implements CustomerService{
@Resource
private CustomerDao customerDao;
@Resource
private CsAdminDao adminDao;
	@Override
	public Customer CustomerLogin(String mailaddress, String password) {
		
		
		return customerDao.CustomerLogin(mailaddress, password);
	}
	public void CustomerRegister(Customer customer) {
		
		 
		 customerDao.CustomerRegister(customer); 
	}
	public Integer getCSByMailaddress(String mailaddress){
		
		
		return customerDao.getCSByMailaddress(mailaddress);
		
	}
	@Override
	public Admin getAdminPasswordByMail(String mail, String password) {
		// TODO Auto-generated method stub
		return adminDao.customerAdminLogin(mail, password);
	}
	@Override
	public void customerStatus(Integer cid, int servicestatus) {
		// TODO Auto-generated method stub
		customerDao.customerStatus(cid, servicestatus);
	}
	@Override
	public void updateStatus(Integer cid) {
		customerDao.updateStatus(cid);
		
	}
	@Override
	public void activeStatus(String mailaddress) {
		customerDao.activeStatus(mailaddress);
		
	}
	@Override
	public Integer getStatusByMailaddress(String mailaddress) {
		// TODO Auto-generated method stub
		return customerDao.getStatusByMailaddress(mailaddress);
	}





}
