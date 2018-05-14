package com.cn.custom_service.dao;

import java.util.List;

import com.cn.custom_service.pojo.Robot;

/**
 * 
 * @author 刘帅
 * @time 2018.2.6
 * @time 2018.4.20
 */
public interface RobotMapper {
	public List<Robot> selectAllRobot(int rid);
	
	public void insertRobot(Robot robot); 
	
	public void deleteRobot(int id);
	
	public void updateRobot(Robot robot);
	
	public void setRobotStatus(Robot robot);
	
	public int selectRobotStatus(int rid);
	
	public Robot selectRobotKAndA(Robot robot);
}
