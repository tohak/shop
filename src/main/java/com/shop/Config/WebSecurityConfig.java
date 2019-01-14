package com.shop.Config;

import com.shop.service.UserService;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

/*
 * Конфигурация доступа к приложению
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true) // включить глобальный поиск прав доступа по проєкту
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    public WebSecurityConfig(UserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    //куда какой доступ по страницам
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()   // без авторизации нечего нельзя делать
                .antMatchers("/", "/h2-console/**", "/registration", "/static/**", "/img/**").permitAll() // перейти на эту страницу может только пользователи групп(все)
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .permitAll()
                .and()
                .csrf().ignoringAntMatchers("/h2-console/**")   // добавить в защите игнор консоле
                .and()
                .rememberMe()
                .and()
                .logout()
                .permitAll()
                .and()
                .headers().frameOptions().sameOrigin();// разрешить использовать frame с одинаковыми url
    }

    /**
     * Адаптация модели безопасности Spring к источнику хранение пользователей;
     * detailsService - какой сервис использовать для загрузки пользователя по логину;
     * passwordEncoder - способ шифрование пароля;
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService)
                .passwordEncoder(passwordEncoder);
    }

}