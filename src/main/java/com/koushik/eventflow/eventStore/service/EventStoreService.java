package com.koushik.eventflow.eventStore.service;

import com.koushik.eventflow.eventStore.entity.EventStoreEntity;
import com.koushik.eventflow.eventStore.eventStoreMapper.EventStoreMapper;
import com.koushik.eventflow.eventStore.repository.EventStoreRepository;
import com.koushik.eventflow.order.domain.event.DomainEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class EventStoreService {

    private final EventStoreMapper eventStoreMapper;
    private final EventStoreRepository eventStoreRepository;

    public void persist(DomainEvent domainEvent) {
        EventStoreEntity entity =
                eventStoreMapper.toEventStoreEntity(domainEvent);

        eventStoreRepository.save(entity);
    }

}
