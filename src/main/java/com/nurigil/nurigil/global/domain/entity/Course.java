package com.nurigil.nurigil.global.domain.entity;

import com.nurigil.nurigil.global.domain.common.BaseEntity;
import com.nurigil.nurigil.global.domain.enums.SafeAreaType;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Course extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "course_id", nullable = false, columnDefinition = "bigint")
    private Long courseId;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Float distance; // km 단위 추천

    private Integer estimatedTime; // 분 단위

    @Column(columnDefinition = "json")
    private String path; // JSON 경로 데이터

    private Float slopeGrade; // 평균 경사도

    private Boolean hasStairs;

    private Boolean hasObstacles;

    @Enumerated(EnumType.STRING)
    private SafeAreaType isSafeArea;

    @Column(columnDefinition = "POINT")
    private String location; // 위도/경도

    @OneToMany(mappedBy = "course")
    private List<MemberCourse> memberCourses = new ArrayList<>();

    @OneToMany(mappedBy = "course")
    private List<CourseAmenity> amenities = new ArrayList<>();

    @OneToMany(mappedBy = "course")
    private List<PathReport> pathReports = new ArrayList<>();
}
