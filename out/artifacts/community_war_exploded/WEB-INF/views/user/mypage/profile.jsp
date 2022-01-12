<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ page contentType="text/html;charset=utf-8" language="java" %>
<c:set var="profileImg" value="/res/img/user/default_img.png"/>
<c:if test="${sessionScope.loginUser.profileimg != null}">
    <c:set var="profileImg" value="/images/user/${sessionScope.loginUser.iuser}/${sessionScope.loginUser.profileimg}"/>
</c:if>
<h1>프로필 정보</h1>
<div class="flex-direction-column flex-container flex-center">
    <div id="profile_view" class="circular_img circular_size300 pointer"><img src="${profileImg}"></div>
    <input type="file" id="profile_file" class="hidden" accept="image/*">
    <div>아이디: ${sessionScope.loginUser.uid}</div>
    <div>이름: ${sessionScope.loginUser.nm}</div>
    <div>성별: ${sessionScope.loginUser.gender == 1 ? '남성' : '여성'}</div>
</div>