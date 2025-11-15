package com.chatapp.chat.utils;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class MessageRequest {

    @NotNull
    private Integer senderId;
    @NotNull
    private Integer receiverId;
    @NotEmpty
    private String content;

}
