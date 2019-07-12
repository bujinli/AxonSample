package com.example.jeff.axonsample.command.contract;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CreateContractCommand extends UpdateContractCommand {
  public CreateContractCommand(Long identifier, String name, String partyA, String partyB) {
    super(identifier, name, partyA, partyB);
  }
}
