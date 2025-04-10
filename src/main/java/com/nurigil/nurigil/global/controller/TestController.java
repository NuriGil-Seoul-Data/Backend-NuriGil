package com.nurigil.nurigil.global.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "Test API", description = "Swagger 테스트용 컨트롤러입니다.")
public class TestController {

    @GetMapping("/api/hello")
    @Operation(summary = "Hello 메시지 반환", description = "입력된 이름을 받아 환영 메시지를 반환합니다.")
    public String sayHello(@RequestParam(defaultValue = "World") String name) {
        return "Hello, " + name + "!";
    }
}
