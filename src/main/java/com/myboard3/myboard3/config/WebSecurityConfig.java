package com.myboard3.myboard3.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    @Autowired
    private DataSource dataSource;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((requests) -> requests
                        .antMatchers("/", "/account/register").permitAll()
                        .anyRequest().authenticated()
                )
                .formLogin((form) -> form
                        .loginPage("/account/login")
                        .permitAll()
                )
                .logout((logout) -> logout.permitAll());
        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails user =
                User.withDefaultPasswordEncoder()
                        .username("test01")
                        .password("1234")
                        .roles("ROLE_USER")
                        .build();

        return new InMemoryUserDetailsManager(user);
    }

//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http
//                .authorizeRequests()
//                    .antMatchers("/", "/account/register").permitAll()
//                    .anyRequest().authenticated()
//                    .and()
//                .formLogin()
//                    .loginPage("/account/login")
//                    .permitAll()
//                    .and()
//                .logout()
//                    .permitAll();
//    }
//
//    @Autowired
//    public void configureGlobal(AuthenticationManagerBuilder auth)
//            throws Exception {
//        auth.jdbcAuthentication()
//                .dataSource(dataSource)
//                .passwordEncoder(passwordEncoder())
//                .usersByUsernameQuery("select id, username, password "
//                        + "from user "
//                        + "where username = ? ")
//                .authoritiesByUsernameQuery("select u.id, u.username, r.name "
//                        + "from user_role ur inner join user u on ur.user_id = u.id "
//                        + "inner join role r on ur.role_id = r.id "
//                        + "where u.username = ? ");
//    }
//
//    @Bean
//    public PasswordEncoder passwordEncoder() { // 스프링에서 제공해주는 인코더
//        return new BCryptPasswordEncoder();
//    }
}
