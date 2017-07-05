package com.example.demo.controller;

import com.example.demo.socket.ChatWebSocketHandler;
import com.googlecode.jsonrpc4j.JsonRpcBasicServer;
import com.googlecode.jsonrpc4j.JsonRpcServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.socket.TextMessage;

import java.io.*;
import java.util.Map;

/**
 * Created by ValkSam on 09.06.2017.
 */
@Controller
public class GreetingController {
  @RequestMapping("/hello")
  public String greeting(Model model) {
    model.addAttribute("!!!!!!!!!!!!!!!");
    return "greeting";
  }

  @Autowired
  Map<String, JsonRpcServer> jsonRpcServerMap;

  @Qualifier("/v1/orderServer") @Autowired JsonRpcServer jsonRpcOrderServer;
  @Qualifier("/v1/depositServer") @Autowired JsonRpcServer jsonRpcDepositServer;

//  @Autowired ChatWebSocketHandler chatWebSocketHandler;

  @Autowired
  private SimpMessagingTemplate simpMessagingTemplate;

  @MessageMapping("/order")
  @SendToUser("/topic/base")
  public String receiveColor(Message message, String str, @Payload String payload) throws IOException {
    System.out.println(message);
    OutputStream out = new ByteArrayOutputStream();
    InputStream in = new ByteArrayInputStream(payload.getBytes());
    jsonRpcOrderServer.handle(in, out);
    return out.toString();
  }

  @Scheduled(fixedDelay = 5000)
  private void bgColor() {
    /*chatWebSocketHandler.getSessions().forEach(webSocketSession -> {
      try {
        webSocketSession.sendMessage(new TextMessage("===!!!===="));
      } catch (IOException e) {
        e.printStackTrace();
      }
    });*/
    simpMessagingTemplate.convertAndSend("/topic/part", "======***======");
  }
}
