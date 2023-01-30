package com.ssafy.promispot.websocket;

import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;




/*
1. bean ��� / websocket Ȱ��ȭ  
 */

@Service
@ServerEndpoint("/*/socket/chat")
public class WebSocketChat {
	
	
	 private static Logger logger = LoggerFactory.getLogger(WebSocketChat.class);

	
	// 2. Ŭ���̾�Ʈ�� session ���� ���� 
	private static Set<Session> clients = Collections.synchronizedSet(new HashSet<Session>());
	
		
		/*
		 * @ServerEndpoing ���� ����� URL�� ��û�� ���� ��� �ش� �޼ҵ尡 ����ȴ�. 
		 * Ŭ���̾�Ʈ�� ������ �Ű������� ���޹޴´�. 
		 */
	   @OnOpen
	   public void onOpen(Session session) {
	        logger.info("open session : {}, clients={}", session.toString(), clients);


	        if(!clients.contains(session)) {
	            clients.add(session);
	            logger.info("session open : {}", session);
	        }else{
	            logger.info("�̹� ����� session");
	        }
	    }

	   
	    /*
	     * Ŭ���̾�Ʈ�� ����Socket�� ����� ���¿���, �޼����� ���޵Ǹ� �ش� �޼��尡
	     *  ����Ǿ� ����� clients�� �ִ� ��� session���� �޼����� �����մϴ�.
	     */
	    @OnMessage
	    public void onMessage(String message, Session session) throws IOException {
	        logger.info("receive message : {}", message);

	        for (Session s : clients) {
	            logger.info("send data : {}", message);

	            s.getBasicRemote().sendText(message);
	        }
	    }

	    
	    /*
	     * 
	     * Ŭ���̾�Ʈ�� URL�� �ٲٰų� �������� �����ϸ� 
	     * �ش� �޼��尡 ����Ǿ� Ŭ���̾�Ʈ�� ���������� clients���� �����մϴ�.
	     * 
	     */
	    @OnClose
	    public void onClose(Session session) {
	        logger.info("session close : {}", session);
	        clients.remove(session);
	    }
	

}
