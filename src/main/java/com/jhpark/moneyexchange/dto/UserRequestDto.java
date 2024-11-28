package com.jhpark.moneyexchange.dto;

import com.jhpark.moneyexchange.entity.User;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class UserRequestDto {
    private final String name;
    private final String email;

    public User toEntity() {
        return new User(this.name, this.email);
    }
}
