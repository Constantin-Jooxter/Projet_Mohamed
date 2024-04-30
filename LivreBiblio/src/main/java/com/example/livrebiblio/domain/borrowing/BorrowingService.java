package com.example.livrebiblio.domain.borrowing;

import com.example.livrebiblio.domain.book.Book;
import com.example.livrebiblio.domain.book.BookNotFoundException;
import com.example.livrebiblio.domain.book.BookRepository;
import com.example.livrebiblio.domain.users.UserNotFoundException;
import com.example.livrebiblio.domain.users.UserRepository;
import com.example.livrebiblio.domain.users.Users;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class BorrowingService {
    private BorrowingRepository borrowingRepository;
    private UserRepository userRepository;
    private BookRepository bookRepository;

    public BorrowingDTO createBorrowing(BorrowingRequest borrowingRequest) throws UserNotFoundException, BookNotFoundException {
        Users user = getUserById(borrowingRequest.getUserId());
        Book book = getBookById(borrowingRequest.getBookId());

        Borrowing borrowing = new Borrowing();
        borrowing.setUsers(user); // Set the user directly on the borrowing
        borrowing.setBook(book);
        borrowing.setBorrowingdate(borrowingRequest.getBorrowDate());
        borrowing.setReturndate(borrowingRequest.getReturnDate());

        Borrowing savedBorrowing = borrowingRepository.save(borrowing);

        return BorrowingMapper.convertToDTO(savedBorrowing);
    }


    public Users getUserById(Long userId) throws UserNotFoundException {
        return userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found with id: " + userId));
    }

    public Book getBookById(Long bookId) throws BookNotFoundException {
        return bookRepository.findById(bookId)
                .orElseThrow(() -> new BookNotFoundException("Book not found with id: " + bookId));
    }
}
