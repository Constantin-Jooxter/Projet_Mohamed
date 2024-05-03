CREATE TABLE reviews
(
    id       SERIAL PRIMARY KEY,
    users_id INT,
    books_id INT,
    rating   INT,
    comment  VARCHAR(255),
    FOREIGN KEY (users_id) REFERENCES "user" (id),
    FOREIGN KEY (books_id) REFERENCES Books (id)
);
