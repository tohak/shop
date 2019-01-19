package com.shop.domain.entity;

import com.shop.domain.entity.product.Order;
import com.shop.domain.enums.UserRole;
import com.shop.domain.enums.UserState;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/*
 * Доменая модель пользователей.
 * также связаная таблица роли для юзера(enum)
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString(callSuper = true)
//@Builder
@Entity
@Table(name = "usr_tbl")
public class User implements UserDetails {
    public static final boolean BUN_NULL = false;
    public static final int LENGTH = 100;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;

    @NotBlank(message = "Name is required")// не занесет в базу пустое поле
    @Column(name = "user_name", length = LENGTH, nullable = BUN_NULL)
    private String username;

    @NotBlank(message = "Password cannot empty")
    @Column(name = "password", length = LENGTH, nullable = BUN_NULL)
    private String password;

    @Column(name = "user_status")
    private String state;

    @NotBlank(message = "Name is required")
    @Email(message = "Email is not correct")
    @Column(name = "email", nullable = BUN_NULL, length = LENGTH)
    private String email;

    @Column(name = "user_address", length = LENGTH)
    private String address;

    @Column(name = "user_tell")
    private Long tell;

    @OneToOne
    @JoinColumn(name = "fk_order_id")
    private Order order;

    @OneToOne
    @JoinColumn(name = "fk_account_id")
    private Account account;

    @ElementCollection(targetClass = UserRole.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "user2roles_tbl",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            foreignKey = @ForeignKey(name = "users2roles_user_fk"),
            uniqueConstraints = @UniqueConstraint(columnNames = {"user_id", "roles_enum"}))
    @Enumerated(EnumType.STRING)
    @Column(name = "roles_enum", length = LENGTH)
    private Set<UserRole> roles = new HashSet<>();


    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return this.state.equals(UserState.ACTIVE.toString());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return getRoles();
    }

    public boolean isAdmin() {
        return roles.contains(UserRole.ADMIN);
    }

    public boolean isUserAut() {
        return roles.contains(UserRole.USER);
    }

    public boolean isAnonymous() {
        return roles.contains(UserRole.ANONYMOUS);
    }


}
