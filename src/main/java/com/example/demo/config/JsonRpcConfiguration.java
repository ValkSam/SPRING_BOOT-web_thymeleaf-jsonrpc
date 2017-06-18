package com.example.demo.config;

import com.example.demo.controller.apiV1.DepositApiV1;
import com.example.demo.controller.apiV1.OrderApiV1;
import com.example.demo.service.apiV1.DepositApiV1Impl;
import com.example.demo.service.apiV1.OrderApiV1Impl;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.googlecode.jsonrpc4j.*;
import com.googlecode.jsonrpc4j.spring.JsonServiceExporter;
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

/*  @Bean
  public Object compositeJsonServiceExporter() {
//    CompositeJsonServiceExporter exp = new CompositeJsonServiceExporter();
    Object exp = ProxyUtil.createCompositeServiceProxy(
        this.getClass().getClassLoader(),
        new Object[]{depositApiV1(), orderApiV1()},
        new Class[]{DepositApiV1.class, OrderApiV1.class},
        true
    );

    //in here you can provide custom HTTP status code providers etc. eg:
    //exp.setHttpStatusCodeProvider();
    //exp.setErrorResolver();
//    exp.setErrorResolver(errorResolver());
//    exp.setAllowExtraParams(false);
//    exp.setAllowLessParams(false);
//    exp.setServices(new Object[]{depositApiV1(), orderApiV1()});
//    exp.setServices(new Object[]{depositApiV1()});
//    exp.setServiceInterfaces(new Class[]{DepositApiV1.class, OrderApiV1.class});
//    exp.setServiceInterfaces(new Class[]{DepositApiV1.class});
    return exp;
  }*/

  @Bean(name = "/v1/deposit")
  public JsonServiceExporter jsonDepositServiceExporter() {
    JsonServiceExporter exporter = new JsonServiceExporter();
    exporter.setService(depositApiV1());
    exporter.setServiceInterface(DepositApiV1.class);
    return exporter;
  }

  @Bean(name = "/v1/order")
//  @Bean
  public JsonServiceExporter jsonOrderServiceExporter() {
    JsonServiceExporter exporter = new JsonServiceExporter();
    exporter.setService(orderApiV1());
    exporter.setServiceInterface(OrderApiV1.class);
    return exporter;
  }

  /*@Bean
  public JsonRpcServer jsonRpcServer() {
    return new JsonRpcServer(jsonOrderServiceExporter());
//    return new JsonRpcServer(compositeJsonServiceExporter());
  }*/
  @Bean
  public JsonRpcBasicServer jsonRpcServer() {
    ObjectMapper mapper = new ObjectMapper();
    Object handler = jsonOrderServiceExporter().getService(); // This would be an instance of a class annotated with @JsonRpcService
    JsonRpcBasicServer server = new JsonRpcBasicServer(mapper, handler);
    return server;
  }

/*  @Bean
  public ErrorResolver errorResolver() {
    return new ErrorResolver() {
      @Override
      public JsonError resolveError(Throwable throwable, Method method, List<JsonNode> list) {
        return new JsonError(1000, "ssss", 100);
      }
    };
  }*/
  /*@Bean
  public JsonRpcClient jsonRpcClient() {
    return new JsonRpcClient();
  }*/

  /*@Bean
  public ServerSocket serverSocket() throws IOException {
    ServerSocket ss = ServerSocketFactory.getDefault().createServerSocket(0, 0, InetAddress.getByName("localhost"));
    return ss;
  }

  @Bean
  public StreamServer streamServer(JsonRpcServer jsonRpcServer) throws IOException {
    int maxThreads = 50;
    int port = 8080;
    InetAddress bindAddress = InetAddress.getByName("localhost");
//    return new StreamServer(jsonRpcServer, maxThreads, port, 0, bindAddress);
    return new StreamServer(jsonRpcServer, maxThreads, 0, 0, bindAddress);
  }*/
}
