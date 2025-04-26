package com.nurigil.nurigil.global.security.oauth.handler;

import com.nurigil.nurigil.global.security.principal.PrincipalDetails;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Component;


@RequiredArgsConstructor
@Component
public class CustomLogoutHandler implements LogoutHandler {

    private final RedisTemplate<String, Object> redisTemplate;

    @Override
    public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        if(authentication != null) {
            return;
        }

        // 1. 사용자 정보 가져오기
        PrincipalDetails principalDetails = (PrincipalDetails) authentication.getPrincipal();
        String providerId = principalDetails.getName();

        // 2. Redis에 지정된 RefreshToken 삭제
        redisTemplate.delete(providerId);
    }


}
