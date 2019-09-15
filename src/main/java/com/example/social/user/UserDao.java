package com.example.social.user;

import com.example.social.configuration.BaseDaoConfiguration;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static com.example.social.user.QUser.user;

@Repository
public class UserDao extends BaseDaoConfiguration {

    @Transactional
    public User save(User user) {
        return getEntityManager().merge(user);
    }

    public Optional<User> findById(long id) {
        return Optional.ofNullable(getQueryFactory()
                .selectFrom(user)
                .where(user.id.eq(id))
                .fetchOne());
    }
}
