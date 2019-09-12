package com.example.social.follow;

import com.example.social.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "FOLLOW")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class Follow implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "ID")
    private Long id;

    @OneToOne
    private User follower;

    @OneToOne
    private User following;
}
