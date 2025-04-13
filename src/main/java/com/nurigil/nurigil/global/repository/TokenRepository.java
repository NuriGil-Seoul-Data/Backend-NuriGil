package com.nurigil.nurigil.global.repository;

import com.nurigil.nurigil.global.security.Token.Token;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface TokenRepository extends CrudRepository<Token, String> {
    Optional<Token> findByAccessToken(String accessToken);
    Optional<Token> findByProviderId(String providerId);
}
