package com.nurigil.nurigil.global.web.controller;

import com.nurigil.nurigil.global.apiPayload.ApiResponse;
import com.nurigil.nurigil.global.apiPayload.code.status.SuccessStatus;
import com.nurigil.nurigil.global.service.MemberPreferenceService.MemberPreferenceService;
import com.nurigil.nurigil.global.web.dto.MemberPreference.MemberPreferenceRequestDTO;
import com.nurigil.nurigil.global.web.dto.MemberPreference.MemberPreferenceResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
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

    private final MemberPreferenceService memberPreferenceService;

    // 선호 설정 등록 API
    @Operation(summary = "선호 설정 등록 API", description = "선호 설정 등록 API 입니다.")
    @Parameters({
            @Parameter(name = "memberId", description = "멤버 ID, 차후 hidden으로 수정 예정", required = false)
    })
    @PostMapping("/{memberId}")
    public ApiResponse<MemberPreferenceResponseDTO.Response> CreateMemberPreference(
            @PathVariable("memberId") Long memberId,
            @RequestBody MemberPreferenceRequestDTO.Request request) {
        MemberPreferenceResponseDTO.Response response = memberPreferenceService.createMemberPreference(memberId, request);
        return ApiResponse.onSuccess(SuccessStatus.MEMBER_PREFERENCE_POST_OK, response);
    }

    // 선호 설정 조회 API
    @Operation(summary = "선호 설정 조회 API", description = "선호 설정 조회 API 입니다.")
    @Parameters({
            @Parameter(name = "memberId", description = "멤버 ID, 차후 hidden으로 수정 예정", required = false)
    })
    @GetMapping("/{memberId}")
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
