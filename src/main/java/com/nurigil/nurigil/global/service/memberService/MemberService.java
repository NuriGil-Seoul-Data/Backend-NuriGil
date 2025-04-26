package com.nurigil.nurigil.global.service.memberService;

import com.nurigil.nurigil.global.web.dto.member.AuthRequestDTO;
import com.nurigil.nurigil.global.web.dto.member.AuthResponseDTO;

public interface MemberService {

    void emailRegister(AuthRequestDTO.EmailRegisterRequest request);

    AuthResponseDTO.EmailLoginResponse emailLogin(AuthRequestDTO.EmailLoginRequest request);
}
