package com.nurigil.nurigil.global.config;


import com.nurigil.nurigil.global.security.oauth.CustomOAuth2UserService;

import com.nurigil.nurigil.global.security.oauth.filter.TokenAuthenticationFilter;
import com.nurigil.nurigil.global.security.oauth.filter.TokenExceptionFilter;
import com.nurigil.nurigil.global.security.oauth.handler.CustomAccessDeniedHandler;
import com.nurigil.nurigil.global.security.oauth.handler.CustomAuthenticationEntryPoint;
import com.nurigil.nurigil.global.security.oauth.handler.CustomLogoutHandler;
import com.nurigil.nurigil.global.security.oauth.handler.OAuth2SuccessHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final CustomOAuth2UserService oAuth2UserService;
    private final TokenAuthenticationFilter tokenAuthenticationFilter;
    private final OAuth2SuccessHandler oAuth2SuccessHandler;
    private final CustomLogoutHandler logoutHandler;


    private static final String[] SECURITY_ALLOW_ARRAY = {
            // swagger 관련
            "/swagger-ui/**",
            "/swagger-resources/**",
            "/v3/api-docs/**",
            "/courses/**",
            "/members/**"
    };

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .httpBasic(AbstractHttpConfigurer::disable)
                .csrf(AbstractHttpConfigurer::disable)
                .formLogin(AbstractHttpConfigurer::disable)
                .sessionManagement(session
                        -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(SECURITY_ALLOW_ARRAY).permitAll()
                        .requestMatchers(
                                // 로그인 관련 접근
                                new AntPathRequestMatcher("/api/**"),
                                new AntPathRequestMatcher("/oauth2/**"),
                                new AntPathRequestMatcher("/auth/token"),
                                new AntPathRequestMatcher("/members/**") // 이메일 로그인 및 회원가입
                        ).permitAll()
                        .anyRequest().authenticated()
                )

                //oauth2 설정
                .oauth2Login(oauth->
                        oauth.userInfoEndpoint(userInfo -> userInfo.userService(oAuth2UserService))
                                .successHandler(oAuth2SuccessHandler)
                )

                //jwt 설정
                .addFilterBefore(tokenAuthenticationFilter, // 로그인 시도 전에 헤더를 통한 토큰 필터
                        UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(new TokenExceptionFilter(), tokenAuthenticationFilter.getClass())

                // 인증 예외 핸들링
                .exceptionHandling((exceptions) -> exceptions
                        .authenticationEntryPoint(new CustomAuthenticationEntryPoint()) // 토큰이 없는 사람이 접근했을 예외
                        .accessDeniedHandler(new CustomAccessDeniedHandler())) // 인증은 되었지만 접근 권한이 없는 경우

                // 로그아웃
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/")
                        .addLogoutHandler(logoutHandler)
                        .invalidateHttpSession(true) //세션 무효화
                        .clearAuthentication(true) // SecurityContext 비우기
                        .deleteCookies("JSESSIONID") // 세션 쿠키 삭제
                )
                .build();

    }


    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.setAllowedOriginPatterns(List.of("*", "http://localhost:3000"));
        config.setAllowedOrigins(List.of("http://localhost:3000"));
        config.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "PATCH", "OPTIONS"));
        config.setAllowedHeaders(List.of("*"));
        config.setExposedHeaders(List.of("*"));

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        return source;
    }

    @Bean
    public PasswordEncoder passwordEncoder() { return new BCryptPasswordEncoder(); }
}
