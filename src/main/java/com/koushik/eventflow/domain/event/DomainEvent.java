package com.koushik.eventflow.domain.event;

import java.util.UUID;

public interface DomainEvent {
    UUID eventId();

    String aggregateId();

    long eventVersion();

    String aggregateType();
}
