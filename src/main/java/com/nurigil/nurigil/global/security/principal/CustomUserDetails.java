package com.nurigil.nurigil.global.security.principal;

import com.nurigil.nurigil.global.domain.entity.Member;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

@Getter
@Setter
public class CustomUserDetails implements UserDetails {

    private final Member member;

    public CustomUserDetails(Member member) {
        this.member = member;
    }

    public Member getMember() {
        return member;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // 권한이 필요 없다면 Collections.emptyList()
        return Collections.emptyList();
    }

    @Override
    public String getPassword() {
        return member.getPassword();  // Member 엔티티의 getPassword()
    }

    @Override
    public String getUsername() {
        return member.getEmail();     // Spring Security는 username으로 식별
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}