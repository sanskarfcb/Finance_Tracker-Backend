package com.financeapp.backend.Controller;

import com.financeapp.backend.DTOs.AuthResponse;
import com.financeapp.backend.DTOs.LoginRequest;
import com.financeapp.backend.DTOs.SignupRequest;
import com.financeapp.backend.Model.User;
import com.financeapp.backend.Security.jwtTokenUtil;
import com.financeapp.backend.Service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;
    private final jwtTokenUtil jwtTokenUtil;

    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody SignupRequest request){
        User user = authService.register(request);
        return ResponseEntity.ok(user);
    }
    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request){
        User user = authService.login(request);
        String token = jwtTokenUtil.generateToken(user.getEmail());
        return ResponseEntity.ok(new AuthResponse(user.getEmail(), token));
    }

}
