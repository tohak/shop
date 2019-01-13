package com.shop.domain.enums;

import org.springframework.security.core.GrantedAuthority;

/*
* Роли юзера
*/

public enum  UserRole implements GrantedAuthority {
    USER, ADMIN, ANONYMOUS, MANAGER;

    @Override
    public String getAuthority() {
        return name();
    }
}