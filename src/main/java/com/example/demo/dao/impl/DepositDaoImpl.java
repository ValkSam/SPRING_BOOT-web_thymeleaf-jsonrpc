package com.example.demo.dao.impl;

import com.example.demo.dao.DepositDao;
import com.example.demo.model.Deposit;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.*;

/**
 * Created by ValkSam on 17.06.2017.
 */
@Repository
public class DepositDaoImpl implements DepositDao {
  Map<Integer, Deposit> deposits = new HashMap<Integer, Deposit>() {{
    put(1, new Deposit(1, "USD", BigDecimal.ONE));
    put(2, new Deposit(2, "EUR", BigDecimal.ONE));
  }};

  @Override
  public List<Deposit> findAll() {
    return new ArrayList<Deposit>(deposits.values());
  }

  @Override
  public Optional<Deposit> findById(Integer id) {
    return Optional.ofNullable(deposits.get(id));
  }
}
