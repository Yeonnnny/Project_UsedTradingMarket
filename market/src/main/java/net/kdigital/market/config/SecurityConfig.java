package net.kdigital.market.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import lombok.RequiredArgsConstructor;
import net.kdigital.market.handler.CustomFailureHandler;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    // 오류 발생 시 오류 던져 받음
    private final CustomFailureHandler failureHandler;

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        // 웹 요청에 대한 접근 권한 설정
        http.authorizeHttpRequests((auth) -> auth.requestMatchers("/",
                "/board/boardList",
                "/user/join",
                "/user/joinProc",
                "/user/login",
                "/script/**",
                "/img/**").permitAll()
                .requestMatchers("/admin/**").hasRole("ADMIN")
                .requestMatchers("/my/**").hasAnyRole("ADMIN", "USER")
                .anyRequest().authenticated());

        // Custom login 설정
        http.formLogin((auth) -> auth.loginPage("/user/login")
                .failureHandler(failureHandler)
                .usernameParameter("memId")
                .passwordParameter("memPw")
                .loginProcessingUrl("/user/loginProc")
                .defaultSuccessUrl("/")
                .permitAll());

        // Custom logout 설정
        http.logout((auth) -> auth.logoutUrl("/user/logout")
                .logoutSuccessUrl("/")
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID"));

        http.csrf((auth) -> auth.disable());

        return http.build();
    }

    @Bean
    BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
