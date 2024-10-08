package com.ohgiraffers.userservice.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ohgiraffers.userservice.service.UserService;
import com.ohgiraffers.userservice.vo.RequestLoginVO;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
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
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
/*
 * 필기. JWT(Json Web Token)의 구조
 *
 * 필기.
 *  1. 헤더(Header)
 *    - typ: 토큰의 타입 지정(JWT)
 *    - alg: 해싱 알고리즘으로 Verify Signature에서 사용 됨
 * 필기.
 *  2. 내용 또는 정보(Payload)
 *    - 토큰에 담을 정보가 들어 있음
 *    - 담는 정보의 한 조각을 클레임(claim - name과 value의 쌍으로 구성)이라 부름
 *       a. 등록된 클레임(registered claim)
 *          : 토큰에 대한 정보가 담김
 *            (iss: 토큰 발급자(issuer)
 *             sub: 토큰 제목(subject)
 *             aud: 토큰 대상자(audience)
 *             exp: 토큰의 만료 시간(expiration)
 *             nbf: 토큰 활성화(발급) 날짜(not before)
 *             iat: 토큰 활성화(발급) 시간(issued at))
 * 필기.
 *       b. 공개 클레임(public claim)
 *          : 사용자 정의 클레임으로 공개용 정보를 위해 사용(충돌 방지를 위해 URI로 구성)
 * 필기.
 *       c. 비공개 클레임(private claim)
 *         : 사용자 정의 클레임으로 서버(JWT 발급자)와 클라이언트 사이에 임의로 지정한 정보를 저장
 *            (충돌 발생 우려가 있어 조심해서 사용할 것)
 * 필기.
 *  3. 서명(Verify Signature)
 *    - Header 인코딩 값과 Payload 인코딩 값을 합쳐서 비밀 키로 해쉬(헤더의 해싱 알고리즘으로)하여 생성
 */

@Slf4j
/* 설명. security filter에 추가할 우리가 만든 필터 */
public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private UserService userSerivce;
    private Environment env;

    public AuthenticationFilter(AuthenticationManager authenticationManager, UserService userSerivce, Environment env) {
        super(authenticationManager);
        this.userSerivce = userSerivce;
        this.env = env;
    }

    /* 설명. 로그인 시도 시 동작하는 기능(POST /login 요청 시) */
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request
            , HttpServletResponse response) throws AuthenticationException {

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
                                            Authentication authResult) throws IOException, ServletException {
        // authResult => principal 객체   ===> 가져오기 성공하여 저장된 사용자의 정보
        // UserServiceImpl의 loadUserByName메소드 실행시 UsernamePasswordAuthenticationToken에서 받은 값이 들어온다.

        /* 설명. 토큰에 들어갈 인증된 정보를 의미함 */
        log.info("로그인 성공하고 security가 관리하는 pincipal객체(authResult): {}", authResult);

        /* 설명. 로그인 이후 관리되고 있는 Authentication 객체를 활용해 JWT Token 만들기 - secret kwy + 암호화 */
        log.info("시크릿 키: " + env.getProperty("token.secret"));

        /* 설명. 토큰의 payload에 어떤 값을 담고 싶은지에 따라 고민해서 자료를 수집한다.(id, 가진 권한들, 만료시간) */
        String userName = ((User)authResult.getPrincipal()).getUsername();  // id의 개념 (우리는 email로 함)
        log.info("인증된 회원의 id: " + userName);


        /* 설명. UserServiceImpl의 loadUserByUsername에 선언한 grantedAuthorites에서 권한을 가져옴 (타입을 맞춰줘야함)
         *       => 권한들을 꺼내 List<String>로 변환 */
        List<String> roles = authResult.getAuthorities().stream()
                .map(role -> role.getAuthority())
                        .collect(Collectors.toList());


        /* 설명. 재료들로 토큰 만들기 (JWT Token 라이브러리 추가(3가지)하기) */
        Claims claims = Jwts.claims().setSubject(userName);      // id는 setSubject로 들어감
        claims.put("auth",roles);        // auth는 비공개 클래임 - 사용자 지정

        String token = Jwts.builder()
                .setClaims(claims)
                .setExpiration(new Date(System.currentTimeMillis()     // 만료시간은 setExpiration으로 들어감
                                + Long.parseLong(env.getProperty("token.expiration_time"))))
                .signWith(SignatureAlgorithm.HS512, env.getProperty("token.secret")) //secret키는 signWith로 들어감
                .compact();

        response.addHeader("token", token);         // reponseHeader에 들어감

    }

}
