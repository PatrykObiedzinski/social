package com.example.social.post;

import com.example.social.timeline.Timeline;
import com.example.social.timeline.TimelineService;
import com.example.social.user.User;
import com.example.social.user.UserService;
import com.example.social.utils.TimeUtil;
import com.example.social.wall.Wall;
import com.example.social.wall.WallService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static java.util.Collections.emptyList;

@Service
@AllArgsConstructor
class PostServiceImpl implements PostService {

    private final PostDao postDao;
    private final UserService userService;
    private final TimelineService timelineService;
    private final WallService wallService;
    private final TimeUtil timeUtil;

    @Transactional
    public void addPost(String content) {
        User author = addUserWithTimelineAndWall();
        Post builtPost = buildPost(author, content);
        Post post = postDao.save(builtPost);
        updateAuthorPosts(author, post);
    }

    private User addUserWithTimelineAndWall() {
        Timeline timeline = timelineService.addTimeline(Timeline.builder().build());
        Wall wall = wallService.addWall(Wall.builder().build());
        User builtAuthor = buildNewUser(timeline, wall);

        User author = userService.addUser(builtAuthor);
        addOwnerToTimeline(author, timeline);
        addOwnerToWall(author, wall);
        return author;
    }

    private User buildNewUser(Timeline timeline, Wall wall) {
        return User.builder()
                .name("Patryk")
                .surname("Obiedziński")
                .follows(emptyList())
                .posts(emptyList())
                .timeline(timeline)
                .wall(wall)
                .build();
    }

    private void addOwnerToTimeline(User owner, Timeline timeline) {
        Timeline updatedTimeline = timeline.toBuilder()
                .owner(owner)
                .build();
        timelineService.addTimeline(updatedTimeline);
    }

    private void addOwnerToWall(User owner, Wall wall) {
        Wall updatedWall = wall.toBuilder()
                .owner(owner)
                .build();
        wallService.addWall(updatedWall);
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
