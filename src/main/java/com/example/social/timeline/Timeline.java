package com.example.social.timeline;

import com.example.social.follow.Follow;
import com.example.social.post.Post;
import com.example.social.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "TIMELINE")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class Timeline implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "ID")
    private Long id;

    @OneToOne
    private User owner;

    @OneToMany
    private List<Follow> follows;

    @OneToMany
    @OrderBy("CREATION_TIME DESC")
    private List<Post> posts;
}
