package com.ohgiraffers.section01.forward;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/forward")
public class ReceiveInformationServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        /* 설명. getParameter()는 client에서 온 request를 처리할 때 사용하며, String을 반환
        *       getAttribute()는 server끼리 request를 처리할 때 사용하며, Object를 반환  */
        String userId = req.getParameter("userId");
        String password = req.getParameter("password");

        System.out.println("userId = " + userId);
        System.out.println("password = " + password);

        /* 설명.
        *   이 부분에서는 id와 pwd에 해당하는 user의 정보를 select하고 오는 비지니스 로직(BL)이
        *   수행되어야 한다.(이름을 조회해서 이름을 알게 됨)
        *   우리는 제대로 조회가 되었다는 가정하에 'XXX님 환영합니다."와 같은 메시지를 출력하는
        *   화면을 만들어 응답해보자. - 하나의 servlet이 너무 많은 기능을 하게 되면 관리와 traffic에서 불리함으로
        *   다른 servlet을 만들어서 넘겨줘서(request를 넘겨줌-attribute사용) 진행
        * */

        /* ex. id와 pw로 인증 후 DB에서 해당 회원 이름 받아옴 */
        req.setAttribute("userName","홍길동");

        /* 설명. 다른 servlet에게 역할을 위임하는 개념 - (ex. 출력잘하는 친구(print)에게 넘겨주는 개념)*/
        RequestDispatcher dispatcher = req.getRequestDispatcher("print");
        /* 설명. forward()를 통해서 request와 response를 넘기고 유지하지 못하여 사라진다.*/
        dispatcher.forward(req,resp);

    }
}
