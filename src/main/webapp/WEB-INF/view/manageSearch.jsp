<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>管理者検索結果</title>
</head>
<body>
<h1>検索一覧</h1>
<a href="reserveBook"><button >検索画面</button></a>
<form:form modelAttribute="searchedList" method="GET" action="manageConfilme" >
<table border="3" cellpadding="13" align="center">
	<thead>
		<tr>
			<th>書名</th>
			<th>著者名</th>
			<th>出版社名</th>
			<th>書類種類</th>
			<th>棚番号</th>
			<th>貸出状況</th>
			<th>予約人数</th>
			<th>予約</th>

		</tr>
	</thead>

	<tbody align="center">

		<c:forEach items="${searchedList}" var="search">
			<tr>

				<td><c:out value="${search.bookName}" /></td>
				<td><c:out value="${search.authorName}" /></td>
				<td><c:out value="${search.publisher}" /></td>
				<td><c:out value="${search.documentName}" /></td>
				<td><c:out value="${search.shelfId}" /></td>
				<td><c:out value="${search.statusName}" /></td>
				<td><c:out value="${search.bookId}" /></td>
				<td><input type="checkbox" name="bookId" value="${search.bookId}"></td>

    		</tr>
		</c:forEach>
	</tbody>
</table>

 <input type="submit" value = "予約確認">
</form:form>

</body>
</html>