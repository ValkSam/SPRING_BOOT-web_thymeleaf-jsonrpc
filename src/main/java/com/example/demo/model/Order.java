package com.example.demo.model;

import lombok.*;

import java.math.BigDecimal;

/**
 * Created by ValkSam on 17.06.2017.
 */
@Getter @Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Order {
  private Integer id;
  private String type;
  private BigDecimal amount;

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    Order order = (Order) o;

    return id != null ? id.equals(order.id) : order.id == null;

  }

  @Override
  public int hashCode() {
    return id != null ? id.hashCode() : 0;
  }
}
