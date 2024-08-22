package com.ohgiraffers.section03.properties.subsection02.i18n;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

@Configuration
public class ContextConfiguration {

    /* 설명. 다국어 지원을 위해서 resource 파일에 만든 속성을 불러옴 - setting해둔 속성을 읽어서 그 언어를 적용시킴*/
    @Bean
    public ReloadableResourceBundleMessageSource messageSource() {

        /* 설명. 접속하는 세션의 로케일(Locale)에 따라 자동 재로딩하는 용도의 MessageSource 구현체 */
        ReloadableResourceBundleMessageSource  messageSource
                = new ReloadableResourceBundleMessageSource();

        /* 설명. 다국어 메시지를 읽어올 properties 파일의 파일 이름 설정 */
        messageSource.setBasename("section03/properties/subsection02/i18n/message");

        /* 설명. 불러온 메시지를 해당 시간 동안 케싱(초 단위)*/
        messageSource.setCacheSeconds(10);

        /* 설명. 기본 인코딩 셋 설정 */
        messageSource.setDefaultEncoding("UTF-8");

        return messageSource;
    }
}
