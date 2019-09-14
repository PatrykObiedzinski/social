package com.example.social.post;

import com.example.social.BaseIntegrationTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

public class PostDaoIntegrationTest extends BaseIntegrationTest {

    private static final long MOCKED_POST_ID = 1L;

    @Autowired
    private PostDao postDao;

    @Test
    public void should_return_a_post() {
        // given
        Post post = mockPost();

        // when
        Optional<Post> postFromDatabase = postDao.findById(MOCKED_POST_ID);

        // then
        assertThat(postFromDatabase).hasValue(post);
    }

    @Test
    @Transactional
    public void should_add_a_post() {
        // given
        Post post = mockPost();

        // when
        postDao.save(post);

        // then
        assertThat(postDao.findById(MOCKED_POST_ID)).isNotNull();
    }

    private Post mockPost() {
        return Post.builder()
                .id(MOCKED_POST_ID)
                .build();
    }
}
