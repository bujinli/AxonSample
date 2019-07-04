package com.example.jeff.axonsample.event;

public class OrderConfirmedEvent {

  private final String orderId;

  public OrderConfirmedEvent(String orderId) {
    this.orderId = orderId;
  }
}
