<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://example.com/functions" prefix="f" %>
<%@ taglib prefix="fmr" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ page isELIgnored="false" %>

<fmt:setLocale value="${sessionScope.lang}" scope="session"/>
<fmt:setBundle basename="messages" />
<c:set var="prevpage" value="userhome" />
<html>

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <link rel="stylesheet" type="text/css" href="../../../static/bootstrap/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="../../../static/css/home.css"/>
    <link rel="stylesheet" type="text/css" href="../../../static/css/header.css"/>
    <title><fmt:message key="payments.list" /></title>
</head>
<body>
<div class="container-fluid">
    <jsp:include page="../fragments/nav-language.jsp">
        <jsp:param name="page" value="makepayment"/>
    </jsp:include>
</div>
<div class="container-fluid">
    <form action="controller" method="get">
        <button class="btn btn-md btn-dark btn-block"
                type="Submit" name="command" value="logout"><fmt:message key="label.logout" /></button>
    </form>
    <div class="container-fluid" style="margin-top:40px;">
        <span><fmt:message key="text.welcome" /> <c:out value="${sessionScope.activeUserName}" /></span>
        <hr>
    </div>

    <div class="container-fluid">
        <h1><fmt:message key="title.payments"/></h1>
        <hr>
        <h2><fmt:message key="title.create.payment"/></h2>

        <div class = "container">
            <form autocomplete="off" action="controller" method="POST" role="form">
                <input type="hidden" name="command" value="sendpayment">
                <input type="hidden" name="id" value="${param.id}">
                <span><fmt:message key="payment.from"/>:    ${param.bankaccountfrom}</span><br>
                <br>
                <span><fmt:message key="payment.to"/>:    </span>
                <input type="text" name="tobankaccount" required class="form-control">
                <input type="text" th:field="*{toBankAccount}" class="form-control mb-4 col-4">
                <label th:if="${#fields.hasErrors('toBankAccount')}" th:errors="*{toBankAccount}"
                       class="validation-message"></label><br>
                <span th:text="#{payment.paymentAmount} + ':   '"></span>
                <input type="text" th:field="*{paymentAmount}" class="form-control mb-4 col-4">
                <label th:if="${#fields.hasErrors('paymentAmount')}" th:errors="*{paymentAmount}"
                       class="validation-message"></label>
                <br>
                <button type="submit" class="btn btn-info col-2" th:text="#{payment.create}"></button>
                <br>
                <br>
                <h2 th:if="${errorMessage}"><span class="text-danger" th:utext="#{error.message}"></span></h2>
            </form>
        </div>
        <p th:if="${stringInfo}" th:utext="#{stringinfo.something.went.wrong}" style="color:red;"></p>
        <hr>
        <a th:href = "@{/user/home}" th:text="#{label.backtouserlist}"></a>
    </div>
</div>
<script th:src="@{static/js/jquery-3.2.1.slim.min.js}"></script>
<script th:src="@{static/js/popper.min.js}"></script>
<script th:src="@{static/bootstrap/bootstrap.min.js}"></script>
<script th:src="@{static/js/enabledisable.js}"></script>
</body>
</html>
