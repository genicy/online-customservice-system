package com.cn.custom_service.webchat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArraySet;

import javax.servlet.http.HttpSession;
import javax.websocket.EndpointConfig;
import javax.websocket.OnOpen;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
@ServerEndpoint(value = "/chatServer/{userId}")
public class ChatServer {
private static int onlineCount = 0;
private static CopyOnWriteArraySet<ChatServer> webscoketSet = new CopyOnWriteArraySet<ChatServer>();
private Session session;
private HttpSession httpSession;
private static Map router = new HashMap<>();  
private static List list = new ArrayList<>();   
private String userid;
@OnOpen
public void OnOpen(Session session ,@PathParam("userId")String userId){
	this.session = session;
	System.out.println(session);
	webscoketSet.add(this);
	addOnlineCount();
	this.userid = userId;
	System.out.println(userId);
	System.out.println(userid);
	list.add(userid);
	router.put(userid, session);
	
}
private void addOnlineCount() {
	ChatServer.onlineCount++;
	
}
@OnClose
public void OnClose(){
	webscoketSet.remove(this);
	subOnlineCount();
	list.remove(userid);
}
private void subOnlineCount() {
	ChatServer.onlineCount--;
	
}
@OnMessage
public void OnMessage(String _message){
	System.out.println(_message);
	JSONObject chat = JSON.parseObject(_message);
	JSONObject message = JSON.parseObject(chat.get("message").toString());
	String to = message.get("to").toString();
	String from = message.get("from").toString();
	System.out.println(from + " " + to);
	System.out.println((Session)router.get(from));
	sigleSend(_message,(Session)router.get(from));
	sigleSend(_message,(Session)router.get(to));
	
	
}
public void sigleSend(String message,Session session){
	try {
		session.getBasicRemote().sendText(message);;
	} catch (Exception e) {
		// TODO: handle exception
		e.printStackTrace();
	}
}
}
