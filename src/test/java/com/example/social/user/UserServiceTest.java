package com.example.social.user;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

    @Mock
    private UserDao userDao;

    @Captor
    private ArgumentCaptor<User> saveArgumentCaptor;

    @InjectMocks
    private UserService userService;

    @Test
    public void should_add_user() {
        // given
        User user = mockUser();

        // when
        userService.addUser(user);

        // then
        verify(userDao).save(saveArgumentCaptor.capture());
        assertThat(saveArgumentCaptor.getValue()).isEqualToComparingFieldByField(mockUser());
    }

    private User mockUser() {
        return User.builder()
                .id(1L)
                .build();
    }
}
