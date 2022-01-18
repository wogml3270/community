<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="my" uri="tld/MyCustomJstlTag.tld" %>
<div class="p-20">
<c:if test="${sessionScope.loginUser.iuser == data.iuser}">
    <div>
        <button id="modBtn">수정</button>
        <button id="delBtn">삭제</button>
    </div>
</c:if>
    <div id="data"
         data-icategory="${data.icategory}"
         data-iboard="${data.iboard}"
         data-iuser="${sessionScope.loginUser.iuser}"
         data-profileimg="${sessionScope.loginUser.profileimg}"></div>
    <div>카테고리: ${data.categorynm}</div>
    <div>조회수: ${data.hits}</div>
    <div>등록일시: ${data.rdt}</div>
    <div>글쓴이: ${data.writernm}</div>
    <div>제목: <c:out value="${data.title}" /></div>
    <hr class="m-t-20">
    <div class="h-200 m-t-20"><c:out value="${data.ctnt}" /></div>
    <div>
        <a href="/board/detail?iboard=${requestScope.prevNext.previboard}" class="${requestScope.prevNext.previboard == 0 ? 'invisibility' : ''}"><button>이전글</button></a>
        <a href="/board/detail?iboard=${requestScope.prevNext.nextiboard}" class="${requestScope.prevNext.nextiboard == 0 ? 'invisibility' : ''}"><button>다음글</button></a>
    </div>
    <c:if test="${sessionScope.loginUser != null}">
        <div class="m-t-20">
            <form id="cmtFrm">
                <input type="text" name="ctnt">
                <input type="button" id="btn_submit" value="댓글달기">
            </form>
        </div>
    </c:if>
    <div class="m-t-20" id="cmt_list"></div>
</div>
