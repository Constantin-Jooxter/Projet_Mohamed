package com.example.livrebiblio.domain.borrowing;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(controllers = BorrowingController.class)
@ExtendWith(MockitoExtension.class)
class BorrowingControllerTest {

    @MockBean
    BorrowingService borrowingService;

    @Mock
    BorrowingController borrowingController;
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    /*
    @Test
    void should_create_borrowing_and_return_borrowing_object() throws Exception {
        // ARRANGE
        BorrowingDTO borrowingDTO = new BorrowingDTO(1L, LocalDate.of(2024, 04, 30),
                LocalDate.of(2024, 04, 30), book);

        BorrowingRequest borrowingRequest = new BorrowingRequest(1L, 2L,
                LocalDate.of(2024, 04, 30), LocalDate.of(2024, 05, 30));

        when(borrowingService.createBorrowing(borrowingRequest)).thenReturn(borrowingDTO);

        mockMvc.perform(MockMvcRequestBuilders.post("/borrowing/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(borrowingRequest)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.borrowDate").value("2024-04-30"))
                .andExpect(jsonPath("$.returnDate").value("2024-04-30"));

        Mockito.verify(borrowingService).createBorrowing(borrowingRequest);
    }

    @Test
    void should_patch_borrowing_and_return_borrowing_object() throws Exception {
        Long idPatch = 1L;
        BorrowingDTO borrowingDTO = new BorrowingDTO(1L, LocalDate.of(2024, 04, 30),
                LocalDate.of(2024, 05, 30));

        BorrowingRequest borrowingRequest = new BorrowingRequest(1L, 2L,
                LocalDate.of(2024, 04, 30), LocalDate.of(2024, 05, 15));

        when(borrowingService.patchBorrowingReturnDate(borrowingRequest, idPatch)).thenReturn(borrowingDTO);

        mockMvc.perform(MockMvcRequestBuilders.patch("/borrowing/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(borrowingRequest)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.borrowDate").value("2024-04-30"))
                .andExpect(jsonPath("$.returnDate").value("2024-05-30"));

        Mockito.verify(borrowingService).patchBorrowingReturnDate(borrowingRequest, idPatch);
    }*/
}