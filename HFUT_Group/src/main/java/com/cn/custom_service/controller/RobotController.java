package com.cn.custom_service.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.swing.plaf.synth.SynthScrollBarUI;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cn.custom_service.dao.RobotMapper;
import com.cn.custom_service.pojo.Robot;
import com.cn.custom_service.util.RobotManage;


/**
 * @author 鍒樺竻
 * @time 2018.3.31
 * */
@Controller
public class RobotController {
	@Resource
	private RobotMapper robotSvc;
	//鏈哄櫒浜哄叧閿瘝绠＄悊
	private static RobotManage robotManage = new RobotManage();
	private static int companyId = -1;
	List<Robot> keyList;
	Map<String,String> KeyMap;
	
	/**
	 * @note 闇�瑕佹妸鍏徃id鏀惧叆session
	 * @param req
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "replyRobot")
	@ResponseBody
	public Robot replyRobot(@RequestBody Robot robot){
		String sentence = robot.getQuestion();         //鑾峰彇鐢ㄦ埛杈撳叆
		keyList = robotSvc.selectAllRobot(1);     //娴嬭瘯鏁版嵁涓簉id=1鍙峰叕鍙�
		System.out.println(sentence);
		String reply;
		for(Robot robots : keyList){
			if(!robots.getKeyword().isEmpty()){
				String key = robots.getKeyword();
				String value = robots.getAnswer();
				robotManage.getKeyWord().add(key);
				robotManage.getKeyMap().put(key, value);
			}
		}
		try{
			if(!robotManage.lookupKey(sentence).equals("0")){
				
				reply=robotManage.lookupKeyMap(robotManage.lookupKey(sentence));
				robot.setAnswer(reply);
				return robot;
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		robot.setAnswer("我不太明白您说的意思呢");
		return robot;
		
	} 
	
	@RequestMapping(value = "/selectAllRobot")
	@ResponseBody
	public List<Robot> selectAllRobot(@RequestBody Robot robot){
		int rid = robot.getRid();
		List<Robot> robotList=robotSvc.selectAllRobot(rid);
		return robotList;
	}
	@RequestMapping(value = "/addRobot")
	@ResponseBody
	public List<Robot> insertRobot(@RequestBody Robot robot){
		String keyword = robot.getKeyword();
		String answer = robot.getAnswer();
		int rid = robot.getRid();
		Robot robotTemp = new Robot();
		robotTemp.setRid(rid);     //测试数据为1号公司
		robotTemp.setKeyword(keyword);
		robotTemp.setAnswer(answer);
		robotTemp.setStatus(0);      //默认不开启
		robotSvc.insertRobot(robotTemp);
		List<Robot> robotList=robotSvc.selectAllRobot(rid);
		return robotList;
	}
	
	@RequestMapping(value = "/deleteRobot.action")
	@ResponseBody
	public List<Robot> deleteRobot(@RequestBody Robot robot){
		int id = robot.getId();
		int rid = robot.getRid();
		robotSvc.deleteRobot(id);
		List<Robot> robotList = robotSvc.selectAllRobot(rid);
		return robotList;
	}
	
	@RequestMapping(value = "/setRobotStatus")
	@ResponseBody
	public List<Robot> setRobotStatus(@RequestBody Robot robot){
		robotSvc.setRobotStatus(robot);
		List<Robot> robotList = robotSvc.selectAllRobot(robot.getRid());
		return robotList;
	}
	
	@RequestMapping(value = "/selectRobotStatus")
	@ResponseBody
	public Robot selectRobotStatus(@RequestBody Robot robot){
		int rid = robot.getRid();
		int status = robotSvc.selectRobotStatus(rid);
		Robot robots =new Robot();
		robots.setStatus(status);
		return robots;
	}
	
	@RequestMapping(value = "/updateKAndA")
	@ResponseBody
	public List<Robot> updateKAndA(@RequestBody Robot robot){
		robotSvc.updateRobot(robot);
		return robotSvc.selectAllRobot(robot.getRid());
	}
}
