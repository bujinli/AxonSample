package com.example.jeff.axonsample.command.contract;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class DeleteContractCommand extends AbstractCommand {
  public DeleteContractCommand(Long identifier) {
    super(identifier);
  }
}
