<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>予約確認</title>
</head>
<body>



<br />
<table border="" cellpadding="13" align="center">
	<thead>
		<tr>
			<th>書名</th>
			<th>著者名</th>
			<th>出版社名</th>
			<th>書類種類</th>
			<th>保管図書館</th>


		</tr>
	</thead>

	<tbody align="center">

		<c:forEach items="${checkedList}" var="cheack">
			<tr>

				<td><c:out value="${cheack.bookName}" /></td>
				<td><c:out value="${cheack.authorName}" /></td>
				<td><c:out value="${cheack.publisher}" /></td>
				<td><c:out value="${cheack.documentName}" /></td>
				<td><c:out value="${cheack.libraryName}" /></td>


    		</tr>
		</c:forEach>
	</tbody>
</table>

<h3>${errorMessage}</h3>
<h3>${message}</h3>
<c:forEach items="${reserveBook}" var="reserveBook">
	<c:out value="${reserveBook.bookName}" /><br />
</c:forEach>

<form:form modelAttribute="reserveForm">
<div><form:errors path="*"  /></div>
<br />
<br />
ユーザーID：<input name = "userId"/><br>
<input type="submit" value = "予約">
</form:form>
<br />

<form action="manageConfilme" method="post">
<input type="submit" name="cancel" value="キャンセル">
</form>



</body>
</html>