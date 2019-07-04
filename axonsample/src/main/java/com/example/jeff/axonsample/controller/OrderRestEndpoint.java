package com.example.jeff.axonsample.controller;

import com.example.jeff.axonsample.command.ConfirmOrderCommand;
import com.example.jeff.axonsample.command.PlaceOrderCommand;
import com.example.jeff.axonsample.command.ShipOrderCommand;
import com.example.jeff.axonsample.query.model.OrderedProduct;
import com.example.jeff.axonsample.query.querystatement.FindAllOrderedProductsQuery;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
public class OrderRestEndpoint {

  private final CommandGateway commandGateway;
  private final QueryGateway queryGateway;

  public OrderRestEndpoint(CommandGateway commandGateway, QueryGateway queryGateway) {
    this.commandGateway = commandGateway;
    this.queryGateway = queryGateway;
  }

  @PostMapping(value = "/ship-order")
  public void shipOrder() {
    String orderId = UUID.randomUUID().toString();
    commandGateway.send(new PlaceOrderCommand(orderId, "Deluxe Chair"));
    commandGateway.send(new ConfirmOrderCommand(orderId));
    commandGateway.send(new ShipOrderCommand(orderId));
  }

  @GetMapping(value = "/all-orders")
  public List<OrderedProduct> findAllOrderedProducts() {
    return queryGateway.query(new FindAllOrderedProductsQuery(),
        ResponseTypes.multipleInstancesOf(OrderedProduct.class)).join();
  }
}
