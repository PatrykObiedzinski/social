package com.example.social.post;

import com.example.social.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "POST")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class Post implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "CONTENT", length = 140)
    private String content;

    @OneToOne
    private User author;

    @Column(name = "CREATION_TIME")
    private LocalDateTime creationTime;
}
