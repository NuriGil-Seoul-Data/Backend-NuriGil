    package com.nurigil.nurigil.global.security.principal;

    import com.nurigil.nurigil.global.domain.entity.Member;
    import org.springframework.security.core.GrantedAuthority;
    import org.springframework.security.core.authority.SimpleGrantedAuthority;
    import org.springframework.security.core.userdetails.UserDetails;
    import org.springframework.security.oauth2.core.user.OAuth2User;

    import java.util.Collection;
    import java.util.Collections;
    import java.util.Map;

    public record PrincipalDetails(
            Member member,
            Map<String, Object> attributes,
            String attributeKey) implements OAuth2User, UserDetails {

        @Override
        public String getName() {
            Object value = attributes.get(attributeKey);
            return (value != null) ? value.toString() : member.getEmail();
        }

        @Override
        public Map<String, Object> getAttributes() {
            return attributes;
        }

        @Override
        public Collection<? extends GrantedAuthority> getAuthorities() {
            return Collections.singletonList(
                    new SimpleGrantedAuthority(member.getRole().getKey()));
        }

        @Override
        public String getPassword() {
            return null;
        }

        @Override
        public String getUsername() {
            return member.getName();
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
