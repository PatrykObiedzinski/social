package com.example.social.wall;

import com.example.social.post.Post;
import com.example.social.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
class WallDto {

    private long id;
    private User owner;
    private List<Post> posts;

    @Override
    public String toString() {
        return "Wall{" +
                "id=" + id +
                ", owner=" + owner +
                ", posts=" + posts +
                '}';
    }
}
