package com.example.social.follow;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class FollowDto {

    private Long followerId;
    private Long followingId;

    @Override
    public String toString() {
        return "FollowDto{" +
                "followerId=" + followerId +
                ", followingId=" + followingId +
                '}';
    }
}
