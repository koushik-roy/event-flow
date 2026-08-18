package com.koushik.eventflow.eventStore;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;
import java.util.UUID;

@Entity
@Table(
    name = "event_store",
    uniqueConstraints = @UniqueConstraint(columnNames = {"aggregate_id", "event_version"}),
    indexes = {
        @Index(name = "idx_aggregate_version", columnList = "aggregate_id, event_version")
    }
)
@Getter
@Setter
public class EventStoreEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "event_id", nullable = false, updatable = false)
    private UUID eventId;

    @Column(name = "aggregate_id", nullable = false)
    private String aggregateId;

    @Column(name = "aggregate_type", nullable = false)
    private String aggregateType;

    @Column(name = "event_type", nullable = false)
    private String eventType;

    @Min(1)
    @Column(name = "event_version", nullable = false)
    private long eventVersion;

    /** Stored as JSONB in the database. Kept as String here; consider using a JSON mapping type if available. */
    @Column(name = "event_data", nullable = false, columnDefinition = "jsonb")
    private String eventData;


    @Column(name = "created_at", nullable = false, updatable = false, columnDefinition = "timestamp with time zone DEFAULT CURRENT_TIMESTAMP")
    private OffsetDateTime createdAt;
}
