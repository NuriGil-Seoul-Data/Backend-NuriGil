package com.nurigil.nurigil.global.domain.entity;

import com.nurigil.nurigil.global.domain.common.BaseEntity;
import com.nurigil.nurigil.global.domain.enums.AmenityType;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class CourseAmenity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "course_amenity_id", nullable = false, columnDefinition = "bigint")
    private Long courseAmenityId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id", nullable = false)
    private Course course;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AmenityType type;

    @Column(columnDefinition = "POINT")
    private String location; // 위도/경도
}
