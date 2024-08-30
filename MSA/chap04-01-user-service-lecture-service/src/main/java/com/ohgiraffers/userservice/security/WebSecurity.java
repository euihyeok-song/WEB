package com.ohgiraffers.userservice.security;

import com.ohgiraffers.userservice.service.UserService;
import jakarta.servlet.Filter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class WebSecurity {

    /* 설명. 사용할 Service 층에서 선언한 Security bean을 등록 */
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private UserService userService;
    private Environment env;
    private JwtUtil jwtUtil;

    @Autowired
    public WebSecurity(BCryptPasswordEncoder bCryptPasswordEncoder, UserService userService,
                       Environment env, JwtUtil jwtUtil) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.userService = userService;
        this.env = env;
        this.jwtUtil = jwtUtil;
    }

    /* 설명. 인가(Authorization)용 메소드(인증 필터 추가)- 우리 회원인지 메소드 사용시 확인 */
    @Bean
    protected SecurityFilterChain configure(HttpSecurity http) throws Exception {

        /* 설명. csrf(빌더 패턴의 setter같은 역할) 비활성화 */
        http.csrf((csrf) -> csrf.disable());

        /* 설명. 로그인 시 추가할 authenticaitonMananger 만들기 */
        AuthenticationManagerBuilder authenticationManagerBuidler =
                http.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuidler.userDetailsService(userService)        // 우리의 Service 계층을 등록해줘야 함
                .passwordEncoder(bCryptPasswordEncoder);            // 사용할 암호화 방식을 넣어줌

        /* 설명. Manager 를 통해서 아래에 다 넣어줌*/
        AuthenticationManager authenticationManager = authenticationManagerBuidler.build();

        /* 설명. 사용가능한 권한이 있는 부분들을 명시해줌 */
        http.authorizeHttpRequests((authz) ->
                authz.requestMatchers(new AntPathRequestMatcher("/health","GET")).permitAll()
                      .requestMatchers(new AntPathRequestMatcher("/welcome","GET")).permitAll()
                      /* 설명. /users/** 경로로 오는 모든 사용자에서 권한 허용 - 화이트리스트(사용 가능 대상) 설정*/
                      .requestMatchers(new AntPathRequestMatcher("/users/**","POST")).permitAll()
                      /* 설명. ROLE_ADMIN 권한을 가진 사용자들에게 GET 요청이 가능하도록 권한 부여의 의미 */
                      .requestMatchers(new AntPathRequestMatcher("/user/**", "GET")).hasRole("ENTERPRISE")
                        /* 설명. 그 외의 요청은 권한 거부 */
                      .anyRequest().authenticated()
        )
                /* 설명. authenticationManager 등록(UserDetails를 상속받는 Service 계층(직접알려줘야함) + BCrypt 암호화): 위에 작성*/
                .authenticationManager(authenticationManager)

                /* 설명. session 방식을 사용하지 않음(JWT Token 방식 사용시 설정할 내용) */
                .sessionManagement((session) -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));


        http.addFilter(getAuthenticationFilter(authenticationManager));
        /* 설명. JwtFilter도 추가해줘야함 - UsernamePasswordAuthentication filter 앞에 붙임 */
        http.addFilterBefore(new JwtFilter(userService, jwtUtil), UsernamePasswordAuthenticationFilter.class);


        return http.build();
    }

    /* 설명. 인증(Authentication)용 메소드(인증 필처 반환)- 우리 회원인지 첫번째로 판별 */
    /* 설명. 같은 폴더에 만든 우리가 만든 필터를 추가함(AuthenticationFilter) */
    private Filter getAuthenticationFilter(AuthenticationManager authenticationManager) {
        return new AuthenticationFilter(authenticationManager, userService, env);
    }



}
