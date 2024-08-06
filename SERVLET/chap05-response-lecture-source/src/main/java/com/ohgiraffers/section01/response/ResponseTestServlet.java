package com.ohgiraffers.section01.response;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/response")
public class ResponseTestServlet extends HttpServlet {
    /* 설명. resp는 어디서 정보가 오는지에 대한 정보를 가지고 있다. (resp를 이용해서 별도의 Stream(돌아가는 통로)을 만들어서 보냄) */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        StringBuilder responseBuilder = new StringBuilder();
        responseBuilder.append("<!DOCTYPE html>\n")
                .append("<html>\n")
                .append("<head>\n")
                .append("</head>\n")
                .append("<body>\n")
                .append("<h1>한글servlet response</h1>\n")
                .append("</body>\n")
                .append("</html>\n");

        /* 설명. Tomcat 10버전 이후 MIME 생략 가능 - 기존 정석 1 */
//        resp.setContentType("text/plain");            // 지금 내보내는 문자열이 단순한 텍스트라는 MIME

        /* 설명. 1. Tomcat 버전 상관 없이 내보내는 데이터의 종류와 인코딩을 명시할 시 */
        resp.setContentType("text/html");             // MIME는 생략해도 되지만, 생략했을시 한글은 깨진다. 생략하지 않으면 한글 안깨짐
        resp.setCharacterEncoding("UTF-8");

        /* 설명. 1-1. 기존 정석 1의 한줄 코드=>  알고있자~!!*/
        resp.setContentType("text/html; charset=UTF-8");

        /* 설명. 2. tomcat 10버전 부터는 최소 MIME 타입이라도 알려주자. - 10버전 부터는 아래처럼만 해줘도 위의 1,2와 같이 처리*/
        resp.setContentType("text/html");

        /* 설명. 왔던 정보를 가지고 PrintWriter 타입의 스트림을 만들어서 내보냄 - 개행처리, StringBuilder도 처리가능한 Scanner 개념*/
        PrintWriter out = resp.getWriter();
        out.print(responseBuilder);                 // PrintWriter의 print는 Writer의 write()와 동일 개냠
        out.flush();                                // PrintWriter를 눌러서 들어가보면 Buffered 보조 스트림을 사용하므로 flush 필요
                                                    // 왠만하면 출력 시에는 flush를 명시하자.
        out.close();
    }
}
