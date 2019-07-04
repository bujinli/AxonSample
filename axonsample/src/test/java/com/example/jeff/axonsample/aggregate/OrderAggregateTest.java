package com.example.jeff.axonsample.aggregate;

import com.example.jeff.axonsample.command.PlaceOrderCommand;
import com.example.jeff.axonsample.event.OrderPlacedEvent;
import org.axonframework.test.aggregate.AggregateTestFixture;
import org.axonframework.test.aggregate.FixtureConfiguration;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.UUID;

@RunWith(SpringRunner.class)
public class OrderAggregateTest {

  private FixtureConfiguration<OrderAggregate> fixture;

  @Before
  public void setUp() {
    fixture = new AggregateTestFixture<>(OrderAggregate.class);
  }

  @Test
  public void test01() {
    String orderId = UUID.randomUUID().toString();
    String product = "Deluxe Chair";
    fixture.givenNoPriorActivity().when(new PlaceOrderCommand(orderId, product))
        .expectEvents(new OrderPlacedEvent(orderId, product));
  }
}
