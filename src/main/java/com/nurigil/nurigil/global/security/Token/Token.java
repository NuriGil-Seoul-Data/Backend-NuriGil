package com.nurigil.nurigil.global.security.Token;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.redis.core.RedisHash;


@RedisHash(value = "token", timeToLive = 60 * 60 * 24 *7)
@NoArgsConstructor
@Getter
public class Token {

    @Id
    String accessToken;

    String refreshToken;
    String email;

    // getter & setter

    public void updateAccessToken(String accessToken){
        this.accessToken = accessToken;
    }

    public void updateRefreshToken(String refreshToken){
        this.refreshToken = refreshToken;
    }
}
