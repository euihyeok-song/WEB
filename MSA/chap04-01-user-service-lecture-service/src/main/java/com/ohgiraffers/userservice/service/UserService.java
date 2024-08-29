package com.ohgiraffers.userservice.service;

import com.ohgiraffers.userservice.dto.UserDTO;
import org.springframework.security.core.userdetails.UserDetailsService;

/* 설명. 계층간의 느슨함을 보장하기 위해 (PSA) interface를 선언한다. => portable */
/* 설명. Security에 사용하기 위해서 UserDetails 타입에 호환해주기 위해서 설정 */
public interface UserService extends UserDetailsService {

    void registUser(UserDTO userDTO);
}
