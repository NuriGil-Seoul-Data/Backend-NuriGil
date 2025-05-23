package com.nurigil.nurigil.global.domain.entity.type;

public enum Role {
    USER("ROLE_USER"),
    ADMIN("ROLE_ADMIN");

    private final String key;

    Role(String key){
        this.key = key;
    }

    public String getKey() {
        return key;
    }
}
