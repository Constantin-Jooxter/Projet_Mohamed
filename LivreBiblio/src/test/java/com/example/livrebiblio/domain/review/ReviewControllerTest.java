package com.example.livrebiblio.domain.review;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = ReviewController.class)
@ExtendWith(MockitoExtension.class)
class ReviewControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private ReviewService reviewService;

    @Test
    void should_return_OK_and_ReviewDTO() throws Exception {
        Long idReview = 1L;
        ReviewDTO reviewDTO = new ReviewDTO(1L, 3, "Test Comment", "Clean Code", "Mohamed");
        when(reviewService.getReviewRequest(1L)).thenReturn(reviewDTO);

        mockMvc.perform(get("/reviews/" + idReview))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.rating").value(3))
                .andExpect(jsonPath("$.comment").value("Test Comment"))
                .andExpect(jsonPath("$.book").value("Clean Code"))
                .andExpect(jsonPath("$.user").value("Mohamed"));

        Mockito.verify(reviewService).getReviewRequest(1L);
    }


}