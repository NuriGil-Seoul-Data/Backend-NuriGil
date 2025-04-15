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
@RequestMapping("/members/preference")
@Tag(name = "멤버 선호 API", description = "멤버 선호 API입니다.")
public class MemberPreferenceController {

    // 선호 설정 등록 API
    @Operation(summary = "선호 설정 등록 API", description = "선호 설정 등록 API 입니다.")
    @PostMapping(   "/{id}")
    public ApiResponse<?> PostMemberPreference() {
        return ApiResponse.onSuccess(SuccessStatus.MEMBER_PREFERENCE_POST_OK, null);
    }

    // 선호 설정 조회 API
    @Operation(summary = "선호 설정 조회 API", description = "선호 설정 조회 API 입니다.")
    @GetMapping(   "/{id}")
    public ApiResponse<?> GetMemberPreference() {
        return ApiResponse.onSuccess(SuccessStatus.MEMBER_PREFERENCE_GET_OK, null);
    }

    // 선호 설정 수정 API
    @Operation(summary = "선호 설정 수정 API", description = "선호 설정 수정 API 입니다.")
    @PatchMapping(   "/{id}")
    public ApiResponse<?> PatchMemberPreference() {
        return ApiResponse.onSuccess(SuccessStatus.MEMBER_PREFERENCE_PATCH_OK, null);
    }

}
