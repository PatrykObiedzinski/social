package com.example.social.wall;

import com.example.social.post.PostDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
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
