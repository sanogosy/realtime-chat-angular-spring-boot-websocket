package com.chatapp.chat.service;

import com.chatapp.chat.dto.UserDto;
import com.chatapp.chat.entity.Users;
import com.chatapp.chat.repository.UserRepository;
import com.chatapp.chat.utils.CurrentUserData;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public CurrentUserData getCurrentUserDataByEmail(String email) {
        Users user = userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("User not found exception"));

        return CurrentUserData.builder()
                .id(user.getId())
                .firstname(user.getFirstname())
                .lastname(user.getLastname())
                .email(user.getEmail())
                .build();
    }

    public List<UserDto> getAllUsers() {
        return userRepository.findAllUser().orElse(Collections.emptyList());
    }

}
