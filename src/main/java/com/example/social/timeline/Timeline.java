package com.example.social.timeline;

import com.example.social.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "TIMELINE")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder(toBuilder = true)
public class Timeline implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "ID")
    private Long id;

    @OneToOne
    private User owner;
}
