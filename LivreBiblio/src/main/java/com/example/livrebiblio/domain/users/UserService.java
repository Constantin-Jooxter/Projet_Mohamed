package com.example.livrebiblio.domain.users;

import lombok.AllArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;

    // GET


    public UserDTO getUserDTOByID(Long id) throws UserNotFoundException {
        return getUserByID(id)
                .map(UserMapper::convertToUserDTO)
                .orElseThrow(() -> new UserNotFoundException("Users not found with ID : " + id));
    }

    public Optional<User> getUserByID(Long id) throws UserNotFoundException {
        return userRepository.findById(id);
    }
    // DELETE

    public void deleteUserByID(Long id) throws UserNotFoundException {
        User UserEntity = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("Users not found with ID : " + id));
        userRepository.delete(UserEntity);
    }

    // CREATE

    public UserDTO createUsers(UserRequest userRequest) {
        User users = createUserFromRequest(userRequest);
        User savedUsers = userRepository.save(users);

        return UserMapper.convertToUserDTO(savedUsers);
    }

    private User createUserFromRequest(UserRequest userRequest) {
        User users = new User();

        users.setName(userRequest.getName());
        users.setSurname(userRequest.getSurname());
        users.setAdress(userRequest.getAdress());
        users.setRegistration(userRequest.getRegistration());
        users.setBorrowing(userRequest.getBorrowing());

        return users;
    }


    // PUT

    public UserRequest updateUser(Long id, UserRequest userRequest) throws UserNotFoundException {
        User users = initialiseUser(id, userRequest);

        userRepository.save(users);

        return userRequest;
    }

    private User initialiseUser(Long id, UserRequest userRequest) throws UserNotFoundException {
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
        Specification<User> specification = buildSpecification(userFilters);
        List<User> users = userRepository.findAll(specification);

        if (!users.isEmpty()) {
            return users.stream()
                    .map(UserMapper::convertToUserDTO)
                    .toList();
        } else {
            throw new UserNotFoundException("User not found");
        }
    }

    public Specification<User> buildSpecification(UserFilters userFilters) {
        return UserSpecificationBuilder.builder()
                .withName(userFilters.getName())
                .withSurname(userFilters.getSurname())
                .withRegistration(userFilters.getRegistration())
                .withBorrowing(userFilters.getBorrowing())
                .build();
    }
}
