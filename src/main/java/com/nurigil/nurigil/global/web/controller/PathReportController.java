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
@RequestMapping("/courses/report")
@Tag(name = "코스 신고 API", description = "코스 신고 API입니다.")
public class PathReportController {

    // 사진/위치 기반 신고 생성 API
    @Operation(summary = "사진/위치 기반 신고 생성 API", description = "사진/위치 기반 신고 생성 API 입니다.")
    @PostMapping(   "/{course_id}")
    public ApiResponse<?> CreateReport() {
        return ApiResponse.onSuccess(SuccessStatus.PATH_REPORT_POST_OK, null);
    }

    // 코스 내 신고 목록 조회 API
    @Operation(summary = "코스 내 신고 목록 조회 API", description = "코스 내 신고 목록 조회 API 입니다.")
    @GetMapping(   "/{id}")
    public ApiResponse<?> GetReports() {
        return ApiResponse.onSuccess(SuccessStatus.PATH_REPORT_GET_OK, null);
    }

}