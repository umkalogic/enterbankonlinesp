<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ page isELIgnored="false" %>
<%@ page session="true" %>

<fmt:setLocale value="${sessionScope.lang}" scope="session"/>
<fmt:setBundle basename="messages" />

<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <link rel="stylesheet" type="text/css" href="../../static/css/bootstrap.min.css" />
    <link rel="stylesheet" type="text/css" href="../../static/css/login.css" />
    <link rel="stylesheet" type="text/css" href="../../static/css/header.css" />

    <title>Enter Bank 24</title>
</head>
<body>
<div class="container-fluid fon">

    <jsp:include page="fragments/nav-language.jsp">
        <jsp:param name="page" value="login"/>
    </jsp:include>

    <section class="container-fluid form-login">

        <form action="controller" method="POST"
              class="form" id="login_form" autocomplete="off">
<%--            <input type="hidden" name="command" value="login"/>--%>
            <h1 class="form-control"><fmt:message key="text.welcome" /></h1>
            <div class="form-group">
                <input type="text" id="user_name" name="user_name" placeholder="<fmt:message key="label.login" />"
                       class="form-control">
            </div>
            <div class="form-group">
                <input type="password" name="password" placeholder="<fmt:message key="label.password" />"
                       id="password" class="form-control">
            </div>
            <div class="form-group col-xs-4">
                <button class="form-control btn btn-lg btn-primary btn-block btn-flat" name="command" value="login" type="Submit">
                    <fmt:message key="label.submit" /></button>
            </div>
            <div align="center">
                <p style="font-size: 20px; color: #FF1C19;"><c:out value="${requestScope.errorMessage}" /></p>
            </div>

        </form>
    </section>


</div>
<script src="../../static/js/jquery-3.2.1.slim.min.js"></script>
<script src="../../static/js/popper.min.js"></script>
<script src="../../static/bootstrap/bootstrap.min.js"></script>
</body>
</html>
