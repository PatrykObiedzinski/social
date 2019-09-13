package com.example.social.wall;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface WallMapper {

    WallDto mapToWallDto(Wall wall);
}
