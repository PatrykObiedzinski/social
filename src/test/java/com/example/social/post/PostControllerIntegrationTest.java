package com.example.social.post;

import com.example.social.BaseIntegrationTest;
import org.junit.Test;
import org.springframework.test.web.servlet.MvcResult;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class PostControllerIntegrationTest extends BaseIntegrationTest {

    @Test
    public void should_post_a_new_message() throws Exception {
        // when
        MvcResult mvcResult = mockMvc.perform(post("/posts").content("TEXT MESSAGE"))
                .andExpect(status().is2xxSuccessful())
                .andReturn();

        // then
        assertThat(mvcResult.getResponse().getContentAsString()).isEmpty();
    }
}
