package com.chatapp.chat.controller;

import com.chatapp.chat.common.GenericResponse;
import com.chatapp.chat.utils.MessageRequest;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/message")
@AllArgsConstructor
public class MessageController {

    @PostMapping(value = "/send-message")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<GenericResponse<String>> sendMessage(@RequestBody @Valid MessageRequest messageRequest) {
        return ResponseEntity.ok(GenericResponse.<String>builder()
                .success(Boolean.TRUE)
                .message("Message envoyé !")
                .data("Message envoyé !")
                .build());
    }

}
