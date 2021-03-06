<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ page contentType="text/html;charset=utf-8" language="java" %>
<div class="flex-container flex-center flex-direction-column">
    <h1 id="login_title">로그인</h1>
    <form action="/user/login" method="post" id="loginFrm">
        <div style="color: red; font-weight: bold;">${requestScope.msg}</div>
        <div class="id_wrap">
            <input type="text" name="uid" placeholder="아이디 입력" value="${requestScope.tryLogin.uid}">
        </div>
        <div class="pw_wrap">
            <input type="password" name="upw" placeholder="비밀번호 입력">
        </div>
        <div>
            <input type="submit" value="로그인">
            <a href="/user/join"><input type="button" value="회원가입"></a>
        </div>
    </form>
</div>

<%--
    로그인 처리
    세션에 UserEntity 객체 주소값 저장 하는데
    키값은 Const.LOGIN_USER를 사용한다
    객체에 iuser, uid, nm, profileimg 값만 저장한다.
    로그인 성공시 /board/list 주소값으로 이동
    로그인 실패시 login.jsp에서 메시지 출력
    (아이디 없음, 비밀번호 확인, 알 수 없는 에러 발생)
--%>