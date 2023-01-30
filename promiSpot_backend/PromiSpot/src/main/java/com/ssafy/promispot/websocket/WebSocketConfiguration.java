package com.ssafy.promispot.websocket;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;


// websocket ��α� : https://velog.io/@postlist/SpringBoot-WebSocket-%EB%A7%8C%EB%93%A4%EA%B8%B0-React-%EC%B1%84%ED%8C%85%EA%B5%AC%ED%98%84

/*
 * �Ϲ������� Ŭ�������� Spring�� ���� bean���� ��ϵǰ� �ش� �ν��Ͻ��� Singleton���� ����������, 
 * @ServerEndPoint Annotation�� �޸� Ŭ�������� WebSocket�� ������ ������ �ν��Ͻ��� �����ǰ� 
 * JWA������ ���� �����Ǳ� ������ ���ο� Autowired�� ������ ������� ���������� �ʱ�ȭ�� �����ʴ´�.
 */

@Component
public class WebSocketConfiguration {
	
	@Bean
    public ServerEndpointExporter serverEndpointExporter() {
        return new ServerEndpointExporter();
    }

}
