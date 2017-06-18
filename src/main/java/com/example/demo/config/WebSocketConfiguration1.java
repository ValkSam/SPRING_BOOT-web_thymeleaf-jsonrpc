package com.example.demo.config;

import com.example.demo.socket.ChatWebSocketHandler;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.*;

/**
 * Created by ValkSam on 18.06.2017.
 */
//@Configuration
@EnableWebSocket
public class WebSocketConfiguration1 implements WebSocketConfigurer {

  @Bean
  ChatWebSocketHandler chatWebSocketHandler(){
    return new ChatWebSocketHandler();
  }

  @Override
  public void registerWebSocketHandlers(WebSocketHandlerRegistry webSocketHandlerRegistry) {
    webSocketHandlerRegistry.addHandler(chatWebSocketHandler(), "/color").withSockJS();
  }
}
