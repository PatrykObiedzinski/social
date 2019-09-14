package com.example.social.follow;

import com.example.social.BaseIntegrationTest;
import org.junit.Test;
import org.springframework.test.web.servlet.MvcResult;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class FollowControllerIntegrationTest extends BaseIntegrationTest {

    private static final long NONEXISTENT_FOLLOWER_ID = 21251L;
    private static final long NONEXISTENT_FOLLOWING_ID = 112315L;

    @Test
    public void should_throw_an_exception_when_no_users_found() throws Exception {
        // when
        MvcResult mvcResult = mockMvc.perform(post("/follows", mockFollowDto()))
                .andExpect(status().is4xxClientError())
                .andReturn();

        // then
        assertThat(mvcResult.getResponse().getContentAsString()).isEmpty();
    }

    private FollowDto mockFollowDto() {
        return FollowDto.builder()
                .followerId(NONEXISTENT_FOLLOWER_ID)
                .followingId(NONEXISTENT_FOLLOWING_ID)
                .build();
    }
}
