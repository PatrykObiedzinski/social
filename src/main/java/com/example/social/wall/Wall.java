package com.example.social.wall;

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
@Table(name = "WALL")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class Wall implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "ID")
    private Long id;

    @OneToOne
    private User owner;

    @OneToMany
    @OrderBy("CREATION_TIME DESC")
    private List<Post> posts;
}
