package com.nurigil.nurigil.global.security.oauth.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nurigil.nurigil.global.apiPayload.code.status.ErrorStatus;
import com.nurigil.nurigil.global.apiPayload.code.status.SuccessStatus;
import com.nurigil.nurigil.global.apiPayload.exception.auth.CustomAuthException;
import com.nurigil.nurigil.global.domain.entity.Member;
import com.nurigil.nurigil.global.repository.MemberRepository;
import com.nurigil.nurigil.global.security.Token.TokenProvider;
import com.nurigil.nurigil.global.security.Token.TokenService;
import com.nurigil.nurigil.global.security.principal.PrincipalDetails;
import com.nurigil.nurigil.global.service.memberService.MemberService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@Component
public class OAuth2SuccessHandler implements AuthenticationSuccessHandler {

    private final TokenProvider tokenProvider;
    private static final String URI = "/auth/token ";
    private final ObjectMapper objectMapper;
    private final TokenService tokenService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response, Authentication authentication) throws IOException, ServletException {


        // accessToken, refreshToken 발급
        String accessToken = tokenProvider.generateAccessToken(authentication);
        String refreshToken = tokenProvider.generateRefreshToken(authentication, accessToken);

        PrincipalDetails principalDetails = (PrincipalDetails) authentication.getPrincipal();
        String email = principalDetails.member().getEmail();

        // token redis에 저장
        tokenService.save(authentication.getName(), accessToken, refreshToken);

        // 응답 데이터 생성
        Map<String, Object> tokenResponse = new HashMap<>();
        tokenResponse.put("access_token", accessToken);
        tokenResponse.put("refresh_token", accessToken);
        tokenResponse.put("memberId", email);
        // tokenResponse.put("redirectUri", "/home")

        // 응답 세팅
        response.setStatus(HttpServletResponse.SC_OK);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        // 응답을 Json에 담아서 보냄
        objectMapper.writeValue(response.getWriter(), tokenResponse);
    }
}
