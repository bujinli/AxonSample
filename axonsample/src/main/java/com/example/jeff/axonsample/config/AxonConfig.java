package com.example.jeff.axonsample.config;

import com.example.jeff.axonsample.aggregate.contract.ContractAggregate;
import com.example.jeff.axonsample.customeventstore.CustomEmbeddedEventStore;
import com.example.jeff.axonsample.customeventstore.CustomEventSourcingRepository;
import com.example.jeff.axonsample.customeventstore.upcaster.ContractEventUpCaster;
import org.axonframework.common.jdbc.PersistenceExceptionResolver;
import org.axonframework.common.jpa.EntityManagerProvider;
import org.axonframework.common.transaction.TransactionManager;
import org.axonframework.config.EventProcessingConfigurer;
import org.axonframework.eventsourcing.AggregateSnapshotter;
import org.axonframework.eventsourcing.EventCountSnapshotTriggerDefinition;
import org.axonframework.eventsourcing.GenericAggregateFactory;
import org.axonframework.eventsourcing.SnapshotTriggerDefinition;
import org.axonframework.eventsourcing.Snapshotter;
import org.axonframework.eventsourcing.eventstore.EventStorageEngine;
import org.axonframework.eventsourcing.eventstore.EventStore;
import org.axonframework.eventsourcing.eventstore.jpa.JpaEventStorageEngine;
import org.axonframework.messaging.annotation.ParameterResolverFactory;
import org.axonframework.serialization.Serializer;
import org.axonframework.serialization.upcasting.event.EventUpcaster;
import org.axonframework.spring.config.AxonConfiguration;
import org.axonframework.springboot.util.RegisterDefaultEntities;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Collections;

@Configuration
@RegisterDefaultEntities(packages = {"org.axonframework.eventsourcing.eventstore.jpa"})
public class AxonConfig {
  @Bean
  public CustomEmbeddedEventStore eventStore(EventStorageEngine storageEngine,
      AxonConfiguration configuration) {
    return CustomEmbeddedEventStore.builder().storageEngine(storageEngine)
        .messageMonitor(configuration.messageMonitor(EventStore.class, "eventStore")).build();
  }

  @Bean
  public CustomEventSourcingRepository<ContractAggregate> contractAggregateRepository(
      CustomEmbeddedEventStore eventStore, SnapshotTriggerDefinition snapshotTriggerDefinition,
      ParameterResolverFactory parameterResolverFactory) {
    return CustomEventSourcingRepository.builder(ContractAggregate.class).eventStore(eventStore)
        .snapshotTriggerDefinition(snapshotTriggerDefinition)
        .parameterResolverFactory(parameterResolverFactory).build();
  }

  @Bean
  public SnapshotTriggerDefinition snapshotTriggerDefinition(Snapshotter snapshotter) {
    return new EventCountSnapshotTriggerDefinition(snapshotter, 5);
  }

  @Bean
  public AggregateSnapshotter snapShotter(CustomEmbeddedEventStore eventStore,
      ParameterResolverFactory parameterResolverFactory) {
    return AggregateSnapshotter.builder().eventStore(eventStore)
        .parameterResolverFactory(parameterResolverFactory).aggregateFactories(
            Collections.singletonList(new GenericAggregateFactory<>(ContractAggregate.class)))
        .build();
  }

  @Bean
  public EventProcessingConfigurer eventProcessingConfigurer(
      EventProcessingConfigurer eventProcessingConfigurer) {
    eventProcessingConfigurer.usingSubscribingEventProcessors();
    return eventProcessingConfigurer;
  }

  @Bean
  public EventStorageEngine eventStorageEngine(Serializer defaultSerializer,
      PersistenceExceptionResolver persistenceExceptionResolver,
      @Qualifier("eventSerializer") Serializer eventSerializer,
      EntityManagerProvider entityManagerProvider, EventUpcaster contractUpCaster,
      TransactionManager transactionManager) {
    return JpaEventStorageEngine.builder().snapshotSerializer(defaultSerializer)
        .upcasterChain(contractUpCaster).persistenceExceptionResolver(persistenceExceptionResolver)
        .eventSerializer(eventSerializer).entityManagerProvider(entityManagerProvider)
        .transactionManager(transactionManager).build();
  }

  @Bean
  public EventUpcaster contractUpCaster() {
    return new ContractEventUpCaster();
  }
}
