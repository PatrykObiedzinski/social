package com.example.social.user;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class UserService {

    private final UserDao userDao;

    @Transactional
    public void addUser(User user) {
        userDao.save(user);
    }
}