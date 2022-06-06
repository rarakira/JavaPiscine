CREATE SCHEMA IF NOT EXISTS chat;

DROP TABLE IF EXISTS chat.users, chat.rooms, chat.messages;

CREATE TABLE IF NOT EXISTS chat.users (
    id          SERIAL PRIMARY KEY,
    login       VARCHAR(50) UNIQUE NOT NULL,
    pass        VARCHAR(50)
);

CREATE TABLE IF NOT EXISTS chat.rooms (
    id          SERIAL PRIMARY KEY,
    name        VARCHAR(100) NOT NULL,
    owner       INT NOT NULL REFERENCES chat.users(id)
);

CREATE TABLE IF NOT EXISTS chat.messages (
    id      SERIAL PRIMARY KEY,
    author  INT REFERENCES chat.users (id),
    room    INT REFERENCES chat.rooms (id),
    message TEXT,
    time    TIMESTAMP
);