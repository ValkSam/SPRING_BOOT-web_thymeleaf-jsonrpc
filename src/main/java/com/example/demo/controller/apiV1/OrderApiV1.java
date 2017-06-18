package com.example.demo.controller.apiV1;

import com.example.demo.model.Order;
import com.example.demo.service.exeption.OrderNotFoundException;
import com.googlecode.jsonrpc4j.*;
import org.springframework.stereotype.Component;

import java.util.List;

//@JsonRpcService("/v1/order")
public interface OrderApiV1 {
  /*
  POST /v1/order
  content-type: application/json
  {"id":"1","jsonrpc":"2.0","method":"getAll"}

  GET /v1/order?jsonrpc=2.0&id=1&method=getAll
  */
  @JsonRpcMethod(value = "getAll")
  List<Order> getAll();

  /*
   POST /v1/order
   content-type: application/json
   {"id":"1","jsonrpc":"2.0","method":"get", "params":{"id":2}}

   GET /v1/order?jsonrpc=2.0&id=1&method=get&params=%7B%22id%22%3A2%7D
  */
  @JsonRpcErrors({
      @JsonRpcError(exception = OrderNotFoundException.class,
          code = -10000, message = "Order not found")
  })
  @JsonRpcMethod(value = "getOne")
  Order getOne(@JsonRpcParam(value = "id") int orderId);

  /*
  POST /v1/order
  content-type: application/json
  {"id":"1","jsonrpc":"2.0","method":"get", "params":[{"id":2, "type":"SELL"}]}

  GET /v1/order?jsonrpc=2.0&id=1&method=get&params=%5B%7B%22id%22%3A2%2C+%22type%22%3A%22SELL%22%7D%5D
  */
  @JsonRpcMethod(value = "getOrder")
  Order getOrder(Order order);

}
