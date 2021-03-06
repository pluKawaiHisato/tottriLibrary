<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
<meta charset="utf-8">
<title>予約</title>
</head>
<body>
	<h1> 予約検索 </h1>
    <h3>${errorMessage}</h3>

<form:form modelAttribute="bookForm" method="GET" action="manageSearch" >
    <div><form:errors path="*"  /></div>
     <h2>検索条件</h2>
         書名：<input name = "bookName"/><br>
        著者名：<input name = "authorName"/><br>
        出版社名：<input name = "publisher"/><br>
        ISBN：<input type="number" name="isbn" /><br>
        書類種類：	<select name = "documentId">
        			<option value= "null">(選択出来ます)</option>
						<c:forEach items = "${ Document }" var = "document">
							<option value = "${ document.documentId }" <c:if test = "${ document.documentId == book.documentId   }">selected='selected'</c:if> >
							<c:out value = "${ document.documentName }" /></option>
						</c:forEach>

					</select><br>
        棚番号：<input type="number" name="shelfId"/><br>

        <input type="submit" value = "検索">
    </form:form>
    <a href="home"><button >ホーム</button></a>
</body>
</html>