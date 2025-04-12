package com.nurigil.nurigil.global.security.Token;

import com.nurigil.nurigil.global.apiPayload.code.status.ErrorStatus;
import com.nurigil.nurigil.global.apiPayload.exception.auth.CustomAuthException;
import com.nurigil.nurigil.global.repository.TokenRepository;
import jakarta.security.auth.message.AuthException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.nurigil.nurigil.global.apiPayload.code.status.ErrorStatus.AUTH_INVALID_TOKEN;

@Service
@RequiredArgsConstructor
public class TokenService {

    private final TokenRepository tokenRepository;

    //access_token으로 Token 조회
    public Token findByAccessTokenOrThrow(String accessToken) {
        return tokenRepository.findByAccessToken(accessToken)
                .orElseThrow(() -> new CustomAuthException(AUTH_INVALID_TOKEN));
    }

    //access token 갱신
    public void updateToken(String newAccessToken, Token token) {
        token.updateAccessToken(newAccessToken);
        tokenRepository.save(token);
    }
}
