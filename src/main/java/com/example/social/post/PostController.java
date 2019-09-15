package com.example.social.post;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/posts")
public class PostController {

    private final PostService postService;

    @PostMapping
    public void addPost(@RequestBody String content) {
        postService.addPost(content);
    }

    @PostMapping("/{authorId}")
    public void addPostByUser(@PathVariable long authorId,
                              @RequestBody String content) {
        postService.addPostByUser(authorId, content);
    }
}
