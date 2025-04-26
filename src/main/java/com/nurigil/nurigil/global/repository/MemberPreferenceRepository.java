package com.nurigil.nurigil.global.repository;

import com.nurigil.nurigil.global.domain.entity.Member;
import com.nurigil.nurigil.global.domain.entity.MemberPreference;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberPreferenceRepository extends JpaRepository<MemberPreference, Long> {
    Optional<MemberPreference> findByMember(Member member);
}
