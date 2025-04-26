package com.nurigil.nurigil.global.repository;

import com.nurigil.nurigil.global.domain.entity.Member;
import com.nurigil.nurigil.global.domain.entity.WalkCalendar;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WalkCalendarRepository extends JpaRepository<WalkCalendar, Long> {
    List<WalkCalendar> findAllByMember(Member member);
}
