<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=utf-8" language="java" %>
<div>
    <h1>비밀번호 변경</h1>
    <div class="msg">${msg}</div>
    <form action="/user/mypage/password" method="post" id="pwFrm">
        <div><label><input name="currentupw" type="password" placeholder="현재 비밀번호"></label></div>
        <div><label><input name="upw" type="password" placeholder="변경 비밀번호"></label></div>
        <div><label><input id="confirmupw" type="password" placeholder="비밀번호 확인"></label></div>
        <div><input type="submit" value="변경"></div>
    </form>
</div>

<!--
    현재, 변경, 확인 비밀번호에 내용이 작성되어 있는지 확인.
    변경, 확인 비밀번호가 같은 값인지 확인
    확인이 제대로 되었다면 send를 날린다. (현재, 변경)
    로그인한 사람의 현재 비밀번호가 맞으면 변경 비밀번호로 수정을 하고, 로그아웃 한 뒤, 로그인 화면으로 이동
    현재 비밀번호가 다르면 비밀번호 변경 페이지로 다시 돌아와서 에러 메시지 출력!!!
-->