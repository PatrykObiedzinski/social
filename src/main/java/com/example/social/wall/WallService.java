package com.example.social.wall;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class WallService {

    private final WallDao wallDao;

    Wall getWallByOwnerId(long ownerId) {
        return wallDao.findByOwnerId(ownerId)
                .orElseThrow(() -> new WallNotFoundException(ownerId));
    }

    @Transactional
    public void addWall(Wall wall) {
        wallDao.save(wall);
    }
}
