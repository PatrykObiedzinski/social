package com.example.social.timeline;

import com.example.social.follow.FollowDto;
import com.example.social.follow.FollowPostDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TimelineDto {

    private String ownerName;
    private String ownerSurname;
    private List<FollowDto> follows;
    private List<FollowPostDto> posts;

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
