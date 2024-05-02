package com.example.livrebiblio.domain.borrowing;

import com.example.livrebiblio.domain.book.Book;
import com.example.livrebiblio.domain.book.BookNotFoundException;
import com.example.livrebiblio.domain.book.BookService;
import com.example.livrebiblio.domain.users.User;
import com.example.livrebiblio.domain.users.UserNotFoundException;
import com.example.livrebiblio.domain.users.UserService;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

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
                .orElseThrow(() -> new BookNotFoundException("Book with ID " + borrowingRequest.getBookId() + " not found"));

        Borrowing borrowing = getBorrowing(borrowingRequest, user, book);

        Borrowing savedBorrowing = borrowingRepository.save(borrowing);
        return BorrowingMapper.convertToDTO(savedBorrowing);
    }

    private static Borrowing getBorrowing(BorrowingRequest borrowingRequest, User user, Book book) {
        Borrowing borrowing = new Borrowing();
        borrowing.setUser(user);
        borrowing.setBook(book);
        borrowing.setStart_date(borrowingRequest.getStart_date());
        borrowing.setEnd_date(borrowingRequest.getEnd_date());
        return borrowing;
    }


    // PUT

    public BorrowingDTO patchBorrowingReturnDate(BorrowingRequest borrowingRequest, Long borrowingId) throws BorrowingNotFoundException {
        Borrowing borrowing = borrowingRepository.findById(borrowingId)
                .orElseThrow(() -> new BorrowingNotFoundException("Borrowing with ID " + borrowingId + " not found"));

        Borrowing updatedBorrowing = getBorrowing(borrowingRequest, borrowing);

        return BorrowingMapper.convertToDTO(updatedBorrowing);
    }

    private Borrowing getBorrowing(BorrowingRequest borrowingRequest, Borrowing borrowing) {
        if (borrowing.getEnd_date() != null) {
            borrowing.setEnd_date(borrowingRequest.getEnd_date());
        }
        if (borrowing.getStart_date() != null) {
            borrowing.setStart_date(borrowingRequest.getStart_date());
        }
        if (borrowing.getBook() != null) {
            borrowing.setBook(borrowing.getBook());
        }
        if (borrowing.getUser() != null) {
            borrowing.setUser(borrowing.getUser());
        }

        return borrowingRepository.save(borrowing);
    }

    public BorrowingDTO getBorrowingDTO(Long borrowingId) throws BorrowingNotFoundException {
        return borrowingRepository.findById(borrowingId)
                .map(BorrowingMapper::convertToDTO)
                .orElseThrow(() -> new BorrowingNotFoundException("Borrowing with ID " + borrowingId + " not found"));
    }

    // SEARCH


    public List<BorrowingDTO> search(BorrowingFilters borrowingFilters) throws BorrowingNotFoundException {
        Specification<Borrowing> specification = buildSpecification(borrowingFilters);
        List<Borrowing> borrowings = borrowingRepository.findAll(specification);

        if (!borrowings.isEmpty()) {
            return borrowings.stream()
                    .map(BorrowingMapper::convertToDTO)
                    .toList();
        } else {
            throw new BorrowingNotFoundException("Borrowing not found");
        }
    }

    public Specification<Borrowing> buildSpecification(BorrowingFilters borrowingFilters) {

        return BorrowingSpecificationBuilder.builder()
                .withBorrowingDate(borrowingFilters.getStart_date())
                .withReturnDate(borrowingFilters.getEnd_date())
                .withUserName(borrowingFilters.getUserName())
                .withBookName(borrowingFilters.getBookTitle())
                .witheBeforeDate(borrowingFilters.getBeforeDate())
                .witheAfterDate(borrowingFilters.getAfterDate())
                .build();
    }
}