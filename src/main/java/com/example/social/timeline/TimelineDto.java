package com.example.social.timeline;

import com.example.social.follow.Follow;
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
public class TimelineDto {

    private Long id;
    private User owner;
    private List<Follow> follows;
    private List<Post> posts;

    @Override
    public String toString() {
        return "TimelineDto{" +
                "id=" + id +
                ", owner=" + owner +
                ", follows=" + follows +
                ", posts=" + posts +
                '}';
    }
}
