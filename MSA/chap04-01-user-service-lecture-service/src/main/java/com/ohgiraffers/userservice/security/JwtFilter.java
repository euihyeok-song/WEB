package com.ohgiraffers.userservice.security;

import com.ohgiraffers.userservice.service.UserService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;


@Slf4j
/* 설명. 노션 그림을 참고하면 UsernamePassword 필터 앞에 끼워서 넣음 */
/* 설명. OncePerRequestFilter를 상속받아 doFilterInternal을 오버라이딩 한다.(한번만 실행되는 필터) */
public class JwtFilter extends OncePerRequestFilter {

    private final UserService userService;
    private final JwtUtil jwtUtil;

    public JwtFilter(UserService userService, JwtUtil jwtUtil) {
        this.userService = userService;
        this.jwtUtil = jwtUtil;
    }

    /* 설명. 들고 온(Request Header) 토큰이 유효한지 판별 및 인증(Authentication 객체로 관리 -> Spring Security가 계속 인지하고 있음) */
    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        log.info("UsernamePasswordAuthenticationFilter보다 먼저 동작하는 필터");

        String authorizationHeader = request.getHeader("Authorization");
        log.info("Authorization header: {}", authorizationHeader);

        /* 설명. JWT 토큰이 Request Header에 있는 경우(로그인 이후 요청일 경울) - Token은 앞에 Bearer라는 접두사가 붙음 */
        if(authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            String token = authorizationHeader.substring(7);    // "Bearer "를 제외한 뒤에 토큰 부분만 추출
            log.info("토큰 값: " + token);
            if(jwtUtil.validateToken(token)) {     // validation 체크를 jwtUtil에게 넘긴다.
                /* 설명. 유효한 토큰이므로 토큰에서 Authentication을 추출함 */
                Authentication authentication = jwtUtil.getAuthentication(token);
                log.info("JwtFilter를 통과한 유효한 토큰을 통해 security가 관리할 principal 객체ㅣ {}", authentication);
                /* 설명. 직접 SecurityContextHolder에 넣었음으로 인증 완료 되었음을 의미 => 이후 다음 필터들을 건너뜀*/
                SecurityContextHolder.getContext().setAuthentication(authentication);   // 인증이 완료되었고 이후 필터 건너뜀
            }
        }

        /* 설명. 위의 if문으로 인증된 Authentication 객체가 principal 객체로 관리되지 않는다면 다음 필터 실행*/
        filterChain.doFilter(request, response);    // 실행 될 다음 필터는 UsernamePasswordAuthentication(로그인용 필터)
    }
}
