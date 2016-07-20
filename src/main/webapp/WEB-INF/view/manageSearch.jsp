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

<table border="" cellpadding="13" align="center">
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

		<c:forEach items="${bokList}" var="book">
			<tr>

				<td><c:out value="${book.bookName}" /></td>
				<td><c:out value="${book.authorName}" /></td>
				<td><c:out value="${book.publisher}" /></td>
				<td><c:out value="${book.documentId}" /></td>
				<td><c:out value="${book.shelfId}" /></td>
				<td><c:out value="${book.status}" /></td>
				<td><c:out value="${book.bookId}" /></td>
				<td><form:form modelAttribute="BookForm" >
				    <form:checkboxes path="book" items="${book.bookId}"  />
				    </form:form></td>

    		</tr>
		</c:forEach>
	</tbody>
</table>


</body>
</html>