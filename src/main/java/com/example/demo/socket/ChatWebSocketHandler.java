package com.example.demo.socket;

import com.googlecode.jsonrpc4j.JsonRpcBasicServer;
import com.googlecode.jsonrpc4j.JsonRpcServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;


//@Component
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

    @Autowired JsonRpcServer jsonRpcServer;
//    @Autowired JsonRpcBasicServer jsonRpcServer;

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        super.handleTextMessage(session, message);
        System.out.println("Handler:               "+message);

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
