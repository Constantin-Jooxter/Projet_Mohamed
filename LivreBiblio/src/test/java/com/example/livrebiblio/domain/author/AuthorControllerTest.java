package com.example.livrebiblio.domain.author;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.sql.Date;
import java.time.Instant;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AuthorController.class)
@ExtendWith(MockitoExtension.class)
public class AuthorControllerTest {
    @MockBean
    private AuthorService authorService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void should_return_OK_when_given_existing_id() throws Exception {
        // Given
        Author author = new Author();
        author.setId(1L);
        author.setBirthday(Date.from(Instant.now()));
        author.setName("John Doe");
        author.setSurname("Dirot");
        author.setOwnBooks("Clean Code");

        // Convert Author entity to AuthorDTO
        AuthorDTO authorDTO = AuthorMapper.convertToAuthorDTO(author);

        // Mocking the service method
        when(authorService.getAuthorById(1L)).thenReturn(authorDTO);

        // When/Then
        mockMvc.perform(get("/author/1"))
                .andDo(print())
                .andExpect(status().isOk());
    }



/*    @Test
    void should_return_OK_and_bookDTO() throws Exception {
        Instant datePublication = Instant.parse("2024-04-22T11:30:03Z");
        BookDTO bookDTO = new BookDTO("1234567890", "Titre du book", "Auteur du book", datePublication, "TestSynopsis");
        when(bookService.getBookById(1L)).thenReturn(bookDTO);

        mockMvc.perform(get("/books/GetById/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.isbn").value("1234567890"))
                .andExpect(jsonPath("$.titre").value("Titre du book"))
                .andExpect(jsonPath("$.auteur").value("Auteur du book"))
                .andExpect(jsonPath("$.datePublication").value(datePublication.toString()))
                .andExpect(jsonPath("$.synopsis").value("TestSynopsis"));

        Mockito.verify(bookService).getBookById(1L);
        log.info(objectMapper.writeValueAsString(bookDTO));
    }*/

}
