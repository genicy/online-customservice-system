package com.cn.custom_service.webchat;

import java.io.IOException;
import java.net.URI;
import javax.websocket.ClientEndpoint;
import javax.websocket.ContainerProvider;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.WebSocketContainer;
import javax.websocket.OnError;
@ClientEndpoint
public class AdminClient {
	
	private Session session;
	@OnOpen
	public void onOpen(Session session) {
		this.session = session;
	}
	@OnMessage
	public void onMessage(String message){
		
	}
	@OnClose
	public void onClose() throws IOException{
		this.session.close();
	}
	@OnError
	public void OnError(Throwable t) {
		t.printStackTrace();
	}
	public void sendMessage(String message) throws IOException{
		try {
			
			this.session.getBasicRemote().sendText(message);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void start(String uri) {
		WebSocketContainer container = ContainerProvider.getWebSocketContainer();
		try {
			this.session = container.connectToServer(AdminClient.class, URI.create(uri));
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
