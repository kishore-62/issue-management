
package com.kishore.issue_management.service;
import com.kishore.issue_management.security.JwtUtil;
import com.kishore.issue_management.entity.User;
import com.kishore.issue_management.repository.UserRepository;
import org.springframework.stereotype.Service;
import com.kishore.issue_management.entity.enums.Role;
import java.util.Optional;
import com.kishore.issue_management.exception.UserAlreadyExistsException;
import com.kishore.issue_management.exception.UserNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;



@Service
public class UserService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;


    public UserService(UserRepository userRepository,PasswordEncoder passwordEncoder,JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.passwordEncoder=passwordEncoder;
        this.jwtUtil=jwtUtil;
    }
    public void register(String name, String email, String password, Role role) {

        if (userRepository.findByEmail(email).isPresent()) {
            throw new RuntimeException("Email already exists");
        }

        User user = new User();
        user.setName(name);
        user.setEmail(email);
        user.setPassword(passwordEncoder.encode(password));

        if (role == null) {
            user.setRole(Role.USER);
        } else {
            user.setRole(role);
        }

        userRepository.save(user);
    }
    public String login(String email, String rawPassword) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Invalid credentials"));

        if (!passwordEncoder.matches(rawPassword, user.getPassword())) {
            throw new RuntimeException("Invalid credentials");
        }

        return jwtUtil.generateToken(user.getEmail(), user.getRole().name());
    }

    public User createUser(User user) {


        Optional<User> existingUser = userRepository.findByEmail(user.getEmail());
        if (existingUser.isPresent()) {
            throw new UserAlreadyExistsException("User already exists with this email");
        }
        if(user.getPassword()==null){
            throw new
                    IllegalArgumentException("password is null  before encoding");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("User not found"));

    }


}