package com.example.jeff.axonsample.query;

import com.example.jeff.axonsample.event.OrderPlacedEvent;
import com.example.jeff.axonsample.query.model.OrderedProduct;
import com.example.jeff.axonsample.query.querystatement.FindAllOrderedProductsQuery;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.queryhandling.QueryHandler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrderedProductsEventHandler {
  private final Map<String, OrderedProduct> orderedProducts = new HashMap<>();

  @EventHandler
  public void on(OrderPlacedEvent event) {
    String orderId = event.getOrderId();
    orderedProducts.put(orderId, new OrderedProduct(orderId, event.getProduct()));
  }


  @QueryHandler
  public List<OrderedProduct> handle(FindAllOrderedProductsQuery query){
    return new ArrayList<>(orderedProducts.values());
  }

}
