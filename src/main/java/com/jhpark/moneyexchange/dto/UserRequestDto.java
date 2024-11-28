package com.jhpark.moneyexchange.dto;

import com.jhpark.moneyexchange.entity.User;
import lombok.Getter;

@Getter
public class UserRequestDto {
    private String name;
    private String email;

    public User toEntity() {
        return new User(this.name, this.email);
    }
}
