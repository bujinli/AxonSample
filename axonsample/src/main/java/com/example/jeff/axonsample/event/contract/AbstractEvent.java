package com.example.jeff.axonsample.event.contract;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Getter
@Setter
@NoArgsConstructor
public class AbstractEvent {

  @TargetAggregateIdentifier
  protected Long identifier;

  public AbstractEvent(Long identifier) {
    this.identifier = identifier;
  }
}
