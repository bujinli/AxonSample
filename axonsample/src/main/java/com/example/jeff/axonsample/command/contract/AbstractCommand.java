package com.example.jeff.axonsample.command.contract;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Getter
@Setter
@NoArgsConstructor
public class AbstractCommand {
  @TargetAggregateIdentifier
  protected Long identifier;

  public AbstractCommand(Long identifier) {
    this.identifier = identifier;
  }
}
