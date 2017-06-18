package com.example.demo.socket;

import com.googlecode.jsonrpc4j.JsonRpcServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;


@Component
public class ChatWebSocketHandler extends TextWebSocketHandler {

  private final List<WebSocketSession> sessions = new CopyOnWriteArrayList<>();

  @Override
  public void afterConnectionEstablished(WebSocketSession session) throws Exception {
    sessions.add(session);
  }

  @Override
  public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
    sessions.remove(session);
  }

  public List<WebSocketSession> getSessions() {
    return sessions;
  }

  @Qualifier("/v1/orderServer") @Autowired JsonRpcServer jsonRpcOrderServer;
  @Qualifier("/v1/depositServer") @Autowired JsonRpcServer jsonRpcDepositServer;

  @Override
  protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
    super.handleTextMessage(session, message);
    System.out.println("Handler:               " + message);

        /*System.out.println(message);
        OutputStream out = new ByteArrayOutputStream();
        InputStream in = new ByteArrayInputStream(message.asBytes());
        jsonRpcServer.handleRequest(in, out);
        System.out.println();
        System.out.println("****************************************************************************   1");
        session.sendMessage(new TextMessage(out.toString()));
        System.out.println("****************************************************************************   2");*/
  }
}
