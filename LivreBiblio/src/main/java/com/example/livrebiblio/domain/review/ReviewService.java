package com.example.livrebiblio.domain.review;


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
public class ReviewService {

    private final ReviewRepository reviewRepository;

    private UserService userService;
    private BookService bookService;


    public ReviewDTO createReview(ReviewRequest reviewRequest) throws UserNotFoundException, BookNotFoundException {
        User user = userService.getUserByID(reviewRequest.getUserId())
                .orElseThrow(() -> new UserNotFoundException("User with ID " + reviewRequest.getUserId() + " not found"));

        Book book = bookService.getBookById(reviewRequest.getBookId())
                .orElseThrow(() -> new BookNotFoundException("Book with ID " + reviewRequest.getBookId() + " not found"));

        Reviews review = getReview(reviewRequest, user, book);

        Reviews saveReview = reviewRepository.save(review);
        return ReviewMapper.convertReviewToDTO(saveReview);
    }

    private static Reviews getReview(ReviewRequest reviewRequest, User user, Book book) {
        Reviews review = new Reviews();
        review.setUser(user);
        review.setBook(book);
        review.setRating(reviewRequest.getRating());
        review.setComment(reviewRequest.getComment());
        return review;
    }

    // GET

    public ReviewDTO getReviewRequest(Long id) throws ReviewNotFoundException {
        return reviewRepository.findById(id)
                .map(ReviewMapper::convertReviewToDTO)
                .orElseThrow(() -> new ReviewNotFoundException("Review not found"));
    }


    // Search


    public List<ReviewDTO> search(ReviewFilters reviewFilters) throws ReviewNotFoundException {
        Specification<Reviews> specification = buildSpecification(reviewFilters);
        List<Reviews> reviews = reviewRepository.findAll(specification);

        if (!reviews.isEmpty()) {
            return reviews.stream()
                    .map(ReviewMapper::convertReviewToDTO)
                    .toList();
        } else {
            throw new ReviewNotFoundException("Review not found");
        }
    }

    public Specification<Reviews> buildSpecification(ReviewFilters reviewFilters) {

        return ReviewSpecificationBuilder.builder()
                .withRating(reviewFilters.getRating())
                .withBookName(reviewFilters.getBookTitle())
                .withUserName(reviewFilters.getUserName())
                .build();
    }
}
