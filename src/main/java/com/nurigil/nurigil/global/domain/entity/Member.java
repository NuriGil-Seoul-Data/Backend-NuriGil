package com.nurigil.nurigil.global.domain.entity;

import com.nurigil.nurigil.global.domain.common.BaseEntity;
import com.nurigil.nurigil.global.domain.entity.type.Role;
import com.nurigil.nurigil.global.domain.enums.Gender;
import com.nurigil.nurigil.global.domain.enums.PreferenceWalk;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Member extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id", nullable = false, columnDefinition = "bigint")
    private Long memberId;

    @Column(length = 45, unique = true)
    private String email;

    @Column(name = "name")
    private String name;

    private String password;

    private int age;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    private Boolean usesWheel;

    private String refreshToken;

    private Role role;

    @Builder.Default
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PreferenceWalk preferenceWalk =  PreferenceWalk.WALK;

    @OneToMany(mappedBy = "member")
    private List<MemberCourse> memberCourses = new ArrayList<>();

    @OneToMany(mappedBy = "member")
    private List<WalkCalendar> walkCalendars = new ArrayList<>();

    @OneToMany(mappedBy = "member")
    private List<PathReport> pathReports = new ArrayList<>();

    @OneToMany(mappedBy = "member")
    private List<EmergencyCall> emergencyCalls = new ArrayList<>();

    @OneToOne(mappedBy = "member", cascade = CascadeType.ALL)
    private MemberPreference memberPreference;

    public void updateRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

}
