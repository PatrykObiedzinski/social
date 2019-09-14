package com.example.social.post;

import com.example.social.configuration.BaseDaoConfiguration;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static com.example.social.post.QPost.post;

@Repository
class PostDao extends BaseDaoConfiguration {

    Optional<Post> findById(long id) {
        return Optional.ofNullable(getQueryFactory()
                .selectFrom(post)
                .where(post.id.eq(id))
                .fetchOne());
    }

    @Transactional
    Post save(Post post) {
        return getEntityManager().merge(post);
    }
}
