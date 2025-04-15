package com.nurigil.nurigil.global.domain.entity;

import com.nurigil.nurigil.global.domain.common.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class MemberCourse extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_course_id", nullable = false, columnDefinition = "bigint")
    private Long memberCourseId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id", nullable = false)
    private Course course;

    private Boolean isSaved;

    private Boolean isFinished;

    private LocalDateTime startedAt;

    private LocalDateTime endedAt;

    private Integer steps;

    private Float distanceWalked;

    private Float calories;
}

