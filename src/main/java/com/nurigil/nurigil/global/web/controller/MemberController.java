package com.nurigil.nurigil.global.web.controller;


import com.nurigil.nurigil.global.apiPayload.ApiResponse;
import com.nurigil.nurigil.global.apiPayload.code.status.SuccessStatus;
import com.nurigil.nurigil.global.domain.entity.Member;
import com.nurigil.nurigil.global.security.Token.Token;
import com.nurigil.nurigil.global.security.Token.TokenService;
import com.nurigil.nurigil.global.service.memberService.MemberService;
import com.nurigil.nurigil.global.web.dto.member.AuthRequestDTO;
import com.nurigil.nurigil.global.web.dto.member.AuthResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Validated
@CrossOrigin
@Slf4j
@RequestMapping("/members")
@Tag(name = "멤버 API", description = "멤버 API입니다.")
public class MemberController {

    private final MemberService memberService;
    private final TokenService tokenService;

    // 이메일 회원 가입 API
    @Operation(summary = "이메일 회원가입 API", description = "이메일 회원가입 API 입니다.")
    @PostMapping(   "/signup/email")
    public ApiResponse<?> Signup(@RequestBody @Valid AuthRequestDTO.EmailRegisterRequest request) {
        memberService.emailRegister(request);
        return ApiResponse.onSuccess(SuccessStatus.MEMBER_SIGNUP_OK, null);
    }

    // 이메일 로그인 API
    @Operation(summary = "이메일 로그인 API", description = "이메일과 비밀번호로 로그인하는 API입니다.")
    @PostMapping("/login/email")
    public ApiResponse<AuthResponseDTO.EmailLoginResponse> emailLogin(@RequestBody @Valid AuthRequestDTO.EmailLoginRequest request) {
        AuthResponseDTO.EmailLoginResponse response = memberService.emailLogin(request);
        return ApiResponse.onSuccess(SuccessStatus.MEMBER_SIGNUP_OK, response);
    }

    // 회원 탈퇴 API
    @Operation(summary = "회원 탈퇴 API", description = "회원 탈퇴 API입니다.")
    @DeleteMapping("delete/member")
    public ApiResponse<AuthResponseDTO.DeleteMemberResponse> DeleteMember(String email, String accessToken) {
        AuthResponseDTO.DeleteMemberResponse response = memberService.deleteMember(email);
        tokenService.deleteTokenByAccessToken(accessToken);
        return ApiResponse.onSuccess(SuccessStatus.MEMBER_DELETE_OK, response);
    }
}