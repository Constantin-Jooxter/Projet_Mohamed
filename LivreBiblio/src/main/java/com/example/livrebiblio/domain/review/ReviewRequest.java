package com.example.livrebiblio.domain.review;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReviewRequest {
    private Long userId;
    private Long bookId;
    private String comment;
    private Integer rating;
}
