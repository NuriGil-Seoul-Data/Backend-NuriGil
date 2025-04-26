package com.nurigil.nurigil.global.web.dto.WalkCalendar;

import lombok.*;

import java.time.LocalDate;
import java.util.List;

public class WalkCalendarResponseDTO {

    // 모든 날짜의 산책 기록
    @Getter
    @Builder
    @AllArgsConstructor
    @RequiredArgsConstructor
    public static class AllResponse {
        private List<DetailResponse> responses;
    }

    @Getter
    @Builder
    @AllArgsConstructor
    @RequiredArgsConstructor
    // 특정 날짜의 산책 기록
    public static class DetailResponse {
        private LocalDate date;
        private Float totalDistance;
        private Integer totalTime;
        private Float calories;
    }
}
