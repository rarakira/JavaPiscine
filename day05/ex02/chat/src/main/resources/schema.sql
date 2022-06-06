CREATE SCHEMA IF NOT EXISTS chat;

DROP TABLE IF EXISTS chat.users, chat.rooms, chat.messages;

CREATE TABLE IF NOT EXISTS chat.users (
    id          BIGSERIAL PRIMARY KEY,
    login       VARCHAR(50) NOT NULL,
    pass        VARCHAR(50)
);

CREATE TABLE IF NOT EXISTS chat.rooms (
    id          BIGSERIAL PRIMARY KEY,
    name        VARCHAR(100) NOT NULL,
    owner       BIGINT NOT NULL REFERENCES chat.users(id)
);

CREATE TABLE IF NOT EXISTS chat.messages (
    id          BIGSERIAL PRIMARY KEY,
    author      BIGINT REFERENCES chat.users (id),
    room        BIGINT REFERENCES chat.rooms (id),
    message     TEXT,
    time        TIMESTAMP
);