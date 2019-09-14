package com.example.social.follow;

import com.example.social.user.User;
import com.example.social.user.UserDao;
import com.example.social.user.UserNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.function.Function;

@Service
@AllArgsConstructor
class FollowService {

    private final FollowDao followDao;
    private final UserDao userDao;

    @Transactional
    void addFollow(FollowDto followDto) {
        User follower = getUserById(followDto, FollowDto::getFollowerId);
        User following = getUserById(followDto, FollowDto::getFollowingId);

        followDao.save(buildFollow(follower, following));
    }

    private User getUserById(FollowDto followDto, Function<FollowDto, Long> followDtoToId) {
        Long id = followDtoToId.apply(followDto);
        return userDao.findUserById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
    }

    private Follow buildFollow(User follower, User following) {
        return Follow.builder()
                .follower(follower)
                .following(following)
                .build();
    }
}
