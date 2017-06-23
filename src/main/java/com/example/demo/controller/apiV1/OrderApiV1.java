package com.example.demo.controller.apiV1;

import com.example.demo.model.Order;
import com.example.demo.service.exeption.OrderNotFoundException;
import com.googlecode.jsonrpc4j.*;
import org.springframework.stereotype.Component;

import java.util.List;

public interface OrderApiV1 {
  /*
  POST /v1/order
  content-type: application/json
  {"id":"1","jsonrpc":"2.0","method":"getAll"}

  GET /v1/order?jsonrpc=2.0&id=1&method=getAll&params=W10=
  base64encode([]) -> W10=
  */
  @JsonRpcMethod(value = "getAll")
  List<Order> getAll();

  /*
   POST /v1/order
   content-type: application/json
   {"id":"1","jsonrpc":"2.0","method":"get", "params":{"id":2}}
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

  GET /v1/order?jsonrpc=2.0&id=1&method=getOne&params=eyJpZCI6Mn0=
  */
  @JsonRpcMethod(value = "getOrder")
  Order getOrder(Order order);

}
