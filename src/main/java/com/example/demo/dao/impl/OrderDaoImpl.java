package com.example.demo.dao.impl;

import com.example.demo.dao.OrderDao;
import com.example.demo.model.Order;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.*;

/**
 * Created by ValkSam on 17.06.2017.
 */
@Repository
public class OrderDaoImpl implements OrderDao {
  Map<Integer, Order> orders = new HashMap<Integer, Order>() {{
    put(1, new Order(1, "SELL", BigDecimal.TEN));
    put(2, new Order(2, "BUY", BigDecimal.TEN));
  }};

  @Override
  public List<Order> findAll() {
    return new ArrayList<Order>(orders.values());
  }

  @Override
  public Optional<Order> findById(Integer id) {
    return Optional.ofNullable(orders.get(id));
  }
}
