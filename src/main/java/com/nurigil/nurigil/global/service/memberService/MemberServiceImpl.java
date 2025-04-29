package com.nurigil.nurigil.global.service.memberService;

import com.nurigil.nurigil.global.apiPayload.code.status.ErrorStatus;
import com.nurigil.nurigil.global.apiPayload.exception.GeneralException;
import com.nurigil.nurigil.global.apiPayload.exception.auth.CustomAuthException;
import com.nurigil.nurigil.global.domain.entity.Member;
import com.nurigil.nurigil.global.domain.entity.type.Role;
import com.nurigil.nurigil.global.repository.MemberRepository;
import com.nurigil.nurigil.global.security.Token.TokenProvider;
import com.nurigil.nurigil.global.security.principal.PrincipalDetails;
import com.nurigil.nurigil.global.web.dto.member.AuthRequestDTO;
import com.nurigil.nurigil.global.web.dto.member.AuthResponseDTO;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenProvider tokenProvider;

    // 이메일 회원가입
    @Override
    @Transactional
    public void emailRegister(AuthRequestDTO.EmailRegisterRequest request) {
        // 이메일 중복 검사
        if(memberRepository.existsByEmail(request.getEmail())) {
            throw new GeneralException(ErrorStatus.MEMBER_EMAIL_ALREADY_EXISTS);
        }

        // 회원 생성
        Member member = Member.builder()
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .build();

        memberRepository.save(member);
    }

    // 이메일 로그인
    @Override
    public AuthResponseDTO.EmailLoginResponse emailLogin(AuthRequestDTO.EmailLoginRequest request) {

        // 이메일 검증
        Member member = memberRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new CustomAuthException(ErrorStatus.INVALID_LOGIN_EMAIL));

        // 비밀번호 검증
        if(!passwordEncoder.matches(request.getPassword(), member.getPassword())) {
            throw new CustomAuthException(ErrorStatus.INVALID_PASSWORD);
        }

        // authentication 생성
        PrincipalDetails principalDetails = new PrincipalDetails(member, Collections.emptyMap(), "email");
        Authentication authentication = new UsernamePasswordAuthenticationToken(principalDetails, null, principalDetails.getAuthorities());

        // 토큰 생성
        String accessToken = tokenProvider.generateAccessToken(authentication); // accessToken은 클라이언트가 들고다님
        String refreshToken = tokenProvider.generateRefreshToken(authentication, (String) accessToken); // 이건 백이 가지고 있어야 함.

        member.updateRefreshToken(refreshToken);
        memberRepository.save(member);

        return AuthResponseDTO.EmailLoginResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .memberId(member.getEmail())
                .build();
    }

    // 회원 탈퇴
    @Override
    @Transactional
    public AuthResponseDTO.DeleteMemberResponse deleteMember(Long id) {
        Member member = memberRepository.findById(id)
                        .orElseThrow(() -> new CustomAuthException(ErrorStatus.INVALID_LOGIN_EMAIL));
        memberRepository.delete(member);

        return AuthResponseDTO.DeleteMemberResponse.builder()
                .memberId(member.getMemberId())
                .memberEmail(member.getEmail())
                .build();
    }

    // 사용자 정보 조회
    @Override
    public AuthResponseDTO.GetMemberResponse getMamberInfo(Long id) {
        Member member = memberRepository.findById(id)
                .orElseThrow(() -> new CustomAuthException(ErrorStatus.ILLEGAL_REGISTRATION_ID));

        return AuthResponseDTO.GetMemberResponse.builder()
                .memberId(member.getMemberId())
                .memberEmail(member.getEmail())
                .memberName(member.getName())
                .memberUserWheel(member.getUsesWheel())
                .build();
    }

        @Override
        @Transactional
        public AuthResponseDTO.UpdateMemberResponse updateMemberInfo(Long id, AuthRequestDTO.UpdateMemberRequest request) {
            Member member = memberRepository.findById(id)
                    .orElseThrow(() -> new CustomAuthException(ErrorStatus.ILLEGAL_REGISTRATION_ID));

            // 사용자 정보 수정 완료
            member.setEmail(request.getEmail());
            member.setName(request.getName());
            member.setUsesWheel(request.getUsesWheel());
            memberRepository.save(member);

            return AuthResponseDTO.UpdateMemberResponse.builder()
                    .memberId(member.getMemberId())
                    .memberName(member.getName())
                    .memberEmail(member.getEmail())
                    .memberUserWheel(member.getUsesWheel())
                    .build();
        }

    @Override
    public Member findMemberByEmail(String email) {
        Member member = memberRepository.findByEmail(email)
                .orElseThrow(() -> new CustomAuthException(ErrorStatus.INVALID_LOGIN_EMAIL));

        return member;
    }

    @Override
    public void saveMember(Member member) {
        memberRepository.save(member);
    }

}
