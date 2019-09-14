package com.example.social.wall;

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
class WallDto {

    private String ownerName;
    private String ownerSurname;
    private List<PostDto> posts;

    @Override
    public String toString() {
        return "WallDto{" +
                "ownerName='" + ownerName + '\'' +
                ", ownerSurname='" + ownerSurname + '\'' +
                ", posts=" + posts +
                '}';
    }
}
