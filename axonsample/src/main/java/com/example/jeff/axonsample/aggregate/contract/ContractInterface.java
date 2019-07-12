package com.example.jeff.axonsample.aggregate.contract;

import javax.validation.constraints.NotBlank;

public interface ContractInterface {
  Long getId();

  @NotBlank String getName();

  @NotBlank String getPartyA();

  @NotBlank String getPartyB();
}

