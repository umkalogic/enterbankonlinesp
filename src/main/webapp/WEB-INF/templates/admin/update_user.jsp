<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%--<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>--%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib uri="http://example.com/functions" prefix="f" %>

<%@ page isELIgnored="false" %>

<fmt:setLocale value="${sessionScope.lang}" scope="session"/>
<fmt:setBundle basename="messages" />
<c:set var="prevpage" value="adminhome" />
<html>
<head>
<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

	<link rel="stylesheet" type="text/css" href="../../../static/bootstrap/bootstrap.min.css">
	<link rel="stylesheet" type="text/css" href="../../../static/css/home.css"/>
	<link rel="stylesheet" type="text/css" href="../../../static/css/header.css"/>
	<title><fmt:message key="title.update.user" /></title>
</head>
<body>

	<div class="container-fluid">
		<jsp:include page="../fragments/nav-language.jsp">
			<jsp:param name="page" value="showformforuserupdate"/>
			<jsp:param name="id" value="${requestScope.theuser.userId}"/>
		</jsp:include>
		<jsp:include page="../fragments/nav-logout.jsp"/>


		<h1><fmt:message key="title.usermanagementsystem" /></h1>
		<hr>
		<h2><fmt:message key="title.user.update" /></h2>

		<div class = "container">
		<form action="controller" method="POST" role="form">
			<input type="hidden" name="id" value="${requestScope.theuser.userId}">
			<input type="text" name="username" value="${requestScope.theuser.userName}" class="form-control mb-4 col-4"
				   placeholder="<fmt:message key="label.username"/>" required>
<%--			<label th:if="${#fields.hasErrors('userName')}" th:errors="*{userName}"--%>
<%--				   class="validation-message"></label>--%>
			<input type="text" name="email" value="${requestScope.theuser.email}"
				   placeholder="<fmt:message key="label.email"/>" class="form-control mb-4 col-4" required>
<%--			<label th:if="${#fields.hasErrors('email')}" th:errors="*{email}"--%>
<%--					class="validation-message"></label>--%>
			<br/>
			<div class="form-control mb-4 col-4">
			  <label><fmt:message key="user.enabled"/>  ?</label>
				<input type="checkbox" name="isactive" id="chk1"
                 <c:if test="${requestScope.theuser.active}">checked="checked"</c:if>>
				<input type="hidden" name="isactive" id="chk2" value="0">
			</div>
			<button type="submit" name="command" value="submitformforuserupdate" class="btn btn-info col-2"><fmt:message key="user.update"/></button>
			<br>
			<br>
			<c:if test="${requestScope.errorMessage}">
			   <h2 class="text-danger"><fmt:message key="${requestScope.errorMessage}"/></h2>
			</c:if>
			<c:if test="${requestScope.infoMessage}">
				<p class="text-danger"><fmt:message key="${requestScope.infoMessage}"/></p>
			</c:if>
		</form>
		</div>
		<hr>
		<a href = "${pageContext.servletContext.contextPath}?command=adminhome"><fmt:message key="label.backtouserlist"/></a>
		<br><br>	<br><br>
	</div>
	<script src="../../../static/js/jquery-3.2.1.slim.min.js"></script>
	<script src="../../../static/js/popper.min.js"></script>
	<script src="../../../static/bootstrap/bootstrap.min.js"></script>
	<script src="../../../static/js/enabledisable.js"></script>
</body>
</html>