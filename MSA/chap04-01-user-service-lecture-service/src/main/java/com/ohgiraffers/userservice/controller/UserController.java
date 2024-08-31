package com.ohgiraffers.userservice.controller;

import com.ohgiraffers.userservice.dto.UserDTO;
import com.ohgiraffers.userservice.service.UserService;
import com.ohgiraffers.userservice.vo.Hello;
import com.ohgiraffers.userservice.vo.RequestResistUserVO;
import com.ohgiraffers.userservice.vo.ResponseFindUserVO;
import com.ohgiraffers.userservice.vo.ResponseRegistUserVO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    private Environment env;
    private Hello hello;
    private ModelMapper modelMapper;
    private UserService userService;

    @Autowired
    public UserController(Environment env,
                          Hello hello,
                          ModelMapper modelMapper,
                          UserService userService) {
        this.env = env;
        this.hello = hello;
        this.modelMapper = modelMapper;
        this.userService = userService;
    }

    @GetMapping("/health")
    public String status(){
        /* 설명. 해당 마이크로 서비스가 application.yml에 설정 값이 제대로 들어 있는지 확인(feat.@Value)
         *      => 설정서버를 따로 뺴서 불러올떄 사용
         * */
        return "I'm Working in User Service " + env.getProperty("local.server.port");
    }


    @GetMapping("/welcome")
    public String welcome(){

        return hello.getMessage();
    }

    /* 설명. 로그인 기능(feat. security 모듈 활용) 전에 회원가입 기능 만들기 */
    @PostMapping("users")
    public ResponseEntity<ResponseRegistUserVO> registUser(@RequestBody RequestResistUserVO newUser) {

        /* 설명. VO <-> DTO <-> Entity => 서비스에 전달하게 해주는 mapper를 사용 (선언 필요)*/
        UserDTO userDTO = modelMapper.map(newUser, UserDTO.class);

        /* 설명. UserServiceImpl의 setUserId를 하는 순간 가르키는 객체는 동일하지만 값이 추가됨 => (반환값이 없어도 값 수정 가능)
        *       => call by reference
        * */
        userService.registUser(userDTO);

//        return ResponseEntity.status(HttpStatus.OK).build();

        /* 설명. UserDTO를 ResponseRegistUserVO로 만들어서 response body에 넣어서 전송*/
        /* 설명. modelMapper는 A라는 클래스에 B클래스의 값을 담아주는 메소드 */
        ResponseRegistUserVO responseUser = modelMapper.map(userDTO, ResponseRegistUserVO.class);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseUser);
    }

    @GetMapping("/users/{memNo}")
    public ResponseEntity<ResponseFindUserVO> getUser(@PathVariable("memNo") String memNo) {
        UserDTO userDTO = userService.getUserByUserId(memNo);

        ResponseFindUserVO findUser = modelMapper.map(userDTO, ResponseFindUserVO.class);

        return ResponseEntity.status(HttpStatus.OK)
                .body(findUser);
    }

}
