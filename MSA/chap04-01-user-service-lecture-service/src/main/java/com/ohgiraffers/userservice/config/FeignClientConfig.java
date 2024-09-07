package com.ohgiraffers.userservice.config;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Configuration
public class FeignClientConfig {

    /* 설명. 하나의 MS(micro service)가 다른 MS로 통신하려고 할때, User가 token을 챙겨가도록 설정해줌 */
    @Bean
    public RequestInterceptor requestInterceptor(){
        return new RequestInterceptor() {
            @Override
            public void apply(RequestTemplate requestTemplate) {

                /* 설명. 현재 요청의 Http Servlet Request를 가져옴 */
                ServletRequestAttributes requestAttributes =
                        (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();

                if(requestAttributes != null){

                    /* 설명. 현재 요청의 Authorization 헤더 추출 (Bearer Token) */
                    String authorization = requestAttributes
                                                .getRequest()            // request 객체 추출
                                                .getHeader(HttpHeaders.AUTHORIZATION);

                    /* 설명. Token을 들고 왔다면  */
                    if(authorization != null){

                        /* 설명. Feign Client 요청에 "Authorization" 헤더 추가 */
                        requestTemplate.header(HttpHeaders.AUTHORIZATION, authorization);
                    }
                }
            }
        };
    }
}
