CREATE TABLE borrowing
(
    id            SERIAL PRIMARY KEY,
    users_id      INT,
    books_id      INT,
    borrowingDate DATE,
    returnDate    DATE,
    FOREIGN KEY (users_id) REFERENCES Users (id),
    FOREIGN KEY (books_id) REFERENCES Books (id)
);
