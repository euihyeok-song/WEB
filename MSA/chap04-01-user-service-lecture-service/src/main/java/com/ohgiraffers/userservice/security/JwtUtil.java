package com.ohgiraffers.userservice.security;

import com.ohgiraffers.userservice.service.UserService;
import com.sun.nio.sctp.IllegalReceiveException;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

/* 설명. JwtFilter에서 해야할 일을 분할받아서 함*/
@Slf4j
@Component
public class JwtUtil {

    private final Key key;
    private UserService userService;

    public JwtUtil(@Value("${token.secret}") String secretKey, UserService userService) {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        this.key = Keys.hmacShaKeyFor(keyBytes);
        this.userService = userService;
    }

    /* 설명. Token을 검증(Bearer 토큰이 넘어왔고, 우리 사이트의 secret key로 만들어 졌는가, 만료되었는지와 내용이 비어있진 않은지 */
    public boolean validateToken(String token) {

        /* 설명. 1. token에 CliamsJws를 붙임 2. key로 검증 */
        try{
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
        } catch (io.jsonwebtoken.security.SecurityException | MalformedJwtException e){
            log.info("Invalid JWT Token {}",e);
        } catch (ExpiredJwtException e){
            log.info("Expired JWT Token {}", e);
        } catch (UnsupportedJwtException e){
            log.info("Unsupported JWT Token {}", e);
        } catch (IllegalReceiveException e){
            log.info("JWT Token claioms empty {}", e);
        }

        return true;
    }

    /* 설명. 넘어온 AccessToken으로 인증 객체 추출 */
    public Authentication getAuthentication(String token) {

        /* 설명. 토큰을 들고 왔던 들고 오지 않았던(로그인 시) 동일하게 security가 관리할 UserDetails 타입 */
        UserDetails userDetails = userService.loadUserByUsername(this.getUserId(token)); // this.~ 아래를 통해 email들어감

        /* 설명. 토큰에서 claiom들 추출 */
        Claims claims = parseClaims(token);
        log.info("넘어온 AccessToken Claims 확인 : {}", claims);

        Collection<? extends GrantedAuthority> authorities = null;

        /* 설명. 권한이 없을 경우 예외 발생 */
        if(claims.get("auth") == null){
            throw new RuntimeException("권한 정보가 없는 토큰입니다.");
        } else {
            /* 설명. 클레임에서 Token에 담긴 권한 정보들 가져오기 - 가공 처리 하여 String 배열안에 넣음(ex."ROLE_ADMIN")
                    => UserServiceImpl의 List<GrantedAuthority> grantedAuthorities ~~ 부분과 같은 기능 */
            authorities = Arrays.stream(claims.get("auth").toString()
                                    .replace("[", "")
                                    .replace("]", "")
                                    .split(","))
                            .map(role -> new SimpleGrantedAuthority(role))
                            .collect(Collectors.toList());
        }

        /* 설명. 토큰 인자: 1. 관리된 객체 전체 + 2. Claims에서 추출한 권한을 넣어줌 ( credential은 추가하려면 하고 아니면 "" ) */
        return new UsernamePasswordAuthenticationToken(userDetails, "", authorities);
    }

    /* 설명. Token에서 Claims 추출 - Claims에서 Token을 추출하여 그 안에서 권한에 해당하는 것을 뽑아옴 */
    public Claims parseClaims(String token){
        return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody();
    }

    /* 설명. Token에서 사용자의 id(subject 클레임) 추출 */
    public String getUserId(String token){
        return parseClaims(token).getSubject();         // getSubject로 넣었던 UserId가 도출됨
    }

}
