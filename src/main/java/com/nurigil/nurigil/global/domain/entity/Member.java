package com.nurigil.nurigil.global.domain.entity;

import com.nurigil.nurigil.global.domain.common.BaseEntity;
import com.nurigil.nurigil.global.domain.entity.type.Gender;
import com.nurigil.nurigil.global.domain.entity.type.PrefersWalk;
import com.nurigil.nurigil.global.domain.entity.type.Role;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Member extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id", nullable = false, columnDefinition = "bigint")
    private Long memberId;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "password")
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name = "gender")
    private Gender gender;

    @Column(name = "uses_wheel")
    private boolean usesWheel;

    @Enumerated(EnumType.STRING)
    @Column(name = "prefers_walk")
    private PrefersWalk prefersWalk;

    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private Role role;

    private String accessToken;
    private String refreshToken;

    public void updateToken(String accessToken, String refreshToken) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }
}
