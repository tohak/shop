package com.shop.dao;

import com.shop.dao.castom.BaseRepository;
import com.shop.domain.entety.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDAO extends BaseRepository<User, Long> {
    User findByUsername(String username);
}