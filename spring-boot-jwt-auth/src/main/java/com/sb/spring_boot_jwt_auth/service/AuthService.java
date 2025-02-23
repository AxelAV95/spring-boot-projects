package com.sb.spring_boot_jwt_auth.service;


import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.sb.spring_boot_jwt_auth.dto.AuthResponse;
import com.sb.spring_boot_jwt_auth.dto.LoginRequest;
import com.sb.spring_boot_jwt_auth.entity.AuditLog;
import com.sb.spring_boot_jwt_auth.entity.User;
import com.sb.spring_boot_jwt_auth.repository.AuditLogRepository;
import com.sb.spring_boot_jwt_auth.repository.UserRepository;
import com.sb.spring_boot_jwt_auth.security.JwtUtil;

@Service
public class AuthService {
    
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final AuditLogRepository auditLogRepository;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;

    public AuthService(AuthenticationManager authenticationManager,
                      UserRepository userRepository,
                      AuditLogRepository auditLogRepository,
                      JwtUtil jwtUtil,
                      PasswordEncoder passwordEncoder) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.auditLogRepository = auditLogRepository;
        this.jwtUtil = jwtUtil;
        this.passwordEncoder = passwordEncoder;
    }

    public AuthResponse login(LoginRequest loginRequest, HttpServletRequest request) {
        Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                loginRequest.getUsername(),
                loginRequest.getPassword()
            )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String token = jwtUtil.generateToken(userDetails);

        // Save login audit
        User user = userRepository.findByUsername(userDetails.getUsername()).orElseThrow();
        AuditLog auditLog = new AuditLog();
        auditLog.setUser(user);
        auditLog.setAction("LOGIN");
        auditLog.setIpAddress(request.getRemoteAddr());
        auditLogRepository.save(auditLog);

        AuthResponse response = new AuthResponse();
        response.setToken(token);
        response.setUsername(userDetails.getUsername());
        response.setExpiresIn(jwtUtil.getExpirationTime());
        return response;
    }

    public void logout(HttpServletRequest request) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            User user = userRepository.findByUsername(auth.getName()).orElseThrow();
            AuditLog auditLog = new AuditLog();
            auditLog.setUser(user);
            auditLog.setAction("LOGOUT");
            auditLog.setIpAddress(request.getRemoteAddr());
            auditLogRepository.save(auditLog);
        }
        SecurityContextHolder.clearContext();
    }

    public User register(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }
}