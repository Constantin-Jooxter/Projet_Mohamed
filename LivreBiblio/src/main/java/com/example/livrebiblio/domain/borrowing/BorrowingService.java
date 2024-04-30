package com.example.livrebiblio.domain.borrowing;

import com.example.livrebiblio.domain.book.Book;
import com.example.livrebiblio.domain.book.BookNotFoundException;
import com.example.livrebiblio.domain.book.BookService;
import com.example.livrebiblio.domain.users.User;
import com.example.livrebiblio.domain.users.UserNotFoundException;
import com.example.livrebiblio.domain.users.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class BorrowingService {
    private BorrowingRepository borrowingRepository;

    private UserService userService;
    private BookService bookService;


    public BorrowingDTO createBorrowing(BorrowingRequest borrowingRequest) throws UserNotFoundException, BookNotFoundException {
        User user = userService.getUserByID(borrowingRequest.getUserId())
                .orElseThrow(() -> new UserNotFoundException("User with ID " + borrowingRequest.getUserId() + " not found"));

        Book book = bookService.getBookById(borrowingRequest.getBookId())
                .orElseThrow(() -> new BookNotFoundException("Book not found"));

        Borrowing borrowing = getBorrowing(borrowingRequest, user, book);

        Borrowing savedBorrowing = borrowingRepository.save(borrowing);
        return BorrowingMapper.convertToDTO(savedBorrowing);
    }

    private static Borrowing getBorrowing(BorrowingRequest borrowingRequest, User user, Book book) {
        Borrowing borrowing = new Borrowing();
        borrowing.setUser(user);
        borrowing.setBook(book);
        borrowing.setBorrowingdate(borrowingRequest.getBorrowDate());
        borrowing.setReturndate(borrowingRequest.getReturnDate());
        return borrowing;
    }


    // PUT


}



/*public BorrowingDTO createBorrowing(BorrowingRequest borrowingRequest) throws UserNotFoundException, BookNotFoundException {

        Optional<User> userByID = userService.getUserByID(borrowingRequest.getUserId());
        Optional<Book> bookById = bookService.getBookById(borrowingRequest.getBookId());

        if (userByID.isPresent()) {
            if (bookById.isPresent()) {
                Borrowing borrowing = getBorrowing(borrowingRequest, userByID.get(), bookById.get());

                Borrowing savedBorrowing = borrowingRepository.save(borrowing);

                return BorrowingMapper.convertToDTO(savedBorrowing);
            } else {
                throw new BookNotFoundException("Book not found");
            }
        } else {
            throw new UserNotFoundException("User with ID " + borrowingRequest.getUserId() + " not found");
        }
    }*/
