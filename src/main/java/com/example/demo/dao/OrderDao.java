package com.example.demo.dao;

import com.example.demo.model.Order;

import java.util.*;

/**
 * Created by ValkSam on 17.06.2017.
 */
public interface OrderDao {

  List<Order> findAll();

  Optional<Order> findById(Integer id);
}
