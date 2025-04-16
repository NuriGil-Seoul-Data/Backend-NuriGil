package com.nurigil.nurigil.global.security.oauth;

import com.nurigil.nurigil.global.apiPayload.exception.auth.CustomAuthException;
import com.nurigil.nurigil.global.domain.entity.Member;
import com.nurigil.nurigil.global.domain.entity.type.Role;
import lombok.Builder;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

import static com.nurigil.nurigil.global.apiPayload.code.status.ErrorStatus.ILLEGAL_REGISTRATION_ID;

@Builder
public record OAuth2UserInfo(
        String name,
        String email,
        String nickname,
        Map<String, Object> attributes
) {
    public static OAuth2UserInfo of(String registrationId, Map<String, Object> attributes) {
        return switch (registrationId) { // registration id별로 userInfo 생성
            case "naver" -> ofNaver(attributes);
            case "kakao" -> ofKakao(attributes);
            default -> throw new CustomAuthException(ILLEGAL_REGISTRATION_ID);
        };
    }

    private static OAuth2UserInfo ofNaver(Map<String, Object> attributes) {
        Map<String, Object> response = (Map<String, Object>) attributes.get("response");

        Map<String, Object> flatAttributes = new HashMap<>();
        flatAttributes.put("response", response.get("id")); // ★ 반드시 있어야 함
        flatAttributes.put("email", response.get("email"));
        flatAttributes.put("nickname", response.get("name"));

        return OAuth2UserInfo.builder()
                .name(String.valueOf(response.get("id")))
                .email((String) response.get("email"))
                .nickname((String) response.get("name"))
                .attributes(flatAttributes)
                .build();
    }

    private static OAuth2UserInfo ofKakao(Map<String, Object> attributes) {
        Map<String, Object> account = (Map<String, Object>) attributes.get("kakao_account");
        Map<String, Object> profile = (Map<String, Object>) account.get("profile");

        return OAuth2UserInfo.builder()
                .name(String.valueOf(attributes.get("id"))) //Oauth2UserInfo에서 고유의 이름으로는 id가 적절하다
                .email((String) account.get("email"))
                .nickname((String)profile.get("nickname"))
                .attributes(attributes)
                .build();
    }

    public Member toEntity() {
        return Member.builder()
                .name(nickname)
                .email(email)
                .role(Role.USER)
                .build();
    }



}
