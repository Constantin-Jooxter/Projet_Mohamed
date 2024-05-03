package com.example.livrebiblio.domain.review;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ReviewDTO {
    private Long id;
    private Integer rating;
    private String comment;
    private String book;
    private String user;
}
