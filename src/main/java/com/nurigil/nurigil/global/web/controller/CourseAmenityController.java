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
@RequestMapping("/courses/amenity")
@Tag(name = "코스 편의 시설 API", description = "코스 편의 시설 API입니다.")
public class CourseAmenityController {

    // 특정 코스의 편의 시설 조회 API
    @Operation(summary = "특정 코스의 편의시설 조회 API", description = "특정 코스의 편의시설 조회 API 입니다.")
    @GetMapping(   "/{id}")
    public ApiResponse<?> GetCourseAmenity() {
        return ApiResponse.onSuccess(SuccessStatus.COURSE_AMENITY_OK, null);
    }
}


