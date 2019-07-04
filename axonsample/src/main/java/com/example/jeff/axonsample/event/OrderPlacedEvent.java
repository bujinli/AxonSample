package com.example.jeff.axonsample.event;

public class OrderPlacedEvent {

  private final String orderId;
  private final String product;

  public OrderPlacedEvent(String orderId, String product) {
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
