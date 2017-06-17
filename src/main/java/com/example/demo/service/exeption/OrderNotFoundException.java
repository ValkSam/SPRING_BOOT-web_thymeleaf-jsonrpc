package com.example.demo.service.exeption;

/**
 * Created by ValkSam on 17.06.2017.
 */
public class OrderNotFoundException extends RuntimeException {
  public OrderNotFoundException(String message) {
    super(message);
  }
}
