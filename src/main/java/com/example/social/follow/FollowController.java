package com.example.social.follow;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/follows")
public class FollowController {

    private final FollowService followService;

    @PostMapping
    public void addFollow(@RequestBody FollowDto followDto) {
        followService.addFollow(followDto);
    }
}
