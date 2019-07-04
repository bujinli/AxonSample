package com.example.jeff.axonsample.command;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

public class ConfirmOrderCommand {
  @TargetAggregateIdentifier
  private final String orderId;

  public ConfirmOrderCommand(String orderId) {
    this.orderId = orderId;
  }
}
