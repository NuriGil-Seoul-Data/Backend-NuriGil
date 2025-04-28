package com.nurigil.nurigil.global.service.memberService;

import com.nurigil.nurigil.global.domain.entity.Member;
import com.nurigil.nurigil.global.web.dto.member.AuthRequestDTO;
import com.nurigil.nurigil.global.web.dto.member.AuthResponseDTO;
import org.springframework.security.core.Authentication;

public interface MemberService {

    // 이메일
    // 이메일 회원가입
    void emailRegister(AuthRequestDTO.EmailRegisterRequest request);
    AuthResponseDTO.EmailLoginResponse emailLogin(AuthRequestDTO.EmailLoginRequest request);

    // 회원탈퇴
    AuthResponseDTO.DeleteMemberResponse deleteMember(Long id);

    // 사용자 정보 조회
    AuthResponseDTO.GetMemberResponse getMamberInfo(Long id);

    // 사용자 정보 수정
    AuthResponseDTO.UpdateMemberResponse updateMemberInfo(Long id, AuthRequestDTO.UpdateMemberRequest request);

    // 사용자 get by email
    Member findMemberByEmail(String email);

    // member 저장
    void saveMember(Member member);
}
