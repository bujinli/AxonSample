package com.example.jeff.axonsample.command.contract;

import com.example.jeff.axonsample.aggregate.contract.ContractInterface;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
public class UpdateContractCommand extends AbstractCommand implements ContractInterface {

  private String name;

  private String partyA;

  private String partyB;

  public UpdateContractCommand(Long identifier, String name, String partyA, String partyB) {
    super(identifier);
    this.name = name;
    this.partyA = partyA;
    this.partyB = partyB;
  }

  @Override
  public Long getId() {
    return this.identifier;
  }

  @Override
  public @NotBlank String getName() {
    return this.name;
  }

  @Override
  public @NotBlank String getPartyA() {
    return this.partyA;
  }

  @Override
  public @NotBlank String getPartyB() {
    return this.partyB;
  }
}
