<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
</head>
<body>
    <h1 align="center">forward</h1>
<%--        레이아웃을 만들경우 표를 잘 사용함 --%>
    <form action="forward" method="post">
<%--        table>tr*2  => 2행짜리 table을 만들어좀--%>
        <table>
            <tr>
<%--         td*3  한행의 3개의 컬럼을 만들어줌--%>
<%--         rowspan은 2개의 행을 합하는 개념 (excel의 셀합치기 개념)      --%>
                <td>어아다: </td>
                <td><input type="text" name="userId"></td>
                <td rowspan="2"><button type="submit" style="height:50px">로그인</button></td>
            </tr>
<%--         td*2  한행의 2개의 컬럼을 만들어줌--%>
<%--         input type="password"는 입력시 ****처럼 보이게 해줌--%>
                <td>비밀번호: </td>
                <td><input type="password" name="password"></td>
            <tr></tr>
        </table>
    </form>
</body>
</html>