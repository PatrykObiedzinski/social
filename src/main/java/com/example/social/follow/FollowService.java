package com.example.social.follow;

import com.example.social.user.User;
import com.example.social.user.UserDao;
import com.example.social.user.UserNotFoundException;
import com.example.social.user.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.function.Function;

@Service
@AllArgsConstructor
class FollowService {

    private final FollowDao followDao;
    private final UserDao userDao;
    private final UserService userService;

    @Transactional
    void addFollow(FollowDto followDto) {
        if (followDto.getFollowerId().equals(followDto.getFollowingId())) {
            throw new IdenticalUsersException();
        }

        User follower = getUserById(followDto, FollowDto::getFollowerId);
        User following = getUserById(followDto, FollowDto::getFollowingId);

        Follow follow = followDao.save(buildFollow(follower, following));
        updateFollowerFollows(follower, follow);
    }

    private User getUserById(FollowDto followDto, Function<FollowDto, Long> followDtoToId) {
        Long id = followDtoToId.apply(followDto);
        return userDao.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
    }

    private Follow buildFollow(User follower, User following) {
        return Follow.builder()
                .follower(follower)
                .following(following)
                .build();
    }

    private void updateFollowerFollows(User follower, Follow follow) {
        List<Follow> follows = follower.getFollows();
        follows.add(follow);

        User updatedFollower = follower.toBuilder()
                .follows(follows)
                .build();
        userService.addUser(updatedFollower);
    }
}
