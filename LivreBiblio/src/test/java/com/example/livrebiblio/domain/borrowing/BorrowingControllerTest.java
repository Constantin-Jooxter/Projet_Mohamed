package com.example.livrebiblio.domain.borrowing;

import com.example.livrebiblio.domain.book.Book;
import com.example.livrebiblio.domain.book.BookDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDate;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = BorrowingController.class)
@ExtendWith(MockitoExtension.class)
class BorrowingControllerTest {

    @MockBean
    private BorrowingService borrowingService;

    @MockBean
    private BorrowingRepository borrowingRepository;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;


    @Test
    void should_create_borrowing_and_return_borrowing_object() throws Exception {
        // ARRANGE
        BookDTO bookDTO = new BookDTO("1234567890", "Titre du book", "Auteur du book", LocalDate.of(2024, 04, 30), "TestSynopsis", "Fantasy");

        BorrowingDTO borrowingDTO = new BorrowingDTO(1L, LocalDate.of(2024, 04, 30),
                LocalDate.of(2024, 04, 30), bookDTO, "Martin");

        BorrowingRequest borrowingRequest = new BorrowingRequest(1L, 2L,
                LocalDate.of(2024, 04, 30), LocalDate.of(2024, 05, 30));

        when(borrowingService.createBorrowing(borrowingRequest)).thenReturn(borrowingDTO);

        mockMvc.perform(post("/borrowing/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(borrowingRequest)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.start_date").value("2024-04-30"))
                .andExpect(jsonPath("$.end_date").value("2024-04-30"));

        Mockito.verify(borrowingService).createBorrowing(borrowingRequest);
    }

    @Test
    void should_patch_borrowing_and_return_borrowing_object() throws Exception {
        Long idPatch = 1L;
        BookDTO bookDTO = new BookDTO("1234567890", "Titre du book", "Auteur du book", LocalDate.of(2024, 04, 30), "TestSynopsis", "Fantasy");
        BorrowingDTO borrowingDTO = new BorrowingDTO(1L, LocalDate.of(2024, 04, 30),
                LocalDate.of(2024, 05, 30), bookDTO, "Martin");

        BorrowingRequest borrowingRequest = new BorrowingRequest(1L, 2L,
                LocalDate.of(2024, 04, 30), LocalDate.of(2024, 05, 15));

        when(borrowingService.patchBorrowingReturnDate(borrowingRequest, idPatch)).thenReturn(borrowingDTO);

        mockMvc.perform(MockMvcRequestBuilders.patch("/borrowing/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(borrowingRequest)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.start_date").value("2024-04-30"))
                .andExpect(jsonPath("$.end_date").value("2024-05-30"));

        Mockito.verify(borrowingService).patchBorrowingReturnDate(borrowingRequest, idPatch);
    }

    // GET


    @Test
    void should_return_OK_and_borrowingDTO_when_given_good_id() throws Exception {
        Long BorrowingId = 1L;
        BookDTO bookDTO = new BookDTO("1234567890", "Titre du book", "Auteur du book", LocalDate.of(2024, 04, 30), "TestSynopsis", "Fantasy");
        BorrowingDTO borrowingDTO = new BorrowingDTO(1L, LocalDate.of(2024, 04, 30),
                LocalDate.of(2024, 05, 30), bookDTO, "Martin");

        when(borrowingService.getBorrowingDTO(BorrowingId)).thenReturn(borrowingDTO);

        mockMvc.perform(get("/borrowing/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(borrowingDTO)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.start_date").value("2024-04-30"))
                .andExpect(jsonPath("$.end_date").value("2024-05-30"));
    }

}