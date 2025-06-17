package com.chatapp.chat.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "roles")
public class Roles {

    @Id
    @GeneratedValue
    private Integer id;
    @Column(unique = true)
    private String name;

    @ManyToMany(mappedBy = "roles")
    @JsonIgnore
    private List<Users> users;

}
