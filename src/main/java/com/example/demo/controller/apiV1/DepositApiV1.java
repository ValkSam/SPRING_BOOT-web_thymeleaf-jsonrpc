package com.example.demo.controller.apiV1;

import com.example.demo.model.Deposit;
import com.example.demo.service.exeption.DepositNotFoundException;
import com.googlecode.jsonrpc4j.*;

import java.util.List;

public interface DepositApiV1 {
  /*
  POST /v1/deposit
  content-type: application/json
  {"id":"1","jsonrpc":"2.0","method":"getAll"}

  GET /v1/deposit?jsonrpc=2.0&id=1&method=getAll&params=W10=
  */
  @JsonRpcMethod(value = "getAll")
  List<Deposit> get_All();

  /*
   POST /v1/deposit
   content-type: application/json
   {"id":"1","jsonrpc":"2.0","method":"get", "params":{"id":2}}

   GET /v1/deposit?jsonrpc=2.0&id=1&method=getOne&params=eyJpZCI6Mn0=
      base64encode({"id":2}) -> eyJpZCI6Mn0=
  */
  @JsonRpcErrors({
      @JsonRpcError(exception = DepositNotFoundException.class,
          code = -10000, message = "Order not found")
  })
  @JsonRpcMethod(value = "getOne")
  Deposit get_One(@JsonRpcParam(value = "id") int depositId);

  /*
  * POST /v1/deposit
  content-type: application/json
  {"id":"1","jsonrpc":"2.0","method":"get", "params":[{"id":2, "type":"USD"}]}
  */
  Deposit get_One(Deposit deposit);

}
