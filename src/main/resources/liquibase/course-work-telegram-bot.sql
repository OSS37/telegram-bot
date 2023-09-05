--liquibase formatted sql

--changeset Sukhanova:1
CREATE TABLE notification_task
(
    id SERIAL PRIMARY KEY,
    chatId BIGINT,
    textMessage TEXT,
    sendingTime TIMESTAMP
);