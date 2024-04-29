CREATE TABLE users
(
    id           SERIAL PRIMARY KEY,
    name         VARCHAR(255),
    surname      VARCHAR(255),
    adress       TEXT,
    registration DATE,
    borrowing    DATE
);