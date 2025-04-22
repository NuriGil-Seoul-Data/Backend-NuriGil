package com.nurigil.nurigil.global.service.WalkCalendarService;

import com.nurigil.nurigil.global.apiPayload.code.status.ErrorStatus;
import com.nurigil.nurigil.global.apiPayload.exception.handler.MemberHandler;
import com.nurigil.nurigil.global.converter.WalkCalendarConverter;
import com.nurigil.nurigil.global.domain.entity.Member;
import com.nurigil.nurigil.global.domain.entity.WalkCalendar;
import com.nurigil.nurigil.global.repository.MemberRepository;
import com.nurigil.nurigil.global.repository.WalkCalendarRepository;
import com.nurigil.nurigil.global.web.dto.WalkCalendar.WalkCalendarResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WalkCalendarServiceImpl implements WalkCalendarService{

    private final MemberRepository memberRepository;
    private final WalkCalendarRepository walkCalendarRepository;

    @Override
    public WalkCalendarResponseDTO.AllResponse getWalkCalendar(Long memberId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberHandler(ErrorStatus.MEMBER_ID_NULL));

        List<WalkCalendar> calendarList = walkCalendarRepository.findAllByMember(member);

        return WalkCalendarConverter.toAllWalkCalendar(calendarList);
    }
}
