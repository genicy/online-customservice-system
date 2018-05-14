package com.cn.custom_service.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Date;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import com.cn.custom_service.pojo.Chat;
import com.cn.custom_service.pojo.Cs;
import com.cn.custom_service.pojo.TempChat;
import com.cn.custom_service.pojo.csStatus;
import com.cn.custom_service.service.csService;
import com.cn.custom_service.util.CameraTest;

//������
@Controller
public class UserController {
@Resource
csService csServ;

@RequestMapping("/getInfo")
@ResponseBody
public Cs getCsInfo(@RequestBody Cs cs)//������
{

	Cs csinfo = csServ.getInfo(cs.getCid());
        csinfo.setJudge(csinfo.getJudge()/2);
	return csinfo;
}

@RequestMapping("/checkpwd")
@ResponseBody
public Cs checkPwd(@RequestBody Cs cs)
{
	System.out.println(cs.getPassword());
	Cs csinfo = csServ.checkPwd(cs.getCid(),cs.getPassword());
	return csinfo;
}
@RequestMapping("/setNewPwd")
@ResponseBody
public Cs setNewPwd(@RequestBody Cs cs)//������
{
	
	 csServ.setNewPwd(cs.getCid(),cs.getPassword());
	 Cs csinfo = csServ.checkPwd(cs.getCid(), cs.getPassword());
	System.out.println(cs.getMailaddress());
	return cs;
}
@RequestMapping("/setImg")//������
public Cs setImg(@RequestBody Cs cs)
{
	System.out.println(213);
	csServ.setImg(cs.getImages(), cs.getCid());
	return cs;
}


@RequestMapping("/UserLogin")//�û���¼,�����������Ŀͷ����������ÿͷ�ʵ����ȥ��
@ResponseBody
public Cs selectCs(@RequestBody Cs cs)//������,����ͷ��߼�
{
	System.out.println("asdasd");
	List<csStatus> cslist = new LinkedList<csStatus>(); 
	cslist = csServ.selectCs();
	Random random = new Random();
	System.out.println(cslist.size());
	int select = random.nextInt(cslist.size());
	csStatus tochat = cslist.get(select);
	Cs retcs = csServ.getInfo(tochat.getCid());
	csServ.updateNumbering(tochat.getCid(),tochat.getNumbering()+1);
	retcs.setImages("http://localhost:8081/static/"+retcs.getImages());
	System.out.println(retcs.getImages());
	System.out.println(retcs.getCid());
	return retcs;
}
@RequestMapping("/uploadImg")//�ϴ�ͷ��
@ResponseBody
public Cs uploadImg(@RequestParam("file") MultipartFile file) throws IOException{
	System.out.println("adasas");
	String filePath = "D:\\HFUT_Group1\\HFUT_Group\\src\\main\\webapp\\image";
	String filePath2 = "D:\\HFUT_Group1\\CustomerService\\static";
	String originalFilename = file.getOriginalFilename();
	String newFilename = UUID.randomUUID()+originalFilename;
	System.out.println(newFilename);
	File targetFile = new File(filePath,newFilename);
	File targetFile2 = new File(filePath2,newFilename);
	file.transferTo(targetFile);
	file.transferTo(targetFile2);
	Cs cs = new Cs();
	cs.setImages(newFilename);

	return cs;
}
@RequestMapping("/uploadImgs")//�ͷ�����ͷ��
@ResponseBody
public Cs uploadImgs(@RequestParam("file") MultipartFile file) throws IOException{
	System.out.println("adasas");
	String filePath = "D:\\HFUT_Group1\\CustomerService\\static";
	String originalFilename = file.getOriginalFilename();
	String newFilename = UUID.randomUUID()+originalFilename;
	System.out.println(newFilename);
	File targetFile = new File(filePath,newFilename);
	file.transferTo(targetFile);
	Cs cs = new Cs();
	cs.setImages(newFilename);
	return cs;
}
@RequestMapping("/uploadImges")//�û�����ͷ��
@ResponseBody
public Cs uploadImge(@RequestParam("file") MultipartFile file) throws IOException{
	System.out.println("adasas");
	String filePath = "D:\\HFUT_Group1\\firstVue\\static";
	String originalFilename = file.getOriginalFilename();
	String newFilename = UUID.randomUUID()+originalFilename;
	System.out.println(newFilename);
	File targetFile = new File(filePath,newFilename);
	file.transferTo(targetFile);
	Cs cs = new Cs();
	cs.setImages(newFilename);
	return cs;
}
@RequestMapping("/askCut")//用户发送头像
@ResponseBody
public Cs askCut() {
	String picpath = (new CameraTest()).snapShot();
	Cs cs = new Cs();
	cs.setImages(picpath);
	return cs;
}

}
