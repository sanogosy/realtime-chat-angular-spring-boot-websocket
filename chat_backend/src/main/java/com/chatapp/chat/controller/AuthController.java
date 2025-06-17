package com.chatapp.chat.controller;

import com.chatapp.chat.common.GenericResponse;
import com.chatapp.chat.service.AuthService;
import com.chatapp.chat.utils.AuthenticationRequest;
import com.chatapp.chat.utils.AuthenticationResponse;
import com.chatapp.chat.utils.MessageRequest;
import com.chatapp.chat.utils.RegistrationRequest;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "auth")
@RequiredArgsConstructor
@Tag(name = "Authentication")
public class AuthController {

    private final AuthService authService;

    @PostMapping(value = "/register")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<?> register(@RequestBody @Valid RegistrationRequest request) {
        authService.register(request);
        return ResponseEntity.accepted().build();
    }

    @PostMapping(value = "/login")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<AuthenticationResponse> login(
            @RequestBody @Valid AuthenticationRequest authenticationRequest
    ) {
        return ResponseEntity.ok(authService.authenticate(authenticationRequest));
    }


}
