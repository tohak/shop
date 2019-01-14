package com.shop.dao;

import com.shop.dao.custom.BaseRepository;
import com.shop.domain.entity.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDAO extends BaseRepository<User, Long> {
    User findByUsername(String username);
}