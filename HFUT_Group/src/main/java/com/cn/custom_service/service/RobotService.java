package com.cn.custom_service.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.cn.custom_service.dao.RobotMapper;
import com.cn.custom_service.pojo.Robot;

/**
 * 
 * @author 刘帅
 * @time 2018.4.6
 * @time 2018.4.20
 */
@Service
@Scope
public class RobotService {
	@Resource
	private RobotMapper robotmapper;
	
	public List<Robot> selectAllRobot(int rid){
		return robotmapper.selectAllRobot(rid);
	}
	
	public void insertRobot(Robot robot){
		robotmapper.insertRobot(robot);
	}
	
	public void deleteRobot(int id){
		robotmapper.deleteRobot(id);
	}
	
	public void updateRobot(Robot robot){
		robotmapper.updateRobot(robot);
	}
	
	public void setRobotStatus(Robot robot){
		robotmapper.setRobotStatus(robot);
	}
	
	public int selectRobotStatus(int rid){
		return robotmapper.selectRobotStatus(rid);
	}
	
	public Robot selectRobotKAndA(Robot robot){
		return robotmapper.selectRobotKAndA(robot);
	}
}
