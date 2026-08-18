-- Flyway migration: create event_store table
-- Creates pgcrypto extension for gen_random_uuid()

CREATE EXTENSION IF NOT EXISTS "pgcrypto";

CREATE TABLE IF NOT EXISTS event_store (
  event_id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
  aggregate_id VARCHAR NOT NULL,
  aggregate_type VARCHAR NOT NULL,
  event_type VARCHAR NOT NULL,
  event_version BIGINT NOT NULL CHECK (event_version > 0),
  event_data JSONB NOT NULL,
  created_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT now()
);

ALTER TABLE event_store
  ADD CONSTRAINT uq_event_store_aggregate_version UNIQUE (aggregate_id, event_version);

CREATE INDEX IF NOT EXISTS idx_event_store_aggregate_version ON event_store (aggregate_id, event_version);
