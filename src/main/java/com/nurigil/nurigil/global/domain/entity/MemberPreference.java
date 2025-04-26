package com.nurigil.nurigil.global.domain.entity;

import com.nurigil.nurigil.global.domain.common.BaseEntity;
import com.nurigil.nurigil.global.domain.enums.Difficulty;
import com.nurigil.nurigil.global.domain.enums.Slope;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class MemberPreference extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_preference_id", nullable = false, columnDefinition = "bigint")
    private Long memberPreferenceId;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Difficulty difficulty;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Slope slope;

    public void updateMemberPreference(Difficulty difficulty, Slope slope) {
        this.difficulty = difficulty;
        this.slope = slope;
    }

}
