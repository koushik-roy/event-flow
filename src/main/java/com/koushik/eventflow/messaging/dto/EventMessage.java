package com.koushik.eventflow.messaging.dto;

import java.util.UUID;

public record EventMessage(
        UUID eventId,
        String aggregateId,
        String aggregateType,
        String eventType,
        long eventVersion,
        Object eventData
) {
}
