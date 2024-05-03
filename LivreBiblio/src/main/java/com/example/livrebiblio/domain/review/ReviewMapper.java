package com.example.livrebiblio.domain.review;

public class ReviewMapper {
    public static ReviewDTO convertReviewToDTO(Reviews review) {
        String userName = review.getUser() != null ? review.getUser().getName() : null;
        String bookTitle = review.getBook() != null ? review.getBook().getTitle() : null;
        return new ReviewDTO(
                review.getId(),
                review.getRating(),
                review.getComment(),
                //BookMapper.convertToBookDTO(review.getBook()),
                bookTitle,
                userName
        );
    }
}


