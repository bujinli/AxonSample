package com.example.jeff.axonsample.query;

import com.example.jeff.axonsample.aggregate.contract.ContractAggregate;
import com.example.jeff.axonsample.customeventstore.CustomEventSourcingRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.eventsourcing.EventSourcedAggregate;
import org.axonframework.modelling.command.LockAwareAggregate;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@Slf4j
public class ContractQueryHandler {

  private final CustomEventSourcingRepository<ContractAggregate> contractAggregateRepository;

  @QueryHandler
  public ContractAggregate on(QueryContractCommand command) {
    LockAwareAggregate<ContractAggregate, EventSourcedAggregate<ContractAggregate>>
        lockAwareAggregate =
        contractAggregateRepository.load(command.getId().toString(), command.getEndDate());
    return lockAwareAggregate.getWrappedAggregate().getAggregateRoot();
  }
}
