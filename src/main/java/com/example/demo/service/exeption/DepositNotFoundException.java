package com.example.demo.service.exeption;

/**
 * Created by ValkSam on 17.06.2017.
 */
public class DepositNotFoundException extends RuntimeException {
  public DepositNotFoundException(String message) {
    super(message);
  }
}
