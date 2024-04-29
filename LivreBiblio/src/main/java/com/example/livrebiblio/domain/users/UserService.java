package com.example.livrebiblio.domain.users;

import lombok.AllArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;

    // GET

    public UserDTO getUserByID(Long id) throws UserNotFoundException {
        return userRepository.findById(id)
                .map(UserMapper::convertToUserDTO)
                .orElseThrow(() -> new UserNotFoundException("Users not found with ID : " + id));
    }

    // DELETE

    public void deleteUserByID(Long id) throws UserNotFoundException {
        Users UserEntity = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("Users not found with ID : " + id));
        userRepository.delete(UserEntity);
    }

    // CREATE

    public UserDTO createUsers(UserRequest userRequest) {
        Users users = createUserFromRequest(userRequest);
        Users savedUsers = userRepository.save(users);

        return UserMapper.convertToUserDTO(savedUsers);
    }

    private Users createUserFromRequest(UserRequest userRequest) {
        Users users = new Users();

        users.setName(userRequest.getName());
        users.setSurname(userRequest.getSurname());
        users.setAdress(userRequest.getAdress());
        users.setRegistration(userRequest.getRegistration());
        users.setBorrowing(userRequest.getBorrowing());

        return users;
    }


    // PUT

    public UserRequest updateUser(Long id, UserRequest userRequest) throws UserNotFoundException {
        Users users = initialiseUser(id, userRequest);

        userRepository.save(users);

        return userRequest;
    }

    private Users initialiseUser(Long id, UserRequest userRequest) throws UserNotFoundException {
        return userRepository.findById(id)
                .map(users -> {
                    users.setName(userRequest.getName());
                    users.setSurname(userRequest.getSurname());
                    users.setAdress(userRequest.getAdress());
                    users.setRegistration(userRequest.getRegistration());
                    users.setBorrowing(userRequest.getBorrowing());
                    return users;
                })
                .orElseThrow(() -> new UserNotFoundException("Users not found with ID : " + id));
    }

    // SEARCH

    public List<UserDTO> search(UserFilters userFilters) throws UserNotFoundException {
        Specification<Users> specification = buildSpecification(userFilters);
        List<Users> users = userRepository.findAll(specification);

        if (!users.isEmpty()) {
            return users.stream()
                    .map(UserMapper::convertToUserDTO)
                    .toList();
        } else {
            throw new UserNotFoundException("User not found");
        }
    }

    public Specification<Users> buildSpecification(UserFilters userFilters) {
        return UserSpecificationBuilder.builder()
                .withName(userFilters.getName())
                .withSurname(userFilters.getSurname())
                .withRegistration(userFilters.getRegistration())
                .withBorrowing(userFilters.getBorrowing())
                .build();
    }
}
