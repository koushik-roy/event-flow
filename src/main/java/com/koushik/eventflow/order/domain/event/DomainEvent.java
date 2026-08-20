package com.koushik.eventflow.order.domain.event;

public interface DomainEvent {

    String aggregateId();

    long eventVersion();

    String aggregateType();
}
