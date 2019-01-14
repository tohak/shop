package com.shop.service;

import com.shop.dao.UserDAO;
import com.shop.domain.entity.User;
import com.shop.domain.enums.UserRole;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class UserService implements UserDetailsService {
    private final UserDAO userDAO;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserDAO userDAO, PasswordEncoder passwordEncoder) {
        this.userDAO = userDAO;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        User user = userDAO.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return user;
    }

    public boolean addUser(User user) {
        User userFromDb = userDAO.findByUsername(user.getUsername());
        // если в базе уже есть такое имя или введенное поле имени пусто то возращаем фолсе
        if (userFromDb != null || user.getUsername().isEmpty()) {
            return false;
        }
        // если нет сохраняем в базу
        user.setRoles(Collections.singleton(UserRole.ANONYMOUS));
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userDAO.save(user);
        return true;
    }
}
