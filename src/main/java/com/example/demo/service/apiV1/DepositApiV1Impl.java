package com.example.demo.service.apiV1;

import com.example.demo.controller.apiV1.DepositApiV1;
import com.example.demo.dao.DepositDao;
import com.example.demo.model.Deposit;
import com.example.demo.model.Order;
import com.example.demo.service.exeption.DepositNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DepositApiV1Impl implements DepositApiV1 {

  @Autowired DepositDao depositDao;

  @Override
  public List<Deposit> get_All() {
    return depositDao.findAll();
  }

  @Override
  public Deposit get_One(int depositId) {
    Optional<Deposit> result = depositDao.findById(depositId);
    return result.orElseThrow(() -> new DepositNotFoundException(String.valueOf(depositId)));
  }

  @Override
  public Deposit get_One(Deposit deposit) {
    return get_One(deposit.getId());
  }
}
