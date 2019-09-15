package com.example.social.post;

import com.example.social.user.User;
import com.example.social.user.UserService;
import com.example.social.utils.TimeUtil;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
class PostServiceImpl implements PostService {

    private final PostDao postDao;
    private final UserService userService;
    private final TimeUtil timeUtil;

    @Transactional
    public void addPost(String content) {
        User author = userService.addMockedUser();
        Post builtPost = buildPost(author, content);
        Post post = postDao.save(builtPost);
        updateAuthorPosts(author, post);
    }

    @Transactional
    public void addPostByConcreteUser(long authorId, String content) {
        User author = userService.findById(authorId);
        Post builtPost = buildPost(author, content);
        Post post = postDao.save(builtPost);
        updateAuthorPosts(author, post);
    }

    private Post buildPost(User author, String content) {
        return Post.builder()
                .author(author)
                .content(content)
                .creationTime(timeUtil.getCurrentDate())
                .build();
    }

    private void updateAuthorPosts(User author, Post post) {
        List<Post> currentPosts = author.getPosts();
        currentPosts.add(post);

        author.toBuilder()
                .posts(currentPosts)
                .build();
    }
}
