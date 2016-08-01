<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>管理者ログイン</title>
</head>
<body>

<div class="login-form">

<p><c:out value="${message}"></c:out></p>

 <form:form modelAttribute="ManageForm">
  <div><form:errors path="*"  /></div>
	<label for="loginId">ログインID</label><br />
	<input type= "number" name="loginId" value="${loginManager.loginId}" id="loginId"/> <br />
	<br />
	<br />

	<label for="password">パスワード</label><br />
	<input name="password" type="password" id="password"/> <br />
	<br />
	<br />

	<input id="submit" type="submit" value="ログイン" /> <br />
</form:form>
</div>

</body>
</html>
