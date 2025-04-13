package com.nurigil.nurigil.global.security.oauth;

import com.nurigil.nurigil.global.apiPayload.exception.auth.CustomAuthException;
import com.nurigil.nurigil.global.domain.entity.Member;
import com.nurigil.nurigil.global.domain.entity.type.Role;
import jakarta.security.auth.message.AuthException;
import lombok.Builder;
import lombok.Getter;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.Map;

import static com.nurigil.nurigil.global.apiPayload.code.status.ErrorStatus.ILLEGAL_REGISTRATION_ID;


@Builder
public record OAuth2UserInfo(
        String name,
        String email,
        Map<String, Object> attributes,
        String userNameAttributes
) {



    public static OAuth2UserInfo of(String registrationId, Map<String, Object> attributes, String userNameAttribute) {
        return switch (registrationId) { // registration id별로 userInfo 생성
            case "naver" -> ofNaver(attributes, userNameAttribute);
            case "kakao" -> ofKakao(attributes, userNameAttribute);
            default -> throw new CustomAuthException(ILLEGAL_REGISTRATION_ID);
        };
    }

    private static OAuth2UserInfo ofNaver(Map<String, Object> attributes, String userNameAttribute) {
        return OAuth2UserInfo.builder()
                .name((String) attributes.get("name"))
                .email((String) attributes.get("email"))
                .attributes(attributes)
                .userNameAttributes(userNameAttribute)
                .build();
    }

    private static OAuth2UserInfo ofKakao(Map<String, Object> attributes, String userNameAttribute) {
        Map<String, Object> account = (Map<String, Object>) attributes.get("kakao_account");
        Map<String, Object> profile = (Map<String, Object>) account.get("profile");

        return OAuth2UserInfo.builder()
                .name((String) profile.get("nickname")) //카카오에선 닉네임이 이름이다.
                .email((String) account.get("email"))
                .attributes(attributes)
                .userNameAttributes(userNameAttribute)
                .build();
    }

    public Member toEntity() {
        return Member.builder()
                .name(name)
                .email(email)
                .role(Role.USER)
                .build();
    }

    public Map<String, Object> getAttributes() {
        return attributes;
    }
}
