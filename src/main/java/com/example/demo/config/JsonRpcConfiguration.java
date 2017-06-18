package com.example.demo.config;

import com.example.demo.controller.apiV1.DepositApiV1;
import com.example.demo.controller.apiV1.OrderApiV1;
import com.example.demo.service.apiV1.DepositApiV1Impl;
import com.example.demo.service.apiV1.OrderApiV1Impl;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.googlecode.jsonrpc4j.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.net.ServerSocketFactory;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;

@Configuration
public class JsonRpcConfiguration {

  @Bean
  public DepositApiV1 depositApiV1() {
    return new DepositApiV1Impl();
  }

  @Bean
  public OrderApiV1 orderApiV1() {
    return new OrderApiV1Impl();
  }


  @Bean
  public JsonRpcServer jsonRpcServer(OrderApiV1 orderApiV1) {
    return new JsonRpcServer(orderApiV1);
  }

  /*@Bean
  public JsonRpcBasicServer jsonRpcServer(OrderApiV1 orderApiV1) {
    ObjectMapper mapper = new ObjectMapper();
    Object handler = orderApiV1; // This would be an instance of a class annotated with @JsonRpcService
    JsonRpcBasicServer server = new JsonRpcBasicServer(mapper, handler);
    return server;
  }*/

}
