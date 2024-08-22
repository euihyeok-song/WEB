<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
</head>
<body>
    <h1 align="center">Cookie handling</h1>

    <form action="cookie" method="post">
        <table style="margin:auto">    <!-- 테이블의 각 셀마다 가운데 정렬(margin:auto) -->
            <%--            <table style="margin:auto; border: 1px solid black">  박스 부근에 검은색 줄로 박스침  --%>
            <tr>
                <td>firstName: </td>
                <td><input type="text" name="firstName"></td>
            </tr>
            <tr>
                <td>lastName: </td>
                <td><input type="text" name="lastName"></td>
            </tr>
            <tr>
                <td colspan="2" align="center">
                    <button type="submit">전송</button>
                </td>
            </tr>
        </table>
    </form>
</body>
</html>