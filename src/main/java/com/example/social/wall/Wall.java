package com.example.social.wall;

import com.example.social.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "WALL")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder(toBuilder = true)
public class Wall implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @OneToOne
    private User owner;
}
