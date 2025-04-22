package com.nurigil.nurigil.global.domain.entity;

import com.nurigil.nurigil.global.domain.common.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class WalkCalendar extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "walk_calendar_id", nullable = false, columnDefinition = "bigint")
    private Long walkCalendarId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    private LocalDate date;

    private Float totalDistance;

    private Integer totalTime;

    private Float calories;
}
