package com.example.social.user;

import com.example.social.follow.Follow;
import com.example.social.post.Post;
import com.example.social.timeline.Timeline;
import com.example.social.wall.Wall;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "USER")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class User implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "ID")
    private Long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "SURNAME")
    private String surname;

    @OneToMany
    private List<Follow> follows;

    @OneToMany
    @OrderBy("CREATION_TIME DESC")
    private List<Post> posts;

    @OneToOne
    private Timeline timeline;

    @OneToOne
    private Wall wall;

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", follows=" + follows +
                '}';
    }
}
