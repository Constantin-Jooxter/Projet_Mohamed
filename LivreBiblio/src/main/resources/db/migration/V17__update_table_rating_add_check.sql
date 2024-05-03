ALTER TABLE reviews
    ADD CONSTRAINT chk_rating CHECK (rating >= 1 AND rating <= 5);