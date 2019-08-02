package com.example.jeff.axonsample.aggregate.contract;

import com.example.jeff.axonsample.command.contract.CreateContractCommand;
import com.example.jeff.axonsample.command.contract.DeleteContractCommand;
import com.example.jeff.axonsample.command.contract.UpdateContractCommand;
import com.example.jeff.axonsample.event.contract.ContractCreatedEvent;
import com.example.jeff.axonsample.event.contract.ContractDeletedEvent;
import com.example.jeff.axonsample.event.contract.ContractUpdatedEvent;
import com.example.jeff.axonsample.util.UIDGenerator;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.messaging.MetaData;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Aggregate
public class ContractAggregate implements ContractInterface {

  @AggregateIdentifier
  private Long id;

  private String name;

  private String partyA;

  private String partyB;
  private String industryName;

  private boolean deleted = false;

  @CommandHandler
  public ContractAggregate(CreateContractCommand command, MetaData metaData,
      UIDGenerator generator) {
    if (null == command.getIdentifier()) {
      command.setIdentifier(generator.getId());
    }
    AggregateLifecycle.apply(
        new ContractCreatedEvent(command.getIdentifier(), command.getName(), command.getPartyA(),
            command.getPartyB()), metaData);
  }

  @CommandHandler
  private void on(UpdateContractCommand command, MetaData metaData) {
    AggregateLifecycle.apply(
        new ContractUpdatedEvent(command.getIdentifier(), command.getName(), command.getPartyA(),
            command.getPartyB()), metaData);
  }

  @CommandHandler
  private void on(DeleteContractCommand command, MetaData metaData) {
    AggregateLifecycle.apply(new ContractDeletedEvent(command.getIdentifier()), metaData);
  }

  @EventSourcingHandler
  private void on(ContractCreatedEvent event) {
    this.setIdentifier(event.getIdentifier());
    this.onUpdate(event);
  }

  private void setIdentifier(Long identifier) {
    this.id = identifier;
  }

  @EventSourcingHandler
  private void onUpdate(ContractUpdatedEvent event) {
    this.setName(event.getName());
    this.setPartyA(event.getPartyA());
    this.setPartyB(event.getPartyB());
  }

  @EventSourcingHandler(payloadType = ContractDeletedEvent.class)
  private void on() {
    this.setDeleted(true);
  }

  @Override
  public Long getId() {
    return this.id;
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