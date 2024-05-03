package com.example.livrebiblio.domain.book;

public class BookMapper {

    public static BookDTO convertToBookDTO(Book book) {
        String authorName = book.getAuthor() != null ? book.getAuthor().getName() : null;
        return new BookDTO(
                book.getIsbn(),
                book.getTitle(),
                authorName,
                book.getDatePublication(),
                book.getSynopsis(),
                book.getType()
        );
    }
}
