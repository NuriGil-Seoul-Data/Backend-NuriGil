package com.nurigil.nurigil.global.security.oauth;

import com.nurigil.nurigil.global.domain.entity.Member;
import com.nurigil.nurigil.global.repository.MemberRepository;
import com.nurigil.nurigil.global.security.Token.TokenProvider;
import com.nurigil.nurigil.global.security.principal.PrincipalDetails;
import io.lettuce.core.ScriptOutputType;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Map;

@RequiredArgsConstructor
@Service
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

    private final MemberRepository memberRepository;
    private final TokenProvider tokenProvider;

    @Transactional
    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {

        // 유저 정보 가져오기
        Map<String, Object> oAuth2UserAttributes = super.loadUser(userRequest).getAttributes();

        // registration ID 가져오기(네이버인지 카카오인지)
        String registrationId = userRequest.getClientRegistration().getRegistrationId();

        // userNameAttribution 가져오기
        String userNameAttributeName = userRequest.getClientRegistration().getProviderDetails()
                .getUserInfoEndpoint().getUserNameAttributeName();

        System.out.println(userNameAttributeName);

        // 유저 정보 DTO
        OAuth2UserInfo oAuth2UserInfo = OAuth2UserInfo.of(registrationId, oAuth2UserAttributes);

        // 멤버 회원 가입 및 로그인
        Member member = getOrSave(oAuth2UserInfo, registrationId, userNameAttributeName);

        // oAuth2User로 변환
        return new PrincipalDetails(member, oAuth2UserInfo.attributes(), userNameAttributeName);
    }

    private Member getOrSave(OAuth2UserInfo oAuth2UserInfo, String registrationId, String userNameAttributeName) {
        // 로그인 및 회원가입
        Member member = memberRepository.findByEmail(oAuth2UserInfo.email())
                .orElseGet(() -> {
                    // 새로운 회원 만듦
                    Member newMember = oAuth2UserInfo.toEntity();
                    // 이제 토큰 만들어야할 차례
                    saveToken(newMember, oAuth2UserInfo, registrationId, userNameAttributeName);

                    return memberRepository.save(newMember);
                        }
                );

        return memberRepository.save(member);
    }

    private void saveToken(Member member, OAuth2UserInfo oAuth2UserInfo, String registrationId, String userNameAttributeName) {
        // accessToken, refreshToken 생성
        OAuth2User oAuth2User = new PrincipalDetails(member, oAuth2UserInfo.attributes(), userNameAttributeName);
        Authentication authentication = new OAuth2AuthenticationToken(oAuth2User, oAuth2User.getAuthorities(), registrationId);

        String accessToken = tokenProvider.generateAccessToken(authentication);
        tokenProvider.generateRefreshToken(authentication, accessToken); // refresh라는 것은 결국엔 로그인 아니면 회원가입 후 로그인이라는 뜻

    }
}
