package vn.iotstar.service;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import vn.iotstar.entity.User;
import vn.iotstar.model.LoginUserModel;
import vn.iotstar.model.RegisterUserModel;
import vn.iotstar.repository.UserRepository;

@Service
public class AuthenticationService {
    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;

    // Constructor for dependency injection
    public AuthenticationService(
            UserRepository userRepository, 
            AuthenticationManager authenticationManager, 
            PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.authenticationManager = authenticationManager;
        this.passwordEncoder = passwordEncoder;
    }

    // Method to authenticate a user
    public User authenticate(LoginUserModel input) {
        // Authenticate the user's email and password
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(input.getEmail(), input.getPassword())
        );

        // Retrieve the user from the database if authentication succeeds
        return userRepository.findByEmail(input.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    // Method to register a new user
    public User signup(RegisterUserModel input) {
        // Create a new User object
        User user = new User();
        user.setFullName(input.getFullName());
        user.setEmail(input.getEmail());
        user.setPassword(passwordEncoder.encode(input.getPassword()));

        // Save the user to the database
        return userRepository.save(user);
    }
}
