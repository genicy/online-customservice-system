package com.cn.custom_service.webchat;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArraySet;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import com.alibaba.fastjson.JSON;
@ServerEndpoint(value = "/adminServer/{adminId}")
public class AdminServer {
	private static int onlineCount = 0;
	private static CopyOnWriteArraySet<AdminServer> webscoketAdminSet = new CopyOnWriteArraySet<AdminServer>();
	private Session session;
	private static Map router = new HashMap<>();  
	private static List list = new ArrayList<>();   
	private String adminid;
	
	@OnOpen
	public void OnOpen(Session session ,@PathParam("adminId")String adminId){
		this.session = session;
		webscoketAdminSet.add(this);
		addOnlineCount();
		this.adminid = adminId;
		list.add(adminid);
		router.put(adminid, session);
		
		
	}
	@OnMessage
	public void onMessage(String message) {
		for(AdminServer adminserver:webscoketAdminSet ){
			try {
				adminserver.session.getBasicRemote().sendText(message);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	private void subOnlineCount() {
		AdminServer.onlineCount--;
		
	}
	public void sigleSend(String message,Session session){
		try {
			session.getBasicRemote().sendText(message);;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	private void addOnlineCount() {
		AdminServer.onlineCount++;
		
	}
	@OnClose
	public void OnClose(){
		webscoketAdminSet.remove(this);
		subOnlineCount();
		list.remove(adminid);
	}
	
}

