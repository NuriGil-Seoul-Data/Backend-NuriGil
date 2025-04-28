package com.nurigil.nurigil.global.security.Token;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;


@RedisHash(value = "token", timeToLive = 60 * 60 * 24 *7)
@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
@Builder
public class Token {

    @Id
    private String provideId;

    private String accessToken;
    private String refreshToken;

    // getter & setter
    public void updateAccessToken(String accessToken){
        this.accessToken = accessToken;
    }
    public void updateRefreshToken(String refreshToken){
        this.refreshToken = refreshToken;
    }
}
