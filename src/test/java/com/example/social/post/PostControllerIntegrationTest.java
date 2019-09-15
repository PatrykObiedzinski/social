package com.example.social.post;

import com.example.social.BaseIntegrationTest;
import org.junit.Test;
import org.springframework.test.web.servlet.MvcResult;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class PostControllerIntegrationTest extends BaseIntegrationTest {

    private static final long MOCKED_AUTHOR_ID = 1L;
    private static final String MOCKED_CONTENT = "TEXT MESSAGE";

    @Test
    public void should_post_a_new_message() throws Exception {
        // when
        MvcResult mvcResult = mockMvc.perform(post("/posts").content(MOCKED_CONTENT))
                .andExpect(status().is2xxSuccessful())
                .andReturn();

        // then
        assertThat(mvcResult.getResponse().getContentAsString()).isEmpty();
    }

    @Test
    public void should_throw_an_exception_when_no_user_found() throws Exception {
        // when
        MvcResult mvcResult = mockMvc.perform(post("/posts/{authorId}", MOCKED_AUTHOR_ID).content("TEXT MESSAGE"))
                .andExpect(status().is4xxClientError())
                .andReturn();

        // then
        assertThat(mvcResult.getResponse().getContentAsString()).isEmpty();
    }
}
