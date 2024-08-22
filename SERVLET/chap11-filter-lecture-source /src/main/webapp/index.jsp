<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
</head>
<body>
    <h1 align="center">Filter</h1>
    <h3>필터의 라이프 사이클</h3>
    <ul>
        <!-- 도메인 별로 filter를 적용하고 싶으면 아래와 같이 계층으로 구현 -->
        <li><a href="first/filter">Filter 사용하기</a></li> <!-- first는 도메인 filter는 기능별 servlet의 개념 -->
    </ul>

    <hr>

    <h3>필터의 활용</h3>
    <form action="member/regist" method="post">
        <!-- label for ~ 와 input id= ~ 부분처럼 선언하면 web에서 아이디 부분을 클릭하면 자동으로 박스내부에 커서 focus(깜빡임)가 일어남 -->
        <label for="test">아이디: </label>
        <input id="test" type="text" name="userId">
        <br>
        <!-- 암호는 hash암호화(단방향 암호화)가 무조건적으로 이루어져야 한다. -->
        <!-- 중요!: 사용자가 입력한 평문(plain text)를 다이제스트(digest)로 바꾸기 위한 hash암호화를 filter를 사용해서 진행 -->
        <!-- 중요!: build.gradle에 단방형 암호화를 위한 bcrypt 관련 라이브러리 추가 -->
        <!-- 중요!: 2개의 라이브러리 추가 -->
        <label>비밀번호: </label>
        <input type="password" name="password">
        <br>
        <label>이름: </label>
        <input type="text" name="name">
        <br>
        <button type="submit">가입하기</button>
    </form>
</body>
</html>