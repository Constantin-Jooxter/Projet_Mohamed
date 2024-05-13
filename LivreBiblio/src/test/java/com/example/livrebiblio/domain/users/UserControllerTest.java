package com.example.livrebiblio.domain.users;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import java.time.LocalDate;
import java.util.Optional;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserController.class)
@ExtendWith(MockitoExtension.class)
class UserControllerTest {

    @MockBean
    private UserService userService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void should_return_user_by_id() throws Exception {
        User user = new User();
        user.setId(1L);
        user.setAdress("10 rue des Lilas");
        user.setName("John Doe");
        user.setSurname("Maverick");
        user.setBorrowing(LocalDate.of(2023, 10, 07));
        user.setRegistration(LocalDate.of(2023, 10, 07));

        UserDTO userDTO = UserMapper.convertToUserDTO(user);

        when(userService.getUserDTOByID(1L)).thenReturn(userDTO);

        mockMvc.perform(get("/users/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.address").value("10 rue des Lilas"))
                .andExpect(jsonPath("$.name").value("John Doe"))
                .andExpect(jsonPath("$.surname").value("Maverick"))
                .andExpect(jsonPath("$.registration").value("2023-10-07"))
                .andExpect(jsonPath("$.borrowing").value(LocalDate.of(2023, 10, 07)));

        verify(userService).getUserByID(1L);
    }
}