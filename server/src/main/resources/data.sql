INSERT IGNORE INTO VIZO_USERS (id, username, password) VALUES (1, 'user1', '$2a$04$Ye7/lJoJin6.m9sOJZ9ujeTgHEVM4VXgI2Ingpsnf9gXyXEXf/IlW');
INSERT IGNORE INTO VIZO_USERS (id, username, password) VALUES (2, 'user2', '$2a$04$StghL1FYVyZLdi8/DIkAF./2rz61uiYPI3.MaAph5hUq03XKeflyW');
INSERT IGNORE INTO VIZO_USERS (id, username, password) VALUES (3, 'user3', '$2a$04$Lk4zqXHrHd82w5/tiMy8ru9RpAXhvFfmHOuqTmFPWQcUhBD8SSJ6W');

INSERT IGNORE INTO VIZO_ROLES (id, description, name) VALUES (4, 'Admin role', 'ADMIN');
INSERT IGNORE INTO VIZO_ROLES (id, description, name) VALUES (5, 'User role', 'USER');

INSERT IGNORE INTO VIZO_USER_ROLES (user_id, role_id) VALUES (1, 4);
INSERT IGNORE INTO VIZO_USER_ROLES (user_id, role_id) VALUES (2, 5);