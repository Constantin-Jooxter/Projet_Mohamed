ALTER TABLE Books
DROP
COLUMN auteur,
ADD COLUMN author_id INTEGER,
ADD CONSTRAINT fk_author
    FOREIGN KEY (author_id)
    REFERENCES Author(id);
