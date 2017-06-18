package com.example.demo.config;

import com.example.demo.controller.apiV1.DepositApiV1;
import com.example.demo.controller.apiV1.OrderApiV1;
import com.example.demo.service.apiV1.DepositApiV1Impl;
import com.example.demo.service.apiV1.OrderApiV1Impl;
import com.googlecode.jsonrpc4j.JsonRpcServer;
import com.googlecode.jsonrpc4j.spring.JsonServiceExporter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Configuration
public class JsonRpcV1Configuration {

  @Bean(name = "/v1/order")
  public JsonServiceExporter jsonOrderServiceExporter(OrderApiV1 orderApiV1) {
    JsonServiceExporter exporter = new JsonServiceExporter();
    exporter.setService(orderApiV1);
    exporter.setServiceInterface(OrderApiV1.class);
    exporter.setAllowExtraParams(true);
    exporter.setAllowLessParams(true);
    return exporter;
  }

  @Bean(name = "/v1/deposit")
  public JsonServiceExporter jsonDepositServiceExporter(DepositApiV1 depositApiV1) {
    JsonServiceExporter exporter = new JsonServiceExporter();
    exporter.setService(depositApiV1);
    exporter.setServiceInterface(DepositApiV1.class);
    return exporter;
  }

  @Bean(name = "/v1/orderServer")
  public JsonRpcServer jsonRpcOrderServer(OrderApiV1 orderApiV1) {
    return new JsonRpcServer(orderApiV1);
  }

  @Bean(name = "/v1/depositServer")
  public JsonRpcServer jsonRpcDepositServer(DepositApiV1 depositApiV1) {
    return new JsonRpcServer(depositApiV1);
  }


}
