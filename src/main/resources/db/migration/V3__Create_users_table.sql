CREATE TABLE users
(
    id       INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(100) NOT NULL,
    password VARCHAR(100) NOT NULL,
    email    VARCHAR(255) NOT NULL,
    active   BIT          NOT NULL,
    is_admin BIT          NOT NULL
);
INSERT INTO users (username, password, email, active, is_admin)
VALUES ('john_doe', '$2a$10$6FQHJiWQJ0pz2gwQ0d3L3eu7OacqvD0bVeobpGXiODsH3BWcQ5rVy', 'john@example.com', b'1', b'0'),
       ('jane_admin', '$2a$10$6FQHJiWQJ0pz2gwQ0d3L3eu7OacqvD0bVeobpGXiODsH3BWcQ5rVy', 'jane@example.com', b'1', b'1'),
       ('alice_user', '$2a$10$6FQHJiWQJ0pz2gwQ0d3L3eu7OacqvD0bVeobpGXiODsH3BWcQ5rVy', 'alice@example.com', b'1', b'0'),
       ('bob_smith', '$2a$10$6FQHJiWQJ0pz2gwQ0d3L3eu7OacqvD0bVeobpGXiODsH3BWcQ5rVy', 'bob@example.com', b'1', b'0'),
       ('charlie_admin', '$2a$10$6FQHJiWQJ0pz2gwQ0d3L3eu7OacqvD0bVeobpGXiODsH3BWcQ5rVy', 'charlie@example.com', b'1', b'1'),
       ('dave_user', '$2a$10$6FQHJiWQJ0pz2gwQ0d3L3eu7OacqvD0bVeobpGXiODsH3BWcQ5rVy', 'dave@example.com', b'1', b'0'),
       ('ellen_manager', '$2a$10$6FQHJiWQJ0pz2gwQ0d3L3eu7OacqvD0bVeobpGXiODsH3BWcQ5rVy', 'ellen@example.com', b'1', b'1'),
       ('frank_guest', '$2a$10$6FQHJiWQJ0pz2gwQ0d3L3eu7OacqvD0bVeobpGXiODsH3BWcQ5rVy', 'frank@example.com', b'1', b'0'),
       ('george_super', '$2a$10$6FQHJiWQJ0pz2gwQ0d3L3eu7OacqvD0bVeobpGXiODsH3BWcQ5rVy', 'george@example.com', b'1', b'1'),
       ('helen_user', '$2a$10$6FQHJiWQJ0pz2gwQ0d3L3eu7OacqvD0bVeobpGXiODsH3BWcQ5rVy', 'helen@example.com', b'1', b'0');
