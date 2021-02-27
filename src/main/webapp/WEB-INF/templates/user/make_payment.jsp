<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>

<%--<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>--%>
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
        <jsp:param name="page" value="showformforpayment"/>
        <jsp:param name="bankaccountfrom" value="${requestScope.bankaccountfrom}"/>
        <jsp:param name="id" value="${requestScope.id}"/>
        <jsp:param name="currency" value="${requestScope.currency}"/>
    </jsp:include>

    <jsp:include page="../fragments/nav-logout.jsp"/>

    <div class="container-fluid" style="margin-top:40px;">
        <span><fmt:message key="text.welcome" /> <c:out value="${sessionScope.activeUserName}" /></span>
        <hr>
    </div>

    <div class="container-fluid">
        <h1><fmt:message key="title.payments"/></h1>
        <hr>
        <h2><fmt:message key="title.create.payment"/></h2>

        <div class = "container">
            <form action="controller" method="POST" role="form">
                <input type="hidden" name="id" value="${requestScope.id}">
                <input type="hidden" name="bankaccountfrom" value="${requestScope.bankaccountfrom}">
                <span><fmt:message key="payment.from"/>:    ${requestScope.bankaccountfrom}</span><br><br>
                <span><fmt:message key="payment.to"/>:    </span>
                <c:set var="msg"><fmt:message key="form.validation.message.bank.account.number"/></c:set>
                <input type="text" name="tobankaccount" required class="form-control col-4"
                pattern="(\d{14})" oninvalid="this.setCustomValidity('${msg}')" oninput="this.setCustomValidity('')"><br>
                <span><fmt:message key="payment.paymentAmount"/>:    </span>
                <div class="form-control col-4">
                    <c:set var="msg2"><fmt:message key="form.validation.message.payment.amount"/></c:set>
                <input type="text" name="paymentamount" required
                       data-behaviour="decimal" min="0.01" pattern="([0-9]+([.][0-9]{0,2})?|[.][0-9]+)"
                       oninvalid="this.setCustomValidity('${msg2}')" oninput="this.setCustomValidity('')"><span><c:out value="${param.currency}"/></span>
                </div>
                <input type="hidden" name="currency" value="${requestScope.currency}">
                <br><br>
                <button type="submit" name="command" value="createpayment" class="btn btn-info col-2">
                    <fmt:message key="payment.create"/></button><br><br>
                <c:if test="${requestScope.errorMessage != null}">
                   <h2 class="text-danger"><fmt:message key="error.message"/></h2>
                    <p class="text-info">${requestScope.infoMessage}</p>
                </c:if>
            </form>
        </div>
        <hr>
        <a href="${pageContext.servletContext.contextPath}/controller?command=userhome"><fmt:message key="label.backtouserlist"/></a>
    </div>

    <br><br><br><br>
</div>
<script src="../../../static/js/jquery-3.2.1.slim.min.js"></script>
<script src="../../../static/js/popper.min.js"></script>
<script src="../../../static/bootstrap/bootstrap.min.js"></script>
<script src="../../../static/js/enabledisable.js"></script>
</body>
</html>
