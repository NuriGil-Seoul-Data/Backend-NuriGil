package com.nurigil.nurigil.global.service.memberService;

import com.nurigil.nurigil.global.web.dto.member.AuthRequestDTO;
import com.nurigil.nurigil.global.web.dto.member.AuthResponseDTO;
import org.springframework.security.core.Authentication;

public interface MemberService {

    // 이메일
    // 이메일 회원가입
    void emailRegister(AuthRequestDTO.EmailRegisterRequest request);
    AuthResponseDTO.EmailLoginResponse emailLogin(AuthRequestDTO.EmailLoginRequest request);

    // 회원탈퇴
    AuthResponseDTO.DeleteMemberResponse deleteMember(String email);
}
