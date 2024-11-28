package com.jhpark.moneyexchange.dto;

import com.jhpark.moneyexchange.entity.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class UserRequestDto {

    @NotBlank
    @Size(min = 2, max = 10, message = "이름은 최소 2자에서 최대 10자 사이로 입력해주세요.")
    private final String name;

    @NotBlank
    @Pattern(regexp = "^[\\w\\.-]+@[\\w\\.-]+\\.[a-zA-Z]{2,}$", message = "올바르지 않은 이메일 형식입니다.")
    private final String email;

    public User toEntity() {
        return new User(this.name, this.email);
    }
}
