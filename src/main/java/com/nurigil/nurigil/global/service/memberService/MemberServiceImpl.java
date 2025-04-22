package com.nurigil.nurigil.global.service.memberService;

import com.nurigil.nurigil.global.apiPayload.code.status.ErrorStatus;
import com.nurigil.nurigil.global.apiPayload.exception.GeneralException;
import com.nurigil.nurigil.global.domain.entity.Member;
import com.nurigil.nurigil.global.repository.MemberRepository;
import com.nurigil.nurigil.global.web.dto.member.AuthRequestDTO;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;


    @Override
    @Transactional
    public void emailRegister(AuthRequestDTO.EmailRegisterRequest request) {
        // 이메일 중복 검사
        if(memberRepository.existsByEmail(request.getEmail())) {
            throw new GeneralException(ErrorStatus.EMAIL_EXISTS);
        }

        // 회원 생성
        Member member = Member.builder()
                .email(request.getEmail())
                .password(request.getPassword())
                .build();

        memberRepository.save(member);
    }
}
