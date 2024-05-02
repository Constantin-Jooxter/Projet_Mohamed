ALTER TABLE borrowing
    RENAME COLUMN borrowingdate TO startDate;
ALTER TABLE borrowing
    RENAME COLUMN returndate TO endDate;
