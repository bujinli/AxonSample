package com.example.jeff.axonsample.customeventstore.upcaster;

import com.example.jeff.axonsample.event.contract.ContractCreatedEvent;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.axonframework.messaging.MetaData;
import org.axonframework.serialization.upcasting.event.IntermediateEventRepresentation;

import java.util.HashMap;

public class ContractCreatedEventUpCaster extends SameEventUpCaster {

  @Override
  public String eventTypeName() {
    return ContractCreatedEvent.class.getTypeName();
  }

  @Override
  public String outputRevision(String originRevision) {
    final HashMap<String, String> revisionConvertMpp = new HashMap<>();
    revisionConvertMpp.put(null, "1.0.0");

    return revisionConvertMpp.get(originRevision);
  }

  @Override
  public JsonNode doUpCastPayload(JsonNode document,
      IntermediateEventRepresentation intermediateEventRepresentation) {

    if (intermediateEventRepresentation.getType().getRevision() == null) {
      ((ObjectNode) document).put("industryName", "internet");
    }

    return document;
  }

  @Override
  public MetaData doUpCastMetaData(MetaData document,
      IntermediateEventRepresentation intermediateEventRepresentation) {
    return document;
  }
}