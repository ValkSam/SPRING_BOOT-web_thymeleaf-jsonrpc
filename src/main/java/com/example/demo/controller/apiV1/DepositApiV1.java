package com.example.demo.controller.apiV1;

import com.example.demo.model.Deposit;
import com.example.demo.service.exeption.DepositNotFoundException;
import com.googlecode.jsonrpc4j.*;

import java.util.List;

//@JsonRpcService("/v1/deposit")
public interface DepositApiV1 {
  /*
  POST /v1/deposit
  content-type: application/json
  {"id":"1","jsonrpc":"2.0","method":"getAll"}

  GET /v1/deposit?jsonrpc=2.0&id=1&method=getAll
  */
//  @JsonRpcMethod(value = "getAll")
  List<Deposit> getAll();

  /*
   POST /v1/deposit
   content-type: application/json
   {"id":"1","jsonrpc":"2.0","method":"get", "params":{"id":2}}

   GET /v1/deposit?jsonrpc=2.0&id=1&method=get&params=%7B%22id%22%3A2%7D
      urlEncode({"id":2}) -> %7B%22id%22%3A2%7D
  */
  @JsonRpcErrors({
      @JsonRpcError(exception = DepositNotFoundException.class,
          code = -10000, message = "Order not found")
  })
//  @JsonRpcMethod(value = "get")
  Deposit getOne(@JsonRpcParam(value = "id") int depositId);

  /*
  * POST /v1/deposit
  content-type: application/json
  {"id":"1","jsonrpc":"2.0","method":"get", "params":[{"id":2, "type":"USD"}]}
  */
//  @JsonRpcMethod(value = "get")
  Deposit getOne(Deposit deposit);

}
