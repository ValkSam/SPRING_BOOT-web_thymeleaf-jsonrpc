package com.example.demo.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.googlecode.jsonrpc4j.JsonRpcServer;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.*;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.view.RedirectView;

import java.io.*;
import java.math.BigDecimal;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

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

  @RequestMapping("/redirect")
  public RedirectView redirect() {
    RedirectView redirectView = new RedirectView("/hello");
    redirectView.addStaticAttribute("attr", "this attr");
    return redirectView;
  }

  String SECRET_KEY = "secrete";
  String APPLICATION_ID = "my_merchant";

  @RequestMapping("/send")
  public RedirectView send() {
    RestTemplate restTemplate = new RestTemplate();
    restTemplate.setErrorHandler(new DefaultResponseErrorHandler() {
      @Override
      protected boolean hasError(HttpStatus statusCode) {
        return false;
      }
    });
    restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
    HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();
    requestFactory.setHttpClient(HttpClientBuilder.create().build());
    requestFactory.setConnectionRequestTimeout(25000);
    requestFactory.setReadTimeout(25000);
    restTemplate.setRequestFactory(requestFactory);
    /**/
    Map<String, String> map = new TreeMap<String, String>() {{
      put("applicationId", APPLICATION_ID);
      put("description", "name of goods");
      put("price", BigDecimal.valueOf(10.1).toString());
      put("amount", BigDecimal.valueOf(3.0001).toString());
    }};
    /**/
    String forHash = map.values().stream().collect(Collectors.joining(":", "", ":")).concat(SECRET_KEY);
    String md5Hex = DigestUtils.md5Hex(forHash);
    map.put("sign", md5Hex);
    /**/
    JSONObject request = new JSONObject(map);
    /**/
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);
    /**/
    HttpEntity<String> entity = new HttpEntity<String>(request.toString(), headers);
    /**/
    ResponseEntity<String> responseEntity = restTemplate.postForEntity("http://localhost:8080/getAuthPage", entity, String.class);
    return new RedirectView(responseEntity.getBody());
  }

  @RequestMapping("/getAuthPage")
  @ResponseBody
  private String getAuthPage(@RequestBody String jsonData) throws IOException {
    System.out.println(jsonData);
    TreeMap<String, String> map = new TreeMap(new ObjectMapper().readValue(jsonData, TreeMap.class));
    String sign = map.remove("sign");
    String forHash = map.values().stream().collect(Collectors.joining(":", "", ":")).concat(SECRET_KEY);
    String md5Hex = DigestUtils.md5Hex(forHash);
    System.out.println(sign);
    System.out.println(md5Hex);
    System.out.println(sign.equals(md5Hex));
    return "http://localhost:8080/authPage";
  }

  @RequestMapping("/authPage")
  private String getAuthPage(Model model) {
    return "auth";
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
