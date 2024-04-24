CREATE TABLE Author
(
    id       SERIAL PRIMARY KEY,
    name     VARCHAR(255),
    surname  VARCHAR(255),
    birthday DATE,
    ownBooks VARCHAR(255)
);
