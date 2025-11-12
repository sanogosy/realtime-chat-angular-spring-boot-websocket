package com.chatapp.chat.utils;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class CurrentUserData {

    private Integer id;
    private String firstname;
    private String lastname;
    private String email;

}
