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
@RequestMapping("/members/calendar")
@Tag(name = "코스 신고 API", description = "코스 신고 API입니다.")
public class WalkCalendarController {

    // 산책 달력 데이터 전체 조회 API
    @Operation(summary = "산책 달력 데이터 전체 조회 API", description = "산책 달력 데이터 전체 조회 API 입니다.")
    @GetMapping(   "/{member_id}")
    public ApiResponse<?> GetCalendars() {
        return ApiResponse.onSuccess(SuccessStatus.WALK_CALENDAR_OK, null);
    }

    // 특정 날짜 산책 기록 조회 API
    @Operation(summary = "특정 날짜 산책 기록 조회 API", description = "특정 날짜 산책 기록 조회 API 입니다.")
    @GetMapping(   "/{member_id}/{date}")
    public ApiResponse<?> GetDetailCalendar() {
        return ApiResponse.onSuccess(SuccessStatus.WALK_CALENDAR_DETAIL_OK, null);
    }

}