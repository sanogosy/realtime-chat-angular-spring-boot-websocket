package com.chatapp.chat.service;

import com.chatapp.chat.dto.UserMessageDto;
import com.chatapp.chat.entity.Messages;
import com.chatapp.chat.entity.Users;
import com.chatapp.chat.repository.MessageRepository;
import com.chatapp.chat.repository.UserRepository;
import com.chatapp.chat.utils.MessageRequest;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class MessageService {

    private static final Logger logger = LoggerFactory.getLogger(MessageService.class);
    private final MessageRepository messageRepository;
    private final UserRepository userRepository;

    public String saveMessage(MessageRequest messageRequest) {

        //get sender infos
        Users userSender = userRepository.findById(messageRequest.getSenderId()).orElseThrow(
                () -> new UsernameNotFoundException("Sender User not found")
        );
        //get receiver infos
        Users userReceiver = userRepository.findById(messageRequest.getReceiverId()).orElseThrow(
                () -> new UsernameNotFoundException("Receiver User not found")
        );
        //check if the message file exists
//        Optional<Messages> msg = messageRepository.findBySenderAndReceiver(userSender.getId(), userReceiver.getId());

        LocalDateTime localDateTime = LocalDateTime.now();

        //store message
        Messages message = Messages.builder()
                .sender(userSender)
                .receiver(userReceiver)
                .content(messageRequest.getContent())
                .lastModifiedDate(localDateTime)
                .createdBy(userSender.getLastname() + " " + userSender.getFirstname())
                .createdDate(localDateTime)
                .build();

        messageRepository.save(message);
        return "Success";
    }

    public List<UserMessageDto> getUserMessages(Integer user_id) {
        return messageRepository.findAllByOwner(user_id);
    }

}
