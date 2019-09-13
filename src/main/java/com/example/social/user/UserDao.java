package com.example.social.user;

import com.example.social.configuration.BaseDaoConfiguration;
import org.springframework.stereotype.Repository;

import java.util.Optional;

import static com.example.social.user.QUser.user;

@Repository
public class UserDao extends BaseDaoConfiguration {

    public Optional<User> findUserById(long id) {
        return Optional.ofNullable(getQueryFactory()
                .selectFrom(user)
                .where(user.id.eq(id))
                .fetchOne());
    }

    void save(User user) {
        getEntityManager().merge(user);
    }
}