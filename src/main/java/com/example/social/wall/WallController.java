package com.example.social.wall;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/wall")
public class WallController {

    private final WallService wallService;
    private final WallMapper wallMapper;

    @GetMapping("/{ownerId}")
    public WallDto getWallByOwnerId(@PathVariable long ownerId) {
        Wall wall = wallService.getWallByOwnerId(ownerId);
        return wallMapper.mapToWallDto(wall);
    }
}
