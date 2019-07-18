package com.example.jeff.axonsample.controller;

import com.example.jeff.axonsample.aggregate.contract.ContractAggregate;
import com.example.jeff.axonsample.command.contract.CreateContractCommand;
import com.example.jeff.axonsample.command.contract.DeleteContractCommand;
import com.example.jeff.axonsample.command.contract.UpdateContractCommand;
import com.example.jeff.axonsample.query.QueryContractCommand;
import lombok.AllArgsConstructor;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import javax.validation.Valid;

@RestController
@RequestMapping("/contracts")
@AllArgsConstructor
public class ContractController {

  private final CommandGateway commandGateway;
  private final QueryGateway queryGateway;

  @PostMapping
  public void createContract(@RequestBody @Valid CreateContractCommand command) {
    commandGateway.send(command);
  }

  @PutMapping("/{id}")
  public void updateContract(@PathVariable("id") Long id,
      @RequestBody @Valid UpdateContractCommand command) {
    command.setIdentifier(id);
    commandGateway.send(command);
  }

  @DeleteMapping("/{id}")
  public void deleteContract(@PathVariable("id") Long id) {
    commandGateway.send(new DeleteContractCommand(id));
  }

  @GetMapping("/{id}")
  public ContractAggregate getContract(@PathVariable("id") Long id) {
    QueryContractCommand command = new QueryContractCommand(id, Instant.now());

    return queryGateway.query(command, ContractAggregate.class).join();
  }
}

