package com.example.livrebiblio.domain.borrowing;

import com.example.livrebiblio.domain.book.Book;
import com.example.livrebiblio.domain.book.BookNotFoundException;
import com.example.livrebiblio.domain.book.BookService;
import com.example.livrebiblio.domain.users.User;
import com.example.livrebiblio.domain.users.UserNotFoundException;
import com.example.livrebiblio.domain.users.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BorrowingServiceTest {

    @Mock
    private BorrowingRepository borrowingRepository;

    @Mock
    private UserService userService;

    @Mock
    private BookService bookService;

    @InjectMocks
    private BorrowingService borrowingService;

    @Test
    public void testCreateBorrowing() throws UserNotFoundException, BookNotFoundException {
        // Arrange
        BorrowingRequest borrowingRequest = new BorrowingRequest();
        borrowingRequest.setUserId(2L);
        borrowingRequest.setBookId(31L);
        borrowingRequest.setBorrowDate(LocalDate.of(2024, 04, 15));
        borrowingRequest.setReturnDate(LocalDate.of(2024, 04, 30));

        User user = new User();
        user.setId(2L);
        Book book = new Book();
        book.setId(31L);

        Borrowing expectedBorrowing = new Borrowing();
        expectedBorrowing.setUser(user);
        expectedBorrowing.setBook(book);
        expectedBorrowing.setBorrowingdate(LocalDate.of(2024, 4, 15));
        expectedBorrowing.setReturndate(LocalDate.of(2024, 4, 30));

        when(borrowingRepository.save(expectedBorrowing)).thenReturn(expectedBorrowing);

        when(userService.getUserByID(eq(2L))).thenReturn(user);
        when(bookService.getBookById(eq(31L))).thenReturn(book);

        // Act
        BorrowingDTO result = borrowingService.createBorrowing(borrowingRequest);

        // Assert
        verify(borrowingRepository).save(eq(expectedBorrowing));

        Assertions.assertNotNull(result);
    }
}