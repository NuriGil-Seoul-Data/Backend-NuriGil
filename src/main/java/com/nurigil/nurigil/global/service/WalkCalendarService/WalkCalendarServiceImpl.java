package com.nurigil.nurigil.global.service.WalkCalendarService;

import com.nurigil.nurigil.global.apiPayload.code.status.ErrorStatus;
import com.nurigil.nurigil.global.apiPayload.exception.handler.MemberHandler;
import com.nurigil.nurigil.global.apiPayload.exception.handler.WalkCalendarHandler;
import com.nurigil.nurigil.global.converter.WalkCalendarConverter;
import com.nurigil.nurigil.global.domain.entity.Member;
import com.nurigil.nurigil.global.domain.entity.WalkCalendar;
import com.nurigil.nurigil.global.repository.MemberRepository;
import com.nurigil.nurigil.global.repository.WalkCalendarRepository;
import com.nurigil.nurigil.global.web.dto.WalkCalendar.WalkCalendarResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class WalkCalendarServiceImpl implements WalkCalendarService{

    private final MemberRepository memberRepository;
    private final WalkCalendarRepository walkCalendarRepository;

    // 모든 날짜의 산책 달력 조회
    @Override
    public WalkCalendarResponseDTO.AllResponse getAllWalkCalendar(Long memberId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberHandler(ErrorStatus.MEMBER_ID_NULL));

        List<WalkCalendar> calendarList = walkCalendarRepository.findAllByMember(member);
        if (calendarList.isEmpty()) throw new WalkCalendarHandler(ErrorStatus.CALENDAR_NOT_FOUND);
        else return WalkCalendarConverter.toAllWalkCalendar(calendarList);
    }

    // 특정 날짜의 산책 달력 조회
    @Override
    public WalkCalendarResponseDTO.AllResponse getDetailCalendar(Long memberId, LocalDate date) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberHandler(ErrorStatus.MEMBER_ID_NULL));

        List<WalkCalendar> calendarList = walkCalendarRepository.findAllByMemberAndDate(member, date);
        if (calendarList.isEmpty()) throw new WalkCalendarHandler(ErrorStatus.CALENDAR_NOT_FOUND);
        else return WalkCalendarConverter.toAllWalkCalendar(calendarList);
    }
}
