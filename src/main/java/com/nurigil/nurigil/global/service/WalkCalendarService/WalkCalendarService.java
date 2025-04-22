package com.nurigil.nurigil.global.service.WalkCalendarService;

import com.nurigil.nurigil.global.web.dto.WalkCalendar.WalkCalendarResponseDTO;

public interface WalkCalendarService {
    WalkCalendarResponseDTO.AllResponse getWalkCalendar(Long memberId);
}
