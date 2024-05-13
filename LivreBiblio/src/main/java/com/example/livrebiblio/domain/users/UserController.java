package com.example.livrebiblio.domain.users;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController("/users")
public class UserController {

    private final UserService userService;

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable Long id) throws UserNotFoundException {
        return ResponseEntity.ok().body(userService.getUserDTOByID(id));
    }


    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deleteUserById(@PathVariable Long id) throws UserNotFoundException {
        userService.deleteUserByID(id);
    }

    @PostMapping("/")
    public ResponseEntity<UserDTO> createUser(@RequestBody @Valid UserRequest userRequest) throws UserNotFoundException {
        return ResponseEntity.ok().body(userService.createUsers(userRequest));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserRequest> putUserById(@PathVariable Long id, @RequestBody @Valid UserRequest userRequest) throws UserNotFoundException {
        return ResponseEntity.ok().body(userService.updateUser(id, userRequest));
    }

    @GetMapping("/search")
    public ResponseEntity<List<UserDTO>> searchUser(@ParameterObject UserFilters userFilters) throws UserNotFoundException {
        List<UserDTO> users = userService.search(userFilters);
        return ResponseEntity.ok().body(users);
    }
}
