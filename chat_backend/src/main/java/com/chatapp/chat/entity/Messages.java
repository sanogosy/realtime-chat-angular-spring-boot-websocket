package com.chatapp.chat.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.w3c.dom.Text;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "messages")
public class Messages {

    @Id
    @GeneratedValue
    private Integer id;

    @NotNull
    @Column(columnDefinition = "TEXT")
    private String content;

    @OneToMany(mappedBy = "parentmessage", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Messages> childmessages;

    @NotNull
    @Column(columnDefinition = "boolean default false")
    private boolean read;

    @NotNull
    @Column(columnDefinition = "boolean default false")
    private boolean deleted;

    @ManyToOne
    @JoinColumn(name = "parentmessage_id")
    private Messages parentmessage;

    @ManyToOne
    @JoinColumn(name = "sender_id")
    private Users sender;

    @ManyToOne
    @JoinColumn(name = "receiver_id")
    private Users receiver;

}
