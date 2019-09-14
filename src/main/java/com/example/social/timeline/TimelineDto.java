package com.example.social.timeline;

import com.example.social.follow.FollowDto;
import com.example.social.post.PostDto;
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

    private String ownerName;
    private String ownerSurname;
    private List<FollowDto> follows;
    private List<PostDto> posts;

    @Override
    public String toString() {
        return "TimelineDto{" +
                "ownerName='" + ownerName + '\'' +
                ", ownerSurname='" + ownerSurname + '\'' +
                ", follows=" + follows +
                ", posts=" + posts +
                '}';
    }
}
