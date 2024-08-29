package com.ohgiraffers.userservice.service;

import com.ohgiraffers.userservice.aggregate.UserEntity;
import com.ohgiraffers.userservice.dto.UserDTO;
import com.ohgiraffers.userservice.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/* 설명. UserService interface의 메소드를 구현하는 클래스 */
@Service
@Slf4j
public class UserServiceImpl implements UserService{

    /* 설명. 도메인 계층에 있는 repo */
    UserRepository userRepository;
    ModelMapper modelMapper;

    /* 설명. security 모듈 추가 후 암호화를 위해 BCryptPasswordEncoder bean 주입*/
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository,
                           ModelMapper modelMapper,
                           BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    @Transactional
    public void registUser(UserDTO userDTO){

        /* 설명. 회원 가입할 인원에게 고유 아이디 생성하여 UserDTO의 UserId에 저장 */
        userDTO.setUserId(UUID.randomUUID().toString());

        /* 설명. 경우에 따라 ModelMapper는 자의적인 판단으로 필드끼리 매핑하는 경우가 있어 정확히 일치되게 매칭할려면 추가할 속성 */
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        /* 설명. Entity manager을 통해 영속성 컨텍스트에 넣는 과정 */
        UserEntity userEntity = modelMapper.map(userDTO, UserEntity.class);
        log.info("Service 계층에서 DTO -> Entity: {}", userEntity);

        /* 설명. BCryptPasswordEncoder 주입 후 암호화(평문 -> 다이제스트) */
        userEntity.setEncryptedPwd(bCryptPasswordEncoder.encode(userDTO.getPwd()));

        userRepository.save(userEntity);
    }

    /* 설명. Spring Security에게 UserDetailsService를 인지시켜, 로그인시 security가 자동으로 호출하는 메소드
            => (메소드를 바로 인지는 불가하니, 객체 타입으로 넘겨준다) - WebSecurity 참고 */
    /* 설명. AuthenticationFilter의 successfulAuthentication로부터 값이 넘어옴 (email)*/
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        /* 설명. 넘어온 email이 사용자가 입력한 id로써 email로 회원을 조회하는 쿼리 메소드 작성 */
        UserEntity loginUser = userRepository.findByEmail(email);

        if(loginUser == null){
            /* 설명. Spring Security에서 제공하는 예외 처리 메소드 */
            throw new UsernameNotFoundException(email + "이메일 아이디의 유저는 존재하지 않습니다.");
        }

        /* 설명. 사용자의 권한들을 가져왔다는 가정 */
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_ENTERPRISE"));


        /* 설명. 기존 Spring Security에서 제공되는 타입 User를 사용 - password, username, authorities는 무조건 값이 들어가야함 */
        /* 설명. 내가 사용하고 싶은 User Class를 Spring Security에서 제공하는 틀과 동일하게 만들어서 첨부하면 Spring Security가 관리 */
        /* 설명. 이 결과값이 UserServiceImpl의 successfulAuthentication의 authResult로 들어감 */
        return new User(loginUser.getEmail(), loginUser.getEncryptedPwd(),
                true, true, true, true,
                grantedAuthorities);
    }
}
