<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="my" uri="tld/MyCustomJstlTag.tld" %>
<%@ page contentType="text/html;charset=utf-8" language="java" %>
<c:set var="profileImg" value="/res/img/user/defaultProfile.png"/>
<c:if test="${sessionScope.loginUser.profileimg != null}">
    <c:set var="profileImg" value="/images/user/${sessionScope.loginUser.iuser}/${sessionScope.loginUser.profileimg}"/>
</c:if>
<div id="data" data-iuser="${sessionScope.loginUser.iuser}"></div>
<div class="flex-direction-column flex-container flex-center">
    <h1 id="profile_title">프로필 정보</h1>

        <my:profileImg idVal="profile_view"
                       classVal="circular_img size300 circular_change pointer"
                       iuser="${sessionScope.loginUser.iuser}"
                       profileImgVal="${sessionScope.loginUser.profileimg}"/>

    <input type="file" id="profile_file" class="hidden" accept="image/*">
    <div>아이디: ${sessionScope.loginUser.uid}</div>
    <div>이름: ${sessionScope.loginUser.nm}</div>
    <div>성별: ${sessionScope.loginUser.gender == 1 ? '남성' : '여성'}</div>
</div>