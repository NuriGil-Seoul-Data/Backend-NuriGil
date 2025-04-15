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
@RequestMapping("/members/emergency")
@Tag(name = "긴급 호출 API", description = "긴급 호출 API입니다.")
public class EmergencyCallController {

    // 긴급 호출 요청 API
    @Operation(summary = "긴급 호출 요청 API", description = "긴급 호출 요청 API 입니다.")
    @PostMapping(   "/{member_id}")
    public ApiResponse<?> PostEmergency() {
        return ApiResponse.onSuccess(SuccessStatus.EMERGENCY_CALL_OK, null);
    }

}