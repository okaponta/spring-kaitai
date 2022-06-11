package com.example.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.sql.DataSource;

@EnableWebSecurity
@Configuration
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return web -> web.ignoring()
                .antMatchers("/webjars/**")
                .antMatchers("/css/**")
                .antMatchers("/js/**")
                .antMatchers("/h2-console/**");
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.formLogin(login -> login
                .loginProcessingUrl("/login") // ログイン処理のパス
                .loginPage("/login") //ログインページ
                .failureUrl("/login?error") //失敗時
                .usernameParameter("userId")
                .passwordParameter("password")
                .defaultSuccessUrl("/user/list", true)
        ).logout(logout -> logout
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login?logout")
        ).authorizeHttpRequests(authz -> authz
                .mvcMatchers("/login").permitAll() // 直リンクOK
                .mvcMatchers("/user/signup").permitAll() // 直リンクOK
                .mvcMatchers("/user/signup/rest").permitAll() // 直リンクOK
                .mvcMatchers("/admin").hasAuthority("ROLE_ADMIN") // 権限制御
                .anyRequest().authenticated()
        );
        //http.csrf().disable();
        return http.build();
    }

    @Bean
    public UserDetailsManager users(DataSource dataSource) {
        String userQuery =
                "select user_id,password,true from m_user where user_id = ?";
        String authoritiesQuery =
                "select user_id,role from m_user where user_id = ?";
        JdbcUserDetailsManager users = new JdbcUserDetailsManager(dataSource);
        users.setUsersByUsernameQuery(userQuery);
        users.setAuthoritiesByUsernameQuery(authoritiesQuery);
        return users;
    }

//    @Bean
//    public InMemoryUserDetailsManager userDetailsService() {
//        PasswordEncoder encoder = passwordEncoder();
//        UserDetails user = User.withUsername("user").password(encoder.encode("user")).roles("GENERAL").build();
//        UserDetails admin = User.withUsername("admin").password(encoder.encode("admin")).roles("ADMIN").build();
//        return new InMemoryUserDetailsManager();
//    }
}
