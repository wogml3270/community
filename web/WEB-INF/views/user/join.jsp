<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ page contentType="text/html;charset=utf-8" language="java" %>
<div class="flex-container flex-center flex-direction-column">
    <h1 id="join_title">회원가입 페이지</h1>
    <form action="/user/join" method="post" id="joinFrm">
        <div>${requestScope.msg}</div>
        <div style="position: relative;">
            <label><input type="text" name="uid" maxlength="15" placeholder="아이디 입력" required></label>
            <input type="button" value="중복 확인" id="idChkBtn"><span id="idChkMsg"></span>
        </div>
        <div><label><input type="password" name="upw" placeholder="비밀번호 입력" required></label></div>
        <div><label><input type="password" id="upwChk" placeholder="비밀번호 확인" required></label></div>
        <div><label><input type="text" name="nm" placeholder="이름 입력" required></label></div>
        <div>
            <label>남성 <input type="radio" name="gender" value="1"></label>
            <label>여성 <input type="radio" name="gender" value="2"></label>
        </div>
        <div>
            <input type="submit" value="JOIN">
            <input type="reset" value="RESET">
        </div>
    </form>
</div>