package com.ohgiraffers.section03.properties.subsection02.i18n;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Locale;

public class Application {
    public static void main(String[] args) {

        /* 설명.
        *   i18n 소프트웨어의 국제화(Internationalization, I와 n사이 18글자)에서 처리해야 할 작업이 많이 있다.
        *   1. 언어, 지역별 번역
        *   2. OS/Platform 인코딩
        *   3. 문자열 치환 방법
        *   4. 국제화 UI(문자열 크기 변화, 폰트, 아이콘 등)
        *   5. 쓰기 방향의 차이
        *   6. 숫자, 공백, 화폐, 날짜, 주소, 측정 단위 등
        *   7. 타임존, 썸머타임 등 시각
        *   8. 문자열 정렬 방법
        *
        * 설명.
        *  우리가 다뤄본 예제는 그 중에서 1번에 해당하는 다국어 메시지 처리에 있어 살펴본 것이다.
        * */

        ApplicationContext context
                = new AnnotationConfigApplicationContext(ContextConfiguration.class);

        /* 설명. Locale.KOREA로 설정해주면 자동으로 resources의 message_ko_KR을 찾아줌 */
        String error404MessageKR = context.getMessage("error.404",null, Locale.KOREA);
        /* 설명. args부분에 {0}, {1} 부분을 넣을 수 있다. */
        String error500MessageKR = context.getMessage("error.500",new Object[]{"여러분", new java.util.Date()},
                Locale.KOREA);

        System.out.println("I18N 404 에러 메세지(한국) = " + error404MessageKR);
        System.out.println("I18N 500 에러 메세지(한국) = " + error500MessageKR);

        String error404MessageUS = context.getMessage("error.404",null, Locale.US);
        String error500MessageUS = context.getMessage("error.500",new Object[]{"You", new java.util.Date()},
                Locale.US);

        System.out.println("I18N 404 에러 메세지(미국) = " + error404MessageUS);
        System.out.println("I18N 500 에러 메세지(미국) = " + error500MessageUS);
    }
}
