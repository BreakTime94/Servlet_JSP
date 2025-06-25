package controller.endpoint;

import java.io.IOException;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import lombok.extern.slf4j.Slf4j;

@ServerEndpoint("/echo") //나중에는 /echo/{roomId}로 표기하여 방번호도 표기 가능하다.  
@Slf4j
public class EchoEndPoint {
	// 모든 연결된 세션을 저장할 Set 필요
	private static final Set<Session> SESSIONS = new CopyOnWriteArraySet<Session>();
	
	//최소 3개의 메서드 정의 
	
	//연결
	@OnOpen
	public void onOpen(Session session) {
		SESSIONS.add(session);
		log.info("현재 접속자수는 {} 명 입니다.", SESSIONS.size());
	}
	//메세지 발송
	@OnMessage
	public void onMessage(String msg, Session session) throws IOException { //여기서 session은 접속한 session 이다.
		log.info("수신된 메세지 {}" , msg);
		
		for(Session s : SESSIONS) {
			s.getBasicRemote().sendText(session.getId() + " : " + msg);
		}
		
//		SESSIONS.forEach(s -> s.getBasicRemote().sendText(msg));
	}
	//종료
	@OnClose
	public void onClose(Session session) {
		SESSIONS.remove(session);
		log.info("현재 접속자수는 {} 명 입니다.", SESSIONS.size());
	}
}
