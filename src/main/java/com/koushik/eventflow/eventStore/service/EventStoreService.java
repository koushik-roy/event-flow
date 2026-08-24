package com.koushik.eventflow.eventStore.service;

import com.koushik.eventflow.eventStore.entity.EventStoreEntity;
import com.koushik.eventflow.eventStore.entity.OutboxEntity;
import com.koushik.eventflow.eventStore.mapper.EventStoreMapper;
import com.koushik.eventflow.eventStore.mapper.OutboxMapper;
import com.koushik.eventflow.eventStore.repository.EventStoreRepository;
import com.koushik.eventflow.domain.event.DomainEvent;
import com.koushik.eventflow.eventStore.repository.OutboxRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
public class EventStoreService {

    private final EventStoreMapper eventStoreMapper;
    private final EventStoreRepository eventStoreRepository;

    private final OutboxMapper outboxMapper;
    private final OutboxRepository outboxRepository;

    @Transactional
    public void persist(DomainEvent domainEvent) {
        EventStoreEntity eventStoreEntity =
                eventStoreMapper.toEventStoreEntity(domainEvent);

        OutboxEntity outboxEntity =
                outboxMapper.toOutboxEntity(domainEvent);

        eventStoreRepository.save(eventStoreEntity);
        outboxRepository.save(outboxEntity);
    }

}
