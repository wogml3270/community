<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="my" uri="tld/MyCustomJstlTag.tld" %>
<div class="flex-container flex-center flex-direction-column">
<c:if test="${sessionScope.loginUser.iuser == data.iuser}">
    <div>
        <button id="modBtn">수정</button>
        <button id="delBtn">삭제</button>
    </div>
</c:if>
    <div id="data" data-icategory="${data.icategory}" data-iboard="${data.iboard}"></div>
    <div>카테고리: ${data.categorynm}</div>
    <div>조회수: ${data.hits}</div>
    <div>등록일시: ${data.rdt}</div>
    <div>글쓴이: ${data.writernm}</div>
    <div>제목: <c:out value="${data.title}" /></div>
    <hr>
    <div><c:out value="${data.ctnt}" /></div>
    <div>
        <c:if test="${requestScope.prevNext.previboard > 0}">
            <a href="/board/detail?iboard=${requestScope.prevNext.previboard}"><button>이전글</button></a>
        </c:if>
        <c:if test="${requestScope.prevNext.nextiboard > 0}">
            <a href="/board/detail?iboard=${requestScope.prevNext.nextiboard}"><button>다음글</button></a>
        </c:if>
    </div>
    <c:if test="${sessionScope.loginUser != null }">
        <div class="m-t-20">
            <form id="cmtFrm">
                <input type="text" name="ctnt">
                <input type="button" id="btn_submit" value="댓글달기">
            </form>
        </div>
    </c:if>
    <div class="m-t-20">comment list</div>
    <table>
        <c:forEach items="${requestScope.list}" var="item">
            <tr class="record" data-iboard="${item.icmt}">
                <td>${item.iboard}</td>
                <td><c:out value="${item.ctnt}"/></td>
                <td>${item.hits}</td>
                <td><my:profileImg idVal="profile_view"
                                   classVal="circular_img size30"
                                   iuser="${item.iuser}"
                                   profileImgVal="${item.profileimg}"/>
                        ${item.writernm}</td>
                <td>${item.rdt}</td>
            </tr>
        </c:forEach>
    </table>
</div>
