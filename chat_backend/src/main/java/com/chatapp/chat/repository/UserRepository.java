package com.chatapp.chat.repository;

import com.chatapp.chat.dto.UserDto;
import com.chatapp.chat.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<Users, Integer> {

    Optional<Users> findByEmail(String email);

    @Query(
            value = "SELECT id, firstname, lastname, email FROM users",
            nativeQuery = true
    )
    Optional<List<UserDto>> findAllUser();

}
