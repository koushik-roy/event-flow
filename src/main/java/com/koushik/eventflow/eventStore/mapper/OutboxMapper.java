package com.koushik.eventflow.eventStore.mapper;

import com.koushik.eventflow.eventStore.entity.OutboxEntity;
import com.koushik.eventflow.domain.event.DomainEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.ObjectMapper;

import java.time.OffsetDateTime;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class OutboxMapper {

    private final ObjectMapper objectMapper;

    public OutboxEntity toOutboxEntity(DomainEvent domainEvent) {

        Map<String, Object> eventData = objectMapper.convertValue(
                domainEvent,
                new TypeReference<Map<String, Object>>() {
                }
        );

        eventData.remove("aggregateId");
        eventData.remove("eventVersion");
        eventData.remove("aggregateType");

        return OutboxEntity.builder()
                .eventId(domainEvent.eventId())
                .aggregateId(domainEvent.aggregateId())
                .aggregateType(domainEvent.aggregateType())
                .eventType(domainEvent.getClass().getSimpleName())
                .eventVersion(domainEvent.eventVersion())
                .eventData(eventData)
                .createdAt(OffsetDateTime.now())
                .publishedAt(null)
                .build();
    }
}