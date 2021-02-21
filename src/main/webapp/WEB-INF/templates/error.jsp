<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib uri="http://example.com/functions" prefix="f" %>
<%@ taglib prefix="fmr" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ page isELIgnored="false" %>

<fmt:setLocale value="${sessionScope.lang}" scope="session"/>
<fmt:setBundle basename="messages" />
<html>
<head>
    <title>Error page</title>
    <meta charset="utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <link rel="stylesheet" href="../../static/css/bootstrap.min.css"/>
    <link rel="stylesheet" href="../../static/css/header.css"/>
    <link rel="stylesheet" type="text/css" href="../../static/css/registration.css"/>
</head>
<body>
<form action="${pageContext.servletContext.contextPath}/controller?command=default" method="get">
    <button class="btn btn-md btn-warning btn-block" type="Submit"><fmt:message key="info.returnback" /></button>
</form>
<div class="container-fluid">
    <jsp:include page="fragments/nav-language.jsp">
        <jsp:param name="page" value="error"/>
    </jsp:include>
</div>

<div class="container fon">
    <h1><fmt:message key="error.occured" /></h1>
    <p><fmt:message key="error.hasoccured" /></p>
    <br>
    <p style="color:darkred;"><c:out value="${requestScope.errorMessage}" /></p>
    <c:remove var="${requestScope.errorMessage}" />
</div>

<script src="../../static/js/jquery-3.2.1.slim.min.js"></script>
<script src="../../static/js/popper.min.js"></script>
<script src="../../static/bootstrap/bootstrap.min.js"></script>

</body>
</html>