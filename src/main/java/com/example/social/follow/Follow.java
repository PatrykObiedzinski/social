package com.example.social.follow;

import com.example.social.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
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
    @NotNull
    private User follower;

    @OneToOne
    @NotNull
    private User following;
}
