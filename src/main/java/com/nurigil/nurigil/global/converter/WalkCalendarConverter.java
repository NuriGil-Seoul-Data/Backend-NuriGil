package com.nurigil.nurigil.global.converter;

import com.nurigil.nurigil.global.domain.entity.WalkCalendar;
import com.nurigil.nurigil.global.web.dto.WalkCalendar.WalkCalendarResponseDTO;

import java.util.List;

public class WalkCalendarConverter {

    // 산책 날짜를 변환하는 DTO
    public static WalkCalendarResponseDTO.DetailResponse toDetailWalkCalendar(WalkCalendar walkCalendar) {
        return WalkCalendarResponseDTO.DetailResponse.builder()
                .date(walkCalendar.getDate())
                .calories(walkCalendar.getCalories())
                .totalDistance(walkCalendar.getTotalDistance())
                .totalTime(walkCalendar.getTotalTime())
                .build();
    }

    // 모든 산책 날짜를 변환하는 DTO
    public static WalkCalendarResponseDTO.AllResponse toAllWalkCalendar(List<WalkCalendar> walkCalendars) {
        return WalkCalendarResponseDTO.AllResponse.builder()
                .responses(walkCalendars.stream().map(WalkCalendarConverter::toDetailWalkCalendar).toList())
                .build();
    }
}
