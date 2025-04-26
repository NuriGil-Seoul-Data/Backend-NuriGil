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
@RequestMapping("/courses")
@Tag(name = "코스 API", description = "코스 API입니다.")
public class CourseController {

    // 전체 코스 조회 API
    @Operation(summary = "전체 코스 조회 API", description = "전체 코스 조회 API 입니다.")
    @GetMapping(   "/")
    public ApiResponse<?> GetAllCourses() {
        return ApiResponse.onSuccess(SuccessStatus.COURSE_ALL_OK, null);
    }

    // 특정 코스 상세 조회 API
    @Operation(summary = "특정 코스 상세 조회 API", description = "특정 코스 상세 조회 API 입니다.")
    @GetMapping(   "/{id}")
    public ApiResponse<?> GetDetailCourse() {
        return ApiResponse.onSuccess(SuccessStatus.COURSE_DETAIL_OK, null);
    }

    // 특정 코스 삭제 API
    @Operation(summary = "특정 코스 삭제 API", description = "특정 코스 삭제 API 입니다.")
    @DeleteMapping(   "/{id}")
    public ApiResponse<?> DeleteCourse() {
        return ApiResponse.onSuccess(SuccessStatus.COURSE_DELETE_OK, null);
    }
}

