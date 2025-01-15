package com.ohgiraffers.bootproject.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:5173")

                // k8s 적용
                // Ingress 적용 이전 프론트 워커노드 포트에 대한 CORS 처리
//                .allowedOrigins("http://localhost:30000")

                // Ingress 적용 이후 CORS 불필요로 인한 경로 제거
                // 외부에서 들어와서 CORS 처리가 필요할 수도 있으니 @EnableWebMvc를 주석처리 해도 좋으나 아래와 같은 방식 채택
                // .allowedOrigins()
                .allowedMethods("GET", "POST", "PUT" , "DELETE");
    }
}