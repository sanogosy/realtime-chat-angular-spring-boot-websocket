package com.chatapp.chat.service;

import com.chatapp.chat.entity.Users;
import com.chatapp.chat.repository.RoleRepository;
import com.chatapp.chat.repository.UserRepository;
import com.chatapp.chat.utils.AuthenticationRequest;
import com.chatapp.chat.utils.AuthenticationResponse;
import com.chatapp.chat.utils.RegistrationRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final Jwtservice jwtService;
    private final AuthenticationManager authenticationManager;

    public void register(@Valid RegistrationRequest request) {
        var role = roleRepository.findByName("USER")
                .orElseThrow(() -> new IllegalStateException("Role USER not found"));

        Users newUser = Users.builder()
                .firstname(request.getFirstname())
                .lastname(request.getLastname())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .roles(List.of(role))
                .build();

        userRepository.save(newUser);
    }

    public AuthenticationResponse authenticate(@Valid AuthenticationRequest authenticationRequest) {
        var auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authenticationRequest.getEmail(),
                        authenticationRequest.getPassword()
                )
        );
        var claims = new HashMap<String, Object>();
        var user = (Users)auth.getPrincipal();
        var email = user.getUsername();
        claims.put("username", user.getUsername()); // get email if found
        var jwtToken = jwtService.generateToken(claims, user);

        return AuthenticationResponse.builder()
                .token(jwtToken)
                .email(email)
                .build();
    }
}
