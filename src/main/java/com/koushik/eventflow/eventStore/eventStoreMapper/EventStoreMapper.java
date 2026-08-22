package com.koushik.eventflow.eventStore.eventStoreMapper;

import com.koushik.eventflow.eventStore.entity.EventStoreEntity;
import com.koushik.eventflow.domain.event.DomainEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.ObjectMapper;

import java.time.OffsetDateTime;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class EventStoreMapper {

    private final ObjectMapper objectMapper;

    public EventStoreEntity toEventStoreEntity(DomainEvent domainEvent) {

        Map<String, Object> eventData = objectMapper.convertValue(
                domainEvent,
                new TypeReference<Map<String, Object>>() {
                }
        );

        eventData.remove("aggregateId");
        eventData.remove("eventVersion");

        return EventStoreEntity.builder()
                .aggregateId(domainEvent.aggregateId())
                .aggregateType(domainEvent.aggregateType())
                .eventType(domainEvent.getClass().getSimpleName())
                .eventVersion(domainEvent.eventVersion())
                .eventData(eventData)
                .createdAt(OffsetDateTime.now())
                .build();
    }

}
