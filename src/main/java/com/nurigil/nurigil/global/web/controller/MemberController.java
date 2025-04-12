package com.nurigil.nurigil.global.web.controller;


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
@RequestMapping("/member")
@Tag(name = "멤버 API", description = "멤버 API입니다.")
public class MemberController {

    // 예시 로그인 API
    @Operation(summary = "예시 로그인 API", description = "예시 로그인 API")
    @PostMapping("/login")
    public String Login() {
        return "success";
    }



}
