package com.nurigil.nurigil.global.repository;

import com.nurigil.nurigil.global.domain.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
