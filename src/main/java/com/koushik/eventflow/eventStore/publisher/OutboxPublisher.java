package com.koushik.eventflow.eventStore.publisher;

import com.koushik.eventflow.eventStore.entity.OutboxEntity;
import com.koushik.eventflow.eventStore.mapper.OutboxMapper;
import com.koushik.eventflow.eventStore.repository.OutboxRepository;
import com.koushik.eventflow.messaging.dto.EventMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Component
@RequiredArgsConstructor
public class OutboxPublisher {

    private final OutboxRepository outboxRepository;
    private final OutboxMapper outboxMapper;
    private final KafkaTemplate<String, EventMessage> kafkaTemplate;

    @Value("${eventflow.kafka.topic}")
    private String topic;

    @Scheduled(fixedDelayString = "${eventflow.publisher.outbox}")
    public void publish() {

        List<OutboxEntity> events =
                outboxRepository.findByPublishedAtIsNull();

        for (OutboxEntity entity : events) {
            EventMessage message =
                    outboxMapper.toEventMessage(entity);

            CompletableFuture<SendResult<String, EventMessage>> future =
                    kafkaTemplate.send(
                            topic,
                            entity.getAggregateId(),
                            message
                    );

            future.whenComplete((result, exception) -> {
                if (exception == null) {
                    System.out.println("Successfully published event: "+entity.getEventType());
                    entity.setPublishedAt(OffsetDateTime.now());
                    outboxRepository.save(entity);
                } else {

                    System.err.println(
                            "Failed to publish event "
                                    + entity.getEventId()
                                    + ": "
                                    + exception.getMessage()
                    );
                }
            });
        }
    }
}