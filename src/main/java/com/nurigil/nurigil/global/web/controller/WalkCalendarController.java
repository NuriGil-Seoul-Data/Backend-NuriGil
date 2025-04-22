package com.nurigil.nurigil.global.web.controller;

import com.nurigil.nurigil.global.apiPayload.ApiResponse;
import com.nurigil.nurigil.global.apiPayload.code.status.SuccessStatus;
import com.nurigil.nurigil.global.service.WalkCalendarService.WalkCalendarService;
import com.nurigil.nurigil.global.web.dto.WalkCalendar.WalkCalendarResponseDTO;
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
@RequestMapping("/members/calendar")
@Tag(name = "산책 달력 API", description = "산책 달력 API입니다.")
public class WalkCalendarController {

    private final WalkCalendarService walkCalendarService;

    // 산책 달력 데이터 전체 조회 API
    @Operation(summary = "산책 달력 데이터 전체 조회 API", description = "산책 달력 데이터 전체 조회 API 입니다.")
    @Parameters({
            @Parameter(name = "memberId", description = "멤버 ID, 추후 hidden으로 수정 예정")
    })
    @GetMapping("/{memberId}")
    public ApiResponse<WalkCalendarResponseDTO.AllResponse> GetCalendars(
            @PathVariable("memberId") Long memberId
    ) {
        WalkCalendarResponseDTO.AllResponse response = walkCalendarService.getWalkCalendar(memberId);
        return ApiResponse.onSuccess(SuccessStatus.WALK_CALENDAR_OK, response);
    }

    // 특정 날짜 산책 기록 조회 API
    @Operation(summary = "특정 날짜 산책 기록 조회 API", description = "특정 날짜 산책 기록 조회 API 입니다.")
    @GetMapping(   "/{member_id}/{date}")
    public ApiResponse<?> GetDetailCalendar() {
        return ApiResponse.onSuccess(SuccessStatus.WALK_CALENDAR_DETAIL_OK, null);
    }

}