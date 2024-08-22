package com.ohgiraffers.chap06;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StopWatch;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/* 설명. Interceptor 추가 및 (static 리소스 호출 경로 등록 설정-addResourceHandler함수)
*       Webserver가 정적 파일을 만들어 static 리소스를 호출 (js,css,upload등)
*       was파일을 통해서 동적페이지를 만들면서 Tomcat을 작동시킴
*       static에 저장된 파일은 누구나 가져와서 사용가능 ex)localhost:8080/css/test.css하면 코드가 보임*/
@Configuration
public class WebConfiguration implements WebMvcConfigurer {

    private StopwatchInterceptor stopwatchInterceptor;

    /* 설명. 생성자 주입 방식 */
    @Autowired
    public WebConfiguration(StopwatchInterceptor stopwatchInterceptor) {
        this.stopwatchInterceptor = stopwatchInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(stopwatchInterceptor);
    }

    /* 설명. 내적으로 was서버에서 핸들러를 통한 행위가 아닌 Web서버에게 넘겨줌으로써 중복으로 호출 될 경우 시간을 줄일 수 있다. */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/css/**")
                .addResourceLocations("classpath:/static/css")
                .setCachePeriod(50);
    }
}
