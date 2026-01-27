package com.kishore.issue_management.service;

import com.kishore.issue_management.entity.User;
import com.kishore.issue_management.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import com.kishore.issue_management.exception.UserAlreadyExistsException;
import com.kishore.issue_management.exception.UserNotFoundException;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createUser(User user) {
        Optional<User> existingUser = userRepository.findByEmail(user.getEmail());
        if (existingUser.isPresent()) {
            throw new UserAlreadyExistsException("User already exists with this email");
        }
        return userRepository.save(user);
    }

    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("User not found"));
    }
}