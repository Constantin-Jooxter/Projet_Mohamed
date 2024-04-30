ALTER TABLE borrowing
    RENAME COLUMN users_id TO fk_users_id;

ALTER TABLE borrowing
    RENAME COLUMN books_id TO fk_books_id;
