package com.chatapp.chat.controller;

import com.chatapp.chat.common.GenericResponse;
import com.chatapp.chat.dto.UserMessageDto;
import com.chatapp.chat.entity.Messages;
import com.chatapp.chat.entity.Users;
import com.chatapp.chat.repository.MessageRepository;
import com.chatapp.chat.repository.UserRepository;
import com.chatapp.chat.service.MessageService;
import com.chatapp.chat.utils.MessageRequest;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "message")
@AllArgsConstructor
public class MessageController {

    private final MessageService messageService;

    @PostMapping(value = "/send-message")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<GenericResponse<String>> sendMessage(@RequestBody @Valid MessageRequest messageRequest) {

        messageService.saveMessage(messageRequest);

        return ResponseEntity.ok(
                GenericResponse.<String>builder()
                .success(Boolean.TRUE)
                .message("Message envoyé !")
                .data("Message envoyé !")
                .build()
        );
    }

    @GetMapping(value = "/user-messages/{userId}")
    public ResponseEntity<GenericResponse<List<UserMessageDto>>> getUserMessage(
            @PathVariable Integer userId
    ) {
        return ResponseEntity.ok(
                GenericResponse.<List<UserMessageDto>>builder().data(messageService.getUserMessages(userId)).build()
        );
    }

}
