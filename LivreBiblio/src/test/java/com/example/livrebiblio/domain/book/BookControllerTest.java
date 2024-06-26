package com.example.livrebiblio.domain.book;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.Instant;
import java.time.LocalDate;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = BookController.class)
@ExtendWith(MockitoExtension.class)
public class BookControllerTest {

    private static final Logger log = LoggerFactory.getLogger(BookControllerTest.class);
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private BookService bookService;

    @Test
    void should_return_OK_and_bookDTO() throws Exception {
        LocalDate expectedDate = LocalDate.of(2024, 04, 30);
        BookDTO bookDTO = new BookDTO("1234567890", "Titre du book", "Auteur du book", LocalDate.of(2024, 04, 30), "TestSynopsis", "Fantasy");
        when(bookService.getBookByIdDTO(1L)).thenReturn(bookDTO);

        mockMvc.perform(get("/books/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.isbn").value("1234567890"))
                .andExpect(jsonPath("$.title").value("Titre du book"))
                .andExpect(jsonPath("$.author").value("Auteur du book"))
                .andExpect(jsonPath("$.datePublication").value(expectedDate.toString()))
                .andExpect(jsonPath("$.synopsis").value("TestSynopsis"))
                .andExpect(jsonPath("$.type").value("Fantasy"));

        Mockito.verify(bookService).getBookByIdDTO(1L);
        log.info(objectMapper.writeValueAsString(bookDTO));
    }


    @Test
    void should_return_NotFound_and_bookDTO() throws Exception {
        Long id = 10L;

        BookDTO bookDTO = new BookDTO("1234567890", "Titre du book", "Auteur du book", LocalDate.of(2024, 04, 30),
                "TestSynopsis", "Fantasy");

        when(bookService.getBookByIdDTO(Mockito.eq(id))).thenThrow(BookNotFoundException.class);

        mockMvc.perform(get("/books/{id}", id))
                .andExpect(status().isNotFound());

        Mockito.verify(bookService).getBookByIdDTO(id);
        log.info(objectMapper.writeValueAsString(bookDTO));
    }

    // POST METHOD


    @Test
    void should_return_OK_when_given_good_body_for_post() throws Exception {
        Instant datePublication = Instant.parse("2024-04-22T11:30:03Z");
        BookDTO bookDTO = new BookDTO("1234567890", "Titre du book", "Auteur du book", LocalDate.of(2024, 04, 30), "TestSynopsis", "Fantasy");
        BookRequest bookingRequest = new BookRequest("1234567890", "Titre du book", 1L, LocalDate.of(2024, 04, 30), "TestSynopsis", "Fantasy");
        when(bookService.createBook(bookingRequest)).thenReturn(bookDTO);

        mockMvc.perform(MockMvcRequestBuilders.post("/books/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(bookingRequest)))
                .andDo(print())
                .andExpect(status().isOk());

        Mockito.verify(bookService).createBook(any(BookRequest.class));
        log.info(objectMapper.writeValueAsString(bookDTO));
    }

    @Test
    void should_return_BadRequest_when_given_BadBody_for_post() throws Exception {
        BookDTO bookDTO = new BookDTO("1234567890", "Titre du book", "Auteur du book", LocalDate.of(2024, 04, 30), "TestSynopsis", "Fantasy");
        BookRequest bookingRequest = new BookRequest("1234567890", "Titre du book", 1L, LocalDate.of(2024, 04, 30), "TestSynopsis", "Fantasy");

        when(bookService.createBook(bookingRequest)).thenReturn(bookDTO);

        mockMvc.perform(MockMvcRequestBuilders.post("/books/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(new BookRequest("", "Titre du book", 1L, LocalDate.of(2024, 04, 30), "TestSynopsis", "Fantasy"))))
                .andDo(print())
                .andExpect(status().isBadRequest());

        Mockito.verify(bookService, never()).createBook(any(BookRequest.class));
        log.info(objectMapper.writeValueAsString(bookDTO));
    }

// DELETE


    @Test
    void should_return_OK_when_delete() throws Exception {
        mockMvc.perform(delete("/books/1"))
                .andDo(print())
                .andExpect(status().isNoContent());

        Mockito.verify(bookService).deleteBook(1L);
    }

    @Test
    void should_return_NotFound_when_delete_with_BadId() throws Exception {

        doThrow(BookNotFoundException.class).when(bookService).deleteBook(2L);
        mockMvc.perform(delete("/books/2"))
                .andDo(print())
                .andExpect(status().isNotFound());

        Mockito.verify(bookService).deleteBook(2L);
    }

    // PUT


    @Test
    void should_return_OK_with_good_body_for_update() throws Exception {
        BookRequest bookRequest = new BookRequest("1234567890", "Titre du book", 1L, LocalDate.of(2024, 04, 30), "TestSynopsis", "Fantasy");

        when(bookService.updateBook(Mockito.eq(1L), any(BookRequest.class)))
                .thenReturn(bookRequest);

        mockMvc.perform(MockMvcRequestBuilders.put("/books/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(new BookRequest("1234567890", "Titre du book", 1L, LocalDate.of(2024, 04, 30), "TestSynopsis", "Fantasy"))))
                .andDo(print())
                .andExpect(status().isOk());

        Mockito.verify(bookService).updateBook(1L, bookRequest);
        log.info(objectMapper.writeValueAsString(bookRequest));

    }

    @Test
    void should_return_NotFound_when_given_Badbody_for_update() throws Exception {
        BookRequest bookRequest = new BookRequest("1234567890", "Titre du book", 1L, LocalDate.of(2024, 04, 30), "TestSynopsis", "Fantasy");
        Long bookId = 2L;

        when(bookService.updateBook(bookId, bookRequest)).thenThrow(BookNotFoundException.class);

        mockMvc.perform(MockMvcRequestBuilders.put("/books/2")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(new BookRequest("1234567890", "Titre du book", 1L, LocalDate.of(2024, 04, 30), "TestSynopsis", "Fantasy"))))
                .andDo(print())
                .andExpect(status().isNotFound());

        Mockito.verify(bookService).updateBook(2L, bookRequest);
        log.info(objectMapper.writeValueAsString(bookRequest));
    }
}