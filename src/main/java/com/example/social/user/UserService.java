package com.example.social.user;

import com.example.social.timeline.Timeline;
import com.example.social.timeline.TimelineService;
import com.example.social.wall.Wall;
import com.example.social.wall.WallService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static java.util.Collections.emptyList;

@Service
@AllArgsConstructor
public class UserService {

    private final UserDao userDao;
    private final TimelineService timelineService;
    private final WallService wallService;

    public User findById(Long id) {
        return userDao.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
    }

    @Transactional
    public User addUser(User user) {
        return userDao.save(user);
    }

    @Transactional
    public User addMockedUser() {
        Timeline timeline = timelineService.addTimeline(Timeline.builder().build());
        Wall wall = wallService.addWall(Wall.builder().build());
        User builtAuthor = buildNewUser(timeline, wall);

        User author = userDao.save(builtAuthor);
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
}
