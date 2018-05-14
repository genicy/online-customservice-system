package com.cn.custom_service.util;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.imageio.ImageIO;
/**
 * 
 * @author 刘帅
 * @time 2018.4.22
 * @snapShot()返回截图图片路径
 *
 */
public class CameraTest {
	    private String filePreStr;  //文件名
	    private String defName = "D:\\HFUT_Group1\\firstVue\\static\\";  
	    Date day=new Date();    
	    SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss"); 
	    private String imageFormat;  // 图像文件的格式
	    private String defaultImageFormat = "jpg"; //截图后缀
	    Dimension d = Toolkit.getDefaultToolkit().getScreenSize(); 
	    
	    public CameraTest() {
	        filePreStr = defName;
	        imageFormat = defaultImageFormat;
	    }
	 
	    public CameraTest(String s, String format) {
	        filePreStr = s;
	        imageFormat = format;
	    }
	 
	    public String snapShot() {
	    	String name = filePreStr + df.format(day) + "." + imageFormat; //文件名
	    	try {
	            BufferedImage screenshot = (new Robot()).createScreenCapture(new Rectangle(0, 0, (int) d.getWidth(), (int) d.getHeight()));
	            File f = new File(name);
	            if(!f.exists()){
	            	f.mkdirs();
	            } 
	            ImageIO.write(screenshot, imageFormat, f);
	        } catch (Exception ex) {
	            System.out.println(ex);
	        }
			return df.format(day) + "." + imageFormat;
	    }
}
