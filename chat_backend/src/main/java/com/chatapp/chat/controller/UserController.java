package com.chatapp.chat.controller;

import com.chatapp.chat.service.UserService;
import com.chatapp.chat.utils.CurrentUserData;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "user")
@RequiredArgsConstructor
@Tag(name = "Users")
public class UserController {

    private final UserService userService;

    @GetMapping(value = "/getCurrentUserData")
    public ResponseEntity<CurrentUserData> getCurrentUserData(@RequestParam String email) {
        return ResponseEntity.ok(userService.getCurrentUserDataByEmail(email));
    }

}
