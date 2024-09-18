<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
</head>
<body>
    <h1 align="center">Response</h1>

<%-- 아래를 emmet 문법(front의 html문을 빠르게 완성하기 위한 문법)   --%>
<%-- ul>(li>a)*3 이후 tab하면 바로 3개 완성 (<% 는 코드에도 안나오는 주석)   --%>
<!-- ul>(li>a)*3 이후 tab하면 바로 3개 완성 (<! 는 코드에 나오는 주석)  -->

    <!-- ul 태그는 unordered list -->
    <ul>
        <li><a href="response">응답 확인하기</a></li>
        <li><a href="header">응답 헤더 확인하기</a></li>
        <li><a href="status">응답 상태 코드 확인하기</a></li>
    </ul>

</body>
</html>