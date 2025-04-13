package com.nurigil.nurigil.global.security.oauth.filter;

import com.nurigil.nurigil.global.security.Token.TokenProvider;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@RequiredArgsConstructor
@Component
public class TokenAuthenticationFilter extends OncePerRequestFilter {

    private final TokenProvider tokenProvider;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response, FilterChain filterChain) throws SecurityException, IOException, ServletException {
        String accessToken = resolveToken(request);

        if (!StringUtils.hasText(accessToken)) {
            filterChain.doFilter(request, response);
            System.out.println("⚠️ 토큰 없음. 필터 통과.");
            return;
        }

        // validate the accessToken
        if(tokenProvider.validateToken(accessToken)) {
            System.out.println("✅ 유효한 토큰. 인증 정보 설정.");
            setAuthentication(accessToken);
        } else {
            // If it's expired -> refresh the access token
            System.out.println("⛔ 만료 혹은 잘못된 토큰.");
            String reissueAccessToken = tokenProvider.reissueAccessToken(accessToken);

            if(StringUtils.hasText(reissueAccessToken)) {
                setAuthentication(reissueAccessToken);

                // 재발급된 accessToken 다시 전달
                response.setHeader("Authorization", "Bearer " + reissueAccessToken);
            }
        }
        filterChain.doFilter(request, response);
    }

    private void setAuthentication(String accessToken) {
        Authentication authentication = tokenProvider.getAuthentication(accessToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    private String resolveToken(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        if(ObjectUtils.isEmpty(token) || !token.startsWith("Bearer ")) {
            return null;
        }
        return token.substring("Bearer ".length());
    }

}
