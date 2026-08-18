package com.koushik.eventflow.repository;

import com.koushik.eventflow.eventStore.EventStoreEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface EventStoreRepository extends JpaRepository<UUID, EventStoreEntity> {

    List<EventStoreEntity> findByAggregateIdOrderByEventVersionAsc(String aggregateId);
}
