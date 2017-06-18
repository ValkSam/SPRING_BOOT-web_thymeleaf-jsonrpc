package com.example.demo.controller;

import com.example.demo.socket.ChatWebSocketHandler;
import com.googlecode.jsonrpc4j.JsonRpcServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.socket.TextMessage;

import java.io.*;

/**
 * Created by ValkSam on 09.06.2017.
 */
@Controller
public class GreetingController {
  @RequestMapping("/hello")
  public String greeting(@RequestParam(value = "name", required = false, defaultValue = "World") String name, Model model) {
    model.addAttribute("name", name + "!!!!!!!!!!!!!!!");
    return "greeting";
  }

//  @Autowired JsonRpcServer jsonRpcServer;

  @Autowired ChatWebSocketHandler chatWebSocketHandler;

  @MessageMapping("/color")
  public void receiveColor(String message) throws IOException {
    System.out.println("==================================" + message);
    OutputStream out = new ByteArrayOutputStream();
    InputStream in = new ByteArrayInputStream(message.getBytes());
//    jsonRpcServer.handle(in, out);
//    jsonRpcServer.handle(request, response);
  }

  @Scheduled(fixedDelay = 5000)
  private void bgColor() {
    chatWebSocketHandler.getSessions().forEach(webSocketSession -> {
      try {
        webSocketSession.sendMessage(new TextMessage("===!!!===="));
      } catch (IOException e) {
        e.printStackTrace();
      }
    });
  }
}
