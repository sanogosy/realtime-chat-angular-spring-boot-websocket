package com.chatapp.chat.dto;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Getter
@Setter
//@Builder
@SuperBuilder
@AllArgsConstructor
public class UserMessageDto {

//    private Integer id;
    private String sendcontent;
    private Integer parentmessageId;
    private Integer messagesendId;
    private Integer receiverId;
    private Timestamp sentat;
    private String senderfirstname;
    private String senderlastname;
//    private Integer messagereceiveId;
//    private String receivecontent;
//    private Integer senderId;

}
