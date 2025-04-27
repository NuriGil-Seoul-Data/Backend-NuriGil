package com.nurigil.nurigil.global.security.Token;

import com.nurigil.nurigil.global.apiPayload.code.status.ErrorStatus;
import com.nurigil.nurigil.global.apiPayload.exception.auth.CustomAuthException;
import com.nurigil.nurigil.global.domain.entity.Member;
import com.nurigil.nurigil.global.repository.MemberRepository;
import com.nurigil.nurigil.global.repository.TokenRepository;
import com.nurigil.nurigil.global.service.memberService.MemberService;
import jakarta.security.auth.message.AuthException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

import static com.nurigil.nurigil.global.apiPayload.code.status.ErrorStatus.AUTH_INVALID_TOKEN;
import static com.nurigil.nurigil.global.apiPayload.code.status.ErrorStatus.INVALID_REQUEST_INFO;

@Service
@RequiredArgsConstructor
public class TokenService {

    private final TokenRepository tokenRepository;
    private final MemberRepository memberRepository;

    //access_token으로 Token 조회
    public Token findByAccessTokenOrThrow(String accessToken) {
        return tokenRepository.findByAccessToken(accessToken)
                .orElseThrow(() -> new CustomAuthException(AUTH_INVALID_TOKEN));
    }

    //access token 갱신
    public void updateAccessToken(String newAccessToken, Token token) {
        token.updateAccessToken(newAccessToken);
        tokenRepository.save(token);
    }

    // access token 과 refresh token 생성
    public void save(String ProviderId ,String refreshToken, String accessToken) {
        Token token = Token.builder()
                .provideId(ProviderId)
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
        tokenRepository.save(token);
    }

    // token 삭제
    @Transactional
    public void deleteTokenByAccessToken(String accessToken) {
        Token token = findByAccessTokenOrThrow(accessToken);
        tokenRepository.delete(token);
    }

    // delete token by RefreshToken in member
    @Transactional
    public void deleteByRefreshToken(Long id) {

        // refresh token 얻음
        Member member = memberRepository.findById(id).orElseThrow(() -> new CustomAuthException(INVALID_REQUEST_INFO));
        String refreshToken = member.getRefreshToken();
        Iterable<Token> allTokens = tokenRepository.findAll();

        for (Token token : allTokens) {
            if (token.getRefreshToken() != null && token.getRefreshToken().equals(refreshToken)) {
                tokenRepository.deleteById(token.getProvideId());
                break; // 찾으면 삭제하고 바로 종료
            }
        }
    }

    // delete token by ProviderId
    public void deleteByProviderId(String providerId) {
        tokenRepository.deleteByProviderId(providerId);
    }
}
