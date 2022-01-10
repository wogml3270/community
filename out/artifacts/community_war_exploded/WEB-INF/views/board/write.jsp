<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=utf-8" language="java" %>
<div>
    <h1>글쓰기</h1>
    <form action="/board/write" method="post">
        <input type="hidden" name="icategory" value="${param.icategory}">
        <div><label><input type="text" name="title" placeholder="제목"></label></div>
        <div><label><textarea name="ctnt" placeholder="내용"></textarea></label></div>
        <div><input type="submit" value="WRITE"></div>
    </form>
</div>