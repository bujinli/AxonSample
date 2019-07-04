package com.example.jeff.axonsample.command;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

public class ShipOrderCommand {

  @TargetAggregateIdentifier
  private final String orderId;

  public ShipOrderCommand(String orderId) {
    this.orderId = orderId;
  }
}
