package com.nurigil.nurigil.global.service.WalkCalendarService;

import com.nurigil.nurigil.global.web.dto.WalkCalendar.WalkCalendarResponseDTO;

import java.time.LocalDate;

public interface WalkCalendarService {
    WalkCalendarResponseDTO.AllResponse getAllWalkCalendar(Long memberId);
    WalkCalendarResponseDTO.AllResponse getDetailCalendar(Long memberId, LocalDate date);
}
