CREATE TABLE Books
(
    id               SERIAL PRIMARY KEY,
    isbn             VARCHAR(20),
    titre            VARCHAR(255),
    auteur           VARCHAR(255),
    date_Publication TIMESTAMP,
    synopsis         TEXT
);