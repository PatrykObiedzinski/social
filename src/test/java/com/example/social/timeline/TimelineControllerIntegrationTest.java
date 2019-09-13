package com.example.social.timeline;

import com.example.social.BaseIntegrationTest;
import org.junit.Test;
import org.springframework.test.web.servlet.MvcResult;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class TimelineControllerIntegrationTest extends BaseIntegrationTest {

    private static final long MOCKED_OWNER_ID = 415231L;

    @Test
    public void should_throw_an_exception_when_no_timeline_exists() throws Exception {
        // when
        MvcResult mvcResult = mockMvc.perform(get("/timeline/{ownerId}", MOCKED_OWNER_ID))
                .andExpect(status().is4xxClientError())
                .andReturn();

        // then
        assertThat(mvcResult.getResponse().getContentAsString()).isEmpty();
    }
}
