package com.example.livrebiblio.domain.review;


import com.example.livrebiblio.domain.book.BookNotFoundException;
import com.example.livrebiblio.domain.users.UserNotFoundException;
import lombok.AllArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/reviews")
public class ReviewController {

    private ReviewService reviewService;

    @PostMapping
    public ResponseEntity<ReviewDTO> createMyBorrowing(@RequestBody ReviewRequest reviewRequest) throws UserNotFoundException, BookNotFoundException {
        return ResponseEntity.ok().body(reviewService.createReview(reviewRequest));
    }

    /*@GetMapping("/{id}")
    public ResponseEntity<ReviewDTO> getReviewById(@PathVariable Long id) throws ReviewNotFoundException {
        return ResponseEntity.ok().body(reviewService.getReviewRequest(id));
    }*/

    @GetMapping("/{id}")
    public ResponseEntity<ReviewDTO> getReviewById(@PathVariable Long id) throws ReviewNotFoundException {
        ReviewDTO reviewDTO = reviewService.getReviewRequest(id);
        return ResponseEntity.ok(reviewDTO);
    }

    @GetMapping("/search")
    public ResponseEntity<List<ReviewDTO>> searchBooks(@ParameterObject ReviewFilters reviewFilters) throws ReviewNotFoundException {
        List<ReviewDTO> reviewDTOS = reviewService.search(reviewFilters);
        return ResponseEntity.ok().body(reviewDTOS);
    }
}
