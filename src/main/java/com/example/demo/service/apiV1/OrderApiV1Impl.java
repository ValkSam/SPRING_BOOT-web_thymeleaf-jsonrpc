package com.example.demo.service.apiV1;

import com.example.demo.controller.apiV1.OrderApiV1;
import com.example.demo.dao.OrderDao;
import com.example.demo.model.Order;
import com.example.demo.service.exeption.OrderNotFoundException;
import com.googlecode.jsonrpc4j.spring.AutoJsonRpcServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AutoJsonRpcServiceImpl
public class OrderApiV1Impl implements OrderApiV1 {

  @Autowired OrderDao orderDao;

  @Override
  public List<Order> getAll() {
    return orderDao.findAll();
  }

  @Override
  public Order getOne(int orderId) {
    Optional<Order> result = orderDao.findById(orderId);
    return result.orElseThrow(() -> new OrderNotFoundException(String.valueOf(orderId)));
  }

  @Override
  public Order getOne(Order order) {
    return getOne(order.getId());
  }
}
