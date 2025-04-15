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
@RequestMapping("/members/courses")
@Tag(name = "멤버 코스 API", description = "멤버 코스 API입니다.")
public class MemberCourseController {

    // 코스 시작 API
    @Operation(summary = "코스 시작 API", description = "코스 시작 API 입니다.")
    @PostMapping(   "/{memberId}/{coursesId}")
    public ApiResponse<?> StartCourse() {
        return ApiResponse.onSuccess(SuccessStatus.MEMBER_COURSE_START_OK, null);
    }

    // 코스 종료 API
    @Operation(summary = "코스 종료 API", description = "코스 종료 API 입니다.")
    @PutMapping(   "/{memberId}/{coursesId}")
    public ApiResponse<?> FinishCourse() {
        return ApiResponse.onSuccess(SuccessStatus.MEMBER_COURSE_FINISH_OK, null);
    }

    // 저장된 or 수행한 코스 목록 조회 API
    @Operation(summary = "저장된 or 수행한 코스 목록 조회 API", description = "저장된 or 수행한 코스 목록 조회 API 입니다.")
    @GetMapping(   "/{memberId}")
    public ApiResponse<?> GetCourses() {
        return ApiResponse.onSuccess(SuccessStatus.MEMBER_COURSE_GET_OK, null);
    }

    // 특정 코스의 기록 조회 API
    @Operation(summary = "특정 코스의 기록 조회 API", description = "특정 코스의 기록 조회 API 입니다.")
    @GetMapping(   "/{memberId}/{coursesId}")
    public ApiResponse<?> GetDetailCourse() {
        return ApiResponse.onSuccess(SuccessStatus.MEMBER_COURSE_DETAIL_OK, null);
    }

    // 저장 취소 API
    @Operation(summary = "저장 취소 API", description = "저장 취소 API 입니다.")
    @DeleteMapping(   "/{memberId}/{coursesId}")
    public ApiResponse<?> RevertCourse() {
        return ApiResponse.onSuccess(SuccessStatus.MEMBER_COURSE_DELETE_OK, null);
    }
}
