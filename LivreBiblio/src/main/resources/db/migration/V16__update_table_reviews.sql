ALTER TABLE reviews
    RENAME COLUMN users_id TO fk_users_id;

ALTER TABLE reviews
    RENAME COLUMN books_id TO fk_books_id;
