package com.example.jeff.axonsample.command;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

public class PlaceOrderCommand {

  @TargetAggregateIdentifier
  private final String orderId;
  private final String product;

  public PlaceOrderCommand(String orderId, String product) {
    this.orderId = orderId;
    this.product = product;
  }

  public String getOrderId() {
    return orderId;
  }

  public String getProduct() {
    return product;
  }
}
