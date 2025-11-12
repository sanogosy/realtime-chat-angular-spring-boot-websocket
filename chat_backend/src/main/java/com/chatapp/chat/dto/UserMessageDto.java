package com.chatapp.chat.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class UserMessageDto {

//    private Integer id;
    private String sendcontent;
    private Integer parentmessageId;
    private Integer messagesendId;
    private Integer receiverId;
//    private Integer messagereceiveId;
//    private String receivecontent;
//    private Integer senderId;

}
