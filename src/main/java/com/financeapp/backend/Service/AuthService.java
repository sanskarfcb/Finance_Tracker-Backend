package com.financeapp.backend.Service;

import com.financeapp.backend.DTOs.LoginRequest;
import com.financeapp.backend.DTOs.SignupRequest;
import com.financeapp.backend.Model.User;
import com.financeapp.backend.Repo.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepo userRepo;
    private final BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,6}$");

    public User register(SignupRequest request) {

        String name = request.getName() != null ? request.getName().trim() : "";
        if (name.isEmpty()) {
            throw new RuntimeException("Name is required");
        }

        String email = request.getEmail() != null ? request.getEmail().trim().toLowerCase() : "";
        if (email.isEmpty() || !EMAIL_PATTERN.matcher(email).matches()) {
            throw new RuntimeException("Invalid or empty email");
        }

        String password = request.getPassword();
        if (password == null || password.length() < 5) {
            throw new RuntimeException("Password must be at least 5 characters long");
        }

        if (userRepo.findByEmail(email).isPresent()) {
            throw new RuntimeException("Email already registered");
        }

        User user = new User();
        user.setName(name);
        user.setEmail(email);
        user.setPassword(bCryptPasswordEncoder.encode(password));

        return userRepo.save(user);
    }

    public User login(LoginRequest request) {

        String email = request.getEmail() != null ? request.getEmail().trim().toLowerCase() : "";
        if (email.isEmpty() || !EMAIL_PATTERN.matcher(email).matches()) {
            throw new RuntimeException("Invalid or empty email");
        }

        String password = request.getPassword();
        if (password == null || password.length() < 5) {
            throw new RuntimeException("Password must be at least 5 characters long");
        }

        Optional<User> userOptional = userRepo.findByEmail(email);
        if (userOptional.isEmpty()) {
            throw new RuntimeException("Invalid credentials");
        }

        User user = userOptional.get();

        if (!bCryptPasswordEncoder.matches(password, user.getPassword())) {
            throw new RuntimeException("Invalid credentials");
        }

        return user;
    }
}