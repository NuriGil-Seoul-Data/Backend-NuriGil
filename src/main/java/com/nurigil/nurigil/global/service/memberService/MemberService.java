package com.nurigil.nurigil.global.service.memberService;

import com.nurigil.nurigil.global.web.dto.member.AuthRequestDTO;

public interface MemberService {

    void emailRegister(AuthRequestDTO.EmailRegisterRequest request);
}
