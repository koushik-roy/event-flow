package com.koushik.eventflow.eventStore.repository;

import com.koushik.eventflow.eventStore.entity.OutboxEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface OutboxRepository
        extends JpaRepository<OutboxEntity, UUID> {

    List<OutboxEntity> findByPublishedAtIsNull();
}
