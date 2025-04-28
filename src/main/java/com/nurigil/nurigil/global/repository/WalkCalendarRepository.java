package com.nurigil.nurigil.global.repository;

import com.nurigil.nurigil.global.domain.entity.Member;
import com.nurigil.nurigil.global.domain.entity.WalkCalendar;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface WalkCalendarRepository extends JpaRepository<WalkCalendar, Long> {
    List<WalkCalendar> findAllByMember(Member member);
    List<WalkCalendar> findAllByMemberAndDate(Member member, LocalDate date);
}
