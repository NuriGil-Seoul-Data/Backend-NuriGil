package com.nurigil.nurigil.global.web.controller;


import com.nurigil.nurigil.global.apiPayload.ApiResponse;
import com.nurigil.nurigil.global.apiPayload.code.status.SuccessStatus;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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

    // 회원 가입 API
    @Operation(summary = "회원가입 API", description = "회원가입 API 입니다.")
    @PostMapping(   "/signup")
    public ApiResponse<?> Signup() {
        return ApiResponse.onSuccess(SuccessStatus.MEMBER_SIGNUP_OK, null);
    }

    // 로그인 API
    @Operation(summary = "로그인 API", description = "로그인 API 입니다.")
    @PostMapping(   "/login")
    public ApiResponse<?> Login() {
        return ApiResponse.onSuccess(SuccessStatus.MEMBER_LOGIN_OK, null);
    }

    // 로그아웃 API
    @Operation(summary = "로그아웃 API", description = "로그아웃 API 입니다.")
    @PostMapping(   "/logout")
    public ApiResponse<?> Logout() {
        return ApiResponse.onSuccess(SuccessStatus.MEMBER_LOGOUT_OK, null);
    }

    // 사용자 정보 조회 API
    @Operation(summary = "사용자 정보 조회 API", description = "사용자 정보 조회 API 입니다.")
    @GetMapping(   "/{id}")
    public ApiResponse<?> GetMemberInfo() {
        return ApiResponse.onSuccess(SuccessStatus.MEMBER_INFO_GET_OK, null);
    }

    // 사용자 정보 수정 API
    @Operation(summary = "사용자 정보 수정 API", description = "사용자 정보 수정 API 입니다.")
    @PatchMapping(   "/{id}")
    public ApiResponse<?> PatchMemberInfo() {
        return ApiResponse.onSuccess(SuccessStatus.MEMBER_INFO_PATCH_OK, null);
    }

    // 회원 탈퇴 API
    @Operation(summary = "사용자 정보 수정 API", description = "사용자 정보 수정 API 입니다.")
    @DeleteMapping(   "/{id}")
    public ApiResponse<?> DeleteMember() {
        return ApiResponse.onSuccess(SuccessStatus.MEMBER_DELETE_OK, null);
    }

}
