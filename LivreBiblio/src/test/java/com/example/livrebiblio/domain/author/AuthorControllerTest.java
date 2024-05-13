package com.example.livrebiblio.domain.author;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;

import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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
        author.setBirthday(LocalDate.of(2024, 04, 30));
        author.setName("John Doe");
        author.setSurname("Dirot");

        // Convert Author entity to AuthorDTO
        AuthorDTO authorDTO = AuthorMapper.convertToAuthorDTO(author);

        // Mocking the service method
        when(authorService.getAuthorById(1L)).thenReturn(authorDTO);

        // When/Then
        mockMvc.perform(get("/author/1"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void should_return_KO_when_given_non_existing_id() throws Exception {
        Author author = new Author();
        author.setId(99L);
        author.setBirthday(LocalDate.of(2024, 04, 30));
        author.setName("John Doe");
        author.setSurname("Dirot");

        when(authorService.getAuthorById(99L)).thenThrow(new AuthorNotFoundException("Author not found"));

        // When/Then
        mockMvc.perform(get("/author/99"))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    void should_delete_when_given_existing_id() throws Exception {
        // ARRANGE
        Long deleteId = 1L;

        // ACT
        mockMvc.perform(delete("/author/" + deleteId))
                .andDo(print())
                .andExpect(status().isNoContent());

        // ASSERT
        verify(authorService).deleteAuthorById(1L);
    }

    @Test
    void should_return_NotFound_when_given_non_existing_id() throws Exception {
        // ARRANGE
        Long deleteId = 99L;

        // ACT
        doThrow(AuthorNotFoundException.class).when(authorService).deleteAuthorById(deleteId);
        mockMvc.perform(delete("/author/" + deleteId))
                .andDo(print())
                .andExpect(status().isNotFound());

        // ASSERT
        verify(authorService).deleteAuthorById(99L);
    }


    @Test
    void should_create_author_when_given_good_request() throws Exception {
        Author author = new Author();
        author.setBirthday(LocalDate.of(2024, 04, 30));
        author.setName("John Doe");
        author.setSurname("Dirot");
        AuthorDTO authorDTO = AuthorMapper.convertToAuthorDTO(author);

        AuthorRequest authorRequest = new AuthorRequest();
        authorRequest.setName("John Doe");
        authorRequest.setBirthday(LocalDate.of(2024, 04, 30));

        when(authorService.createAuthor(authorRequest)).thenReturn(authorDTO);

        mockMvc.perform(post("/author/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(authorRequest)))
                .andDo(print())
                .andExpect(status().isCreated());

        verify(authorService).createAuthor(authorRequest);
    }

    @Test
    void should_return_BadRequest_when_given_bad_request() throws Exception {
        Author author = new Author();
        author.setBirthday(LocalDate.of(2024, 04, 30));
        author.setName("John Doe");
        author.setSurname("");
        AuthorDTO authorDTO = AuthorMapper.convertToAuthorDTO(author);

        AuthorRequest authorRequest = new AuthorRequest();
        authorRequest.setName("jkihijk");
        authorRequest.setSurname("Lala");
        authorRequest.setBirthday(LocalDate.of(2024, 04, 30));

        when(authorService.createAuthor(authorRequest)).thenThrow(new AuthorBadRequestException("Failed to create author"));

        mockMvc.perform(post("/author/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(authorRequest)))
                .andDo(print())
                .andExpect(status().isBadRequest());

        verify(authorService).createAuthor(authorRequest);
    }

}
