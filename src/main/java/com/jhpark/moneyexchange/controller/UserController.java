package com.jhpark.moneyexchange.controller;

import com.jhpark.moneyexchange.dto.user.UserRequestDto;
import com.jhpark.moneyexchange.dto.user.UserResponseDto;
import com.jhpark.moneyexchange.exception.CustomException;
import com.jhpark.moneyexchange.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    /**
     * 유저 생성
     * @param dto (유저 생성에 필요한 요청 정보 전달)
     * @return
     */
    @PostMapping
    public ResponseEntity<UserResponseDto> createUser(@RequestBody @Valid UserRequestDto dto) {
        return new ResponseEntity<>(userService.save(dto), HttpStatus.CREATED);
    }

    /**
     * 유저 전부 조회
     * @return (List<UserResponseDto>)
     */
    @GetMapping
    public ResponseEntity<List<UserResponseDto>> findUsers() {
        return ResponseEntity.ok().body(userService.findAll());
    }

    /**
     * 특정 유저 조회
     * @param id (유저 ID)
     * @return (UserResponseDto)
     * @throws CustomException (유저가 없을 시 예외 발생)
     */
    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDto> findUser(@PathVariable Long id) throws CustomException {
        return ResponseEntity.ok().body(userService.findById(id));
    }

    /**
     * 유저 삭제 (유저 삭제 시 관련된 환전 요청 정보도 같이 삭제됨)
     * @param id (유저 ID)
     * @return String
     * @throws CustomException (유저가 없을 시 예외 발생)
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) throws CustomException{
        userService.deleteUserById(id);
        return ResponseEntity.ok().body("정상적으로 삭제되었습니다.");
    }
}
