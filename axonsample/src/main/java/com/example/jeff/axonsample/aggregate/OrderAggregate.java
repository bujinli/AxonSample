package com.example.jeff.axonsample.aggregate;

import com.example.jeff.axonsample.command.ConfirmOrderCommand;
import com.example.jeff.axonsample.command.PlaceOrderCommand;
import com.example.jeff.axonsample.command.ShipOrderCommand;
import com.example.jeff.axonsample.event.OrderConfirmedEvent;
import com.example.jeff.axonsample.event.OrderPlacedEvent;
import com.example.jeff.axonsample.event.OrderShippedEvent;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

@Aggregate
public class OrderAggregate {

  @AggregateIdentifier
  private String orderId;

  private boolean orderConfirmed;

  @CommandHandler
  public OrderAggregate(PlaceOrderCommand cmd) {
    AggregateLifecycle.apply(new OrderPlacedEvent(cmd.getOrderId(), cmd.getProduct()));
  }

  @EventSourcingHandler
  public void handle(OrderPlacedEvent event) {
    this.orderId = event.getOrderId();

    orderConfirmed = false;
  }

  protected OrderAggregate() {
  }

  @CommandHandler
  public void handle(ConfirmOrderCommand command) {
    AggregateLifecycle.apply(new OrderConfirmedEvent(orderId));
  }

  @CommandHandler
  public void handle(ShipOrderCommand command) {
    if (!orderConfirmed) {
      throw new IllegalStateException("Cannot ship an order which has not been confirmed yet.");
    }
    AggregateLifecycle.apply(new OrderShippedEvent(orderId));
  }

  @EventSourcingHandler
  public void on(OrderConfirmedEvent event) {
    orderConfirmed = true;
  }
}
