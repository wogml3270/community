<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="my" uri="tld/MyCustomJstlTag.tld" %>

<tiles:importAttribute name="menuList"/>
<c:set var="currentPagePath" value="${requestScope['javax.servlet.forward.request_uri']}" />
<c:set var="splitURI" value="${fn:split(currentPagePath, '/')}"/>
<c:set var="lastPath" value="${splitURI[fn:length(splitURI) - 1]}"/>
<c:set var="profileImg" value="/res/img/user/default_img.png"/>
<c:if test="${sessionScope.loginUser.profileimg != null}">
    <c:set var="profileImg" value="/images/user/${sessionScope.loginUser.iuser}/${sessionScope.loginUser.profileimg}"/>
</c:if>
<header class="h-100">
    <div class="flex-container flex-align-center p-lr-20">
        <c:choose>
            <c:when test="${sessionScope.loginUser == null}">
                <div class="m-r-20"><a href="/user/login" class="color-white">로그인</a></div>
            </c:when>
            <c:otherwise>
                <div class="m-r-20">
                    <a href="/user/mypage/profile">
                        <my:profileImg classVal="circular_img size40"
                                       iuser="${sessionScope.loginUser.iuser}"
                                       imgIdVal="header-profileimg"
                                       profileImgVal="${sessionScope.loginUser.profileimg}"/>
                    </a>
                </div>
                <div class="m-r-20"><a href="/user/logout" class="color-white">로그아웃</a></div>
            </c:otherwise>
        </c:choose>
        <c:forEach items="${menuList}" var="item">
            <div class="m-r-30 ${lastPath == ''.concat(item.icategory) ? 'menu-selected' : ''}">
                <a href="/board/list/${item.icategory}" class="color-white">${item.nm}</a>
            </div>
        </c:forEach>
    </div>
</header>