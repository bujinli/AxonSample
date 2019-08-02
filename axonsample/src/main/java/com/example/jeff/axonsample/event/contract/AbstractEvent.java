package com.example.jeff.axonsample.event.contract;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.axonframework.modelling.command.TargetAggregateIdentifier;
import org.axonframework.serialization.Revision;

@Getter
@Setter
@NoArgsConstructor
@Revision("1.0.0")
public class AbstractEvent {

  @TargetAggregateIdentifier
  protected Long identifier;

  public AbstractEvent(Long identifier) {
    this.identifier = identifier;
  }
}
