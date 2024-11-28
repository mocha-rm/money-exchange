package com.jhpark.moneyexchange.service;

import com.jhpark.moneyexchange.dto.UserRequestDto;
import com.jhpark.moneyexchange.dto.UserResponseDto;
import com.jhpark.moneyexchange.entity.User;
import com.jhpark.moneyexchange.exception.CustomException;
import com.jhpark.moneyexchange.exception.CustomExceptionCode;
import com.jhpark.moneyexchange.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    @Transactional
    public UserResponseDto save(UserRequestDto dto) {
        User savedUser = userRepository.save(dto.toEntity());
        return new UserResponseDto(savedUser);
    }

    public UserResponseDto findById(Long id) throws CustomException {
        return new UserResponseDto(findUserById(id));
    }

    public User findUserById(Long id) throws CustomException {
        return userRepository.findById(id).orElseThrow(() -> new CustomException(CustomExceptionCode.USER_NOT_FOUND));
    }

    public List<UserResponseDto> findAll() {
        return userRepository.findAll().stream().map(UserResponseDto::toDto).toList();
    }

    @Transactional
    public void deleteUserById(Long id) throws CustomException {
        this.findUserById(id);
        userRepository.deleteById(id);
    }
}
