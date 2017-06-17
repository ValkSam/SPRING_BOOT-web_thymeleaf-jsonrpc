package com.example.demo.dao;

import com.example.demo.model.Deposit;
import com.example.demo.model.Order;

import java.util.List;
import java.util.Optional;

/**
 * Created by ValkSam on 17.06.2017.
 */
public interface DepositDao {

  List<Deposit> findAll();

  Optional<Deposit> findById(Integer id);
}
