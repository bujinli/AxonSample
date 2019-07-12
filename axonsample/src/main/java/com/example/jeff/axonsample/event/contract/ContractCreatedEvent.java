package com.example.jeff.axonsample.event.contract;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ContractCreatedEvent extends ContractUpdatedEvent {
  public ContractCreatedEvent(Long identifier, String name, String partyA, String partyB) {
    super(identifier, name, partyA, partyB);
  }
}
