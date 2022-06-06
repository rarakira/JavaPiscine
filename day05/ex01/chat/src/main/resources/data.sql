INSERT INTO chat.users (login, pass) VALUES ('Mike', 'SecretPass');
INSERT INTO chat.users (login, pass) VALUES ('Nick', 'SecretPass2');
INSERT INTO chat.users (login, pass) VALUES ('Jack', 'NoSecret');
INSERT INTO chat.users (login, pass) VALUES ('Mary', '123456');
INSERT INTO chat.users (login, pass) VALUES ('X-man', '654321');

INSERT INTO chat.rooms (name, owner) VALUES ('Cosy room', 4);
INSERT INTO chat.rooms (name, owner) VALUES ('Dark room', 5);
INSERT INTO chat.rooms (name, owner) VALUES ('Random', 1);
INSERT INTO chat.rooms (name, owner) VALUES ('General', 2);

INSERT INTO chat.messages (author, room, message, time) VALUES (4, 1, 'Hi everyone', '2022-04-24 19:10:25-07');
INSERT INTO chat.messages (author, room, message, time) VALUES (3, 1, 'Hello to you too', '2022-04-24 19:10:35-07');
INSERT INTO chat.messages (author, room, message, time) VALUES (2, 1, 'Hi!', '2022-04-24 19:10:37-07');
INSERT INTO chat.messages (author, room, message, time) VALUES (4, 1, 'How are you?', '2022-04-24 19:11:02-07');
INSERT INTO chat.messages (author, room, message, time) VALUES (1, 3, 'Have a look at this article - link!', '2022-04-24 19:11:25-07');
INSERT INTO chat.messages (author, room, message, time) VALUES (2, 3, 'Do you guys have pets?', '2022-04-24 19:12:25-07');
INSERT INTO chat.messages (author, room, message, time) VALUES (1, 3, 'I really like Britney Spears...', '2022-04-24 19:13:25-07');
INSERT INTO chat.messages (author, room, message, time) VALUES (2, 3, 'Hi everyone', '2022-04-24 19:14:25-07');
INSERT INTO chat.messages (author, room, message, time) VALUES (3, 2, 'Turn off the lights!', '2022-04-24 19:15:25-07');
INSERT INTO chat.messages (author, room, message, time) VALUES (5, 3, 'I lost my pen, have you seen it?', '2022-04-24 19:16:25-07');
INSERT INTO chat.messages (author, room, message, time) VALUES (3, 2, 'Cant see you', '2022-04-24 19:17:25-07');
INSERT INTO chat.messages (author, room, message, time) VALUES (4, 1, 'You want some tea?', '2022-04-24 19:18:25-07');
INSERT INTO chat.messages (author, room, message, time) VALUES (1, 1, 'Yes please', '2022-04-24 19:19:25-07');