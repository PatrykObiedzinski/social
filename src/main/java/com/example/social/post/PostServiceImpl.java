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
        Post post = buildPost(author, content);
        postDao.save(post);
    }

    private User addUserWithTimelineAndWall() {
        addTimeline();
        addWall();
        User author = addUser();
        userService.addUser(author);
        return author;
    }

    private void addTimeline() {
        timelineService.addTimeline(Timeline.builder().build());
    }

    private void addWall() {
        wallService.addWall(Wall.builder().build());
    }

    private User addUser() {
        User author = buildNewUser();
        userService.addUser(author);
        return author;
    }

    private User buildNewUser() {
        return User.builder()
                .name("Patryk")
                .surname("Obiedziński")
                .build();
    }

    private Post buildPost(User author, String content) {
        return Post.builder()
                .author(author)
                .content(content)
                .creationTime(timeUtil.getCurrentDate())
                .build();
    }
}
