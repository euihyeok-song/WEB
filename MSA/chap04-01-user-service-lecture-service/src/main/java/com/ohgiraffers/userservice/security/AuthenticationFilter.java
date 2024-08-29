package com.ohgiraffers.userservice.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ohgiraffers.userservice.service.UserService;
import com.ohgiraffers.userservice.vo.RequestLoginVO;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;
import java.util.ArrayList;

@Slf4j
/* 설명. security filter에 추가할 우리가 만든 필터 */
public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private UserService userService;
    private Environment env;

    /* 설명. 우리가 authenticationManager를 넣어주고 super로 넘겨줌*/
    public AuthenticationFilter(AuthenticationManager authenticationManager, UserService userService, Environment env) {
        super(authenticationManager);
        this.userService = userService;
        this.env = env;
    }

    /* 설명. 로그인 시도 시 동작하는 기능(POST /login 요청 시) */
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException {

        /* 설명. json으로 들어오는 값들을 바로 뽑아주려 했지만, controller가 아니기 떄문에 @RequestBody를 사용할 수 없다.*/
        /* 설명. request body에 담긴 내용을 우리가 만든 RequestLoginVO 타입에 담는다.(일종의 @RequestBOdy의 개념) */
        /* 설명. 우리가 반환한 Token으로 Token을 재정의 하는 과정 */
        try {
            RequestLoginVO creds = new ObjectMapper().readValue(request.getInputStream(), RequestLoginVO.class);

            /* 설명. Notion 그림의 AuthenticationFilter -> UsernamePasswordAuthenticaion Token으로 Token을 주는 개념*/
            return getAuthenticationManager().authenticate(
                    /* 설명. new ArrayList<>()는 Token이 담기는 곳이며, 권한에 해당하는 곳이다.*/
                    new UsernamePasswordAuthenticationToken(creds.getEmail(), creds.getPwd(), new ArrayList<>())
            );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /* 설명. 로그인 성공 시 JWT 토큰 생성하는 기능 */

    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response,
                                            FilterChain chain,
                                            Authentication authResult)  // authResult => principle 객체(사용자의 정보)
        // UserServiceImpl의 loadUserByName메소드 실행시 UsernamePasswordAuthenticationToken에서 받은 값이 들어온다.
            throws IOException, ServletException {

        /* 설명. 토큰에 들어갈 인증된 정보를 의미함 */
        log.info("로그인 성공하고 security가 관리하는 pincipal객체(authResult): {}", authResult);

    }
}
