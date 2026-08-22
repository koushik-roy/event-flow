package com.koushik.eventflow.domain.event;

public interface DomainEvent {

    String aggregateId();

    long eventVersion();

    String aggregateType();
}
