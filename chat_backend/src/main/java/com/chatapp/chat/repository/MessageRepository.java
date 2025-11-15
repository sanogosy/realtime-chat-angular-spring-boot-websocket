package com.chatapp.chat.repository;

import com.chatapp.chat.dto.UserMessageDto;
import com.chatapp.chat.entity.Messages;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MessageRepository extends JpaRepository<Messages, Integer> {

    @Query(
            value = "SELECT COALESCE(ms.content, mr.content) AS sendcontent, " +
                    "ms.parentmessage_id AS parentmessageId, " +
                    "COALESCE(ms.id, mr.sender_id) AS messagesendId, " +
                    "COALESCE(ms.receiver_id, mr.receiver_id) AS receiverId, " +
                    "COALESCE(ms.created_date, mr.created_date) AS sentat, " +
                    "u.firstname AS senderfirstname, " +
                    "u.lastname AS senderlastname " +
                    "FROM users u " +
                    "LEFT JOIN messages ms " +
                    "ON u.id = ms.sender_id " +
                    "LEFT JOIN messages mr " +
                    "ON u.id = mr.receiver_id " +
                    "WHERE u.id = ?",
            nativeQuery = true
    )
    List<UserMessageDto> findAllByOwner(Integer userId);

//    Optional<Messages> findBySenderAndReceiver(Integer senderId, Integer receiverId);
//    List<Messages> findAllBySenderAndReceiver(Integer senderId, Integer receiverId);
    List<Messages> findAllMessagesBySenderIdAndReceiverId(Integer senderId, Integer receiverId);
}
